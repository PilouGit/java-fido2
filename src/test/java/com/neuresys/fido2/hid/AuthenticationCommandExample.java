package com.neuresys.fido2.hid;



import java.security.SecureRandom;

import org.hid4java.HidDevice;
import org.hid4java.HidException;
import org.hid4java.HidManager;
import org.hid4java.HidServices;
import org.hid4java.HidServicesSpecification;
import org.hid4java.event.HidServicesEvent;
import org.hid4java.jna.HidApi;

/*
 * Demonstrate the USB HID interface using a FIDO2 USB device
 *
 * @since 0.8.0
 */
public class AuthenticationCommandExample extends BaseExample {

	boolean done=false;
  // Authentication data flags
  private static byte CTAP_AUTHDATA_USER_PRESENT = 0x01;
  private static byte CTAP_AUTHDATA_USER_VERIFIED = 0x04;
  private static byte CTAP_AUTHDATA_ATT_CRED = 0x40;
  private static byte CTAP_AUTHDATA_EXT_DATA = (byte) 0x80;

  // CTAPHID command opcodes
  private static byte CTAP_CMD_PING = 0x01;
  private static byte CTAP_CMD_MSG = 0x03;
  private static byte CTAP_CMD_LOCK = 0x04;
  private static byte CTAP_CMD_INIT = 0x06;
  private static byte CTAP_CMD_WINK = 0x08;
  private static byte CTAP_CMD_CBOR = 0x10;
  private static byte CTAP_CMD_CANCEL = 0x11;
  private static byte CTAP_KEEPALIVE = 0x3b;
  private static byte CTAP_FRAME_INIT = (byte) 0x80;

  // CTAPHID CBOR command opcodes
  private static byte CTAP_CBOR_MAKECRED = 0x01;
  private static byte CTAP_CBOR_ASSERT = 0x02;
  private static byte CTAP_CBOR_GETINFO = 0x04;
  private static byte CTAP_CBOR_CLIENT_PIN = 0x06;
  private static byte CTAP_CBOR_RESET = 0x07;
  private static byte CTAP_CBOR_NEXT_ASSERT = 0x08;
  private static byte CTAP_CBOR_BIO_ENROLL_PRE = 0x40;
  private static byte CTAP_CBOR_CRED_MGMT_PRE = 0x41;

  // U2F command opcodes
  private static byte U2F_CMD_REGISTER = 0x01;
  private static byte U2F_CMD_AUTH = 0x02;

  // U2F command flags
  private static byte U2F_AUTH_SIGN = 0x03;
  private static byte U2F_AUTH_CHECK = 0x07;

  // ISO7816-4 status words
  private static int SW_CONDITIONS_NOT_SATISFIED = 0x6985;
  private static int SW_WRONG_DATA = 0x6a80;
  private static int SW_NO_ERROR = 0x9000;

  // HID Broadcast channel ID
  private static int CTAP_CID_BROADCAST = 0xffffffff;

  private static int CTAP_INIT_HEADER_LEN = 7;
  private static int CTAP_CONT_HEADER_LEN = 5;

  // Maximum length of a CTAP HID report in bytes
  private static int CTAP_MAX_REPORT_LEN = 64;

  // Minimum length of a CTAP HID report in bytes
  private static int CTAP_MIN_REPORT_LEN = CTAP_INIT_HEADER_LEN + 1;

  // Maximum message size in bytes
  private static int FIDO_MAXMSG = 2048;

  // CTAP capability bits (set means supported)
  private static byte FIDO_CAP_WINK = 0x01;
  private static byte FIDO_CAP_CBOR = 0x04;
  private static byte FIDO_CAP_NMSG = 0x08;

  // Supported COSE algorithms
  private static int COSE_ES256 = -7;
  private static int COSE_EDDSA = -8;
  private static int COSE_ECDH_ES256 = -25;
  private static int COSE_RS256 = -257;

  // Supported COSE types
  private static int COSE_KTY_OKP = 1;
  private static int COSE_KTY_EC2 = 2;
  private static int COSE_KTY_RSA = 3;

  // Supported curves
  private static int COSE_P256 = 1;
  private static int COSE_ED25519 = 6;

  // Supported extensions
  private static byte FIDO_EXT_HMAC_SECRET = 0x01;
  private static byte FIDO_EXT_CRED_PROTECT = 0x02;

  // Supported credential protection policies
  private static int FIDO_CRED_PROT_UV_OPTIONAL = 0x01;
  private static int FIDO_CRED_PROT_UV_OPTIONAL_WITH_ID = 0x02;
  private static int FIDO_CRED_PROT_UV_REQUIRED = 0x03;

  // Secure random source for nonce
  private static SecureRandom secureRandom = new SecureRandom();

  // Instance variables
  private byte[] nonce = new byte[8];
  private byte[] fidoChannel = new byte[4];

  public static void main(String[] args) throws HidException {

    AuthenticationCommandExample example = new AuthenticationCommandExample();
    example.executeExample();

  }

  private void executeExample() throws HidException {

    printPlatform();

    // Demonstrate low level traffic logging
    HidApi.logTraffic = true;

    // Configure to use custom specification
    HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();

    // Use manual start
    hidServicesSpecification.setAutoStart(false);

    // Use data received events
    hidServicesSpecification.setAutoDataRead(true);
    hidServicesSpecification.setDataReadInterval(500);

    // Get HID services using custom specification
    HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);

    // Register for service events
    hidServices.addHidServicesListener(this);

    // Manually start HID services
    hidServices.start();

    // Enumerate devices looking for = 0xf1d0 usage page
    HidDevice fidoDevice = null;
    for (HidDevice hidDevice : hidServices.getAttachedHidDevices()) {
    
      if (hidDevice.getProduct().contains("FIDO2")) {
        System.out.println(ANSI_GREEN + "Using FIDO device: " + hidDevice.getPath() + ANSI_RESET);
        fidoDevice = hidDevice;
        break;
      }
    }

    if (fidoDevice == null) {
      // Shut down and rely on auto-shutdown hook to clear HidApi resources
      System.out.println(ANSI_YELLOW + "No FIDO2 devices attached." + ANSI_RESET);
    } else {
      if (handleInitialise(fidoDevice)) {
        // TODO Expand example to include a registration request
        //handleRegister(fidoDevice);
      }
    }

    waitAndShutdown(hidServices);

  }

  /**
   * Generate a random set of 8 bytes for use as a nonce
   */
  private void generateNonce() {

    secureRandom.nextBytes(nonce);

  }

  /**
   * Initialise the FIDO2 device and set a communications channel
   * @param hidDevice The device to use
   * @return True if the device is now initialised for use
   */
  private boolean handleInitialise(HidDevice hidDevice) {

    // Ensure device is open
    if (hidDevice.isClosed()) {
      if (!hidDevice.open()) {
        throw new IllegalStateException("Unable to open device");
      }
    }

    generateNonce();
InitPacketRequest request=new InitPacketRequest(this.nonce);
    System.out.println(ANSI_GREEN + "Sending CTAPHID_INIT..." + ANSI_RESET);
    int bytesWritten = hidDevice.write(request.getData(), CTAP_MAX_REPORT_LEN, (byte) 0x00, true);
    if (bytesWritten < 0) {
      System.out.println(ANSI_RED + hidDevice.getLastErrorMessage() + ANSI_RESET);
      return false;
    }

    return true;

  }

  @Override
  public void hidDataReceived(HidServicesEvent event) {
    super.hidDataReceived(event);

    // Analyse the response
    byte[] initialiseResponse = event.getDataReceived();
    InitPacketResponse response=new InitPacketResponse(initialiseResponse);
    System.out.println(response);
    System.out.println(ANSI_GREEN + "Sending CTAPHID_PING..." + ANSI_RESET);
   if (!done) {
    PingPacketRequest pingpacket=new PingPacketRequest(response.getNewChannelId(),"pilou");
    event.getHidDevice().write(pingpacket.getData(), CTAP_MAX_REPORT_LEN, (byte) 0x00, true);
   done=true;
   }
  }
}