package com.neuresys.fido2.hid.cte;

public enum CtapHIDStatusCode {
	 CTAP2_OK ((byte)0x00),
			    CTAP1_ERR_INVALID_COMMAND ((byte)0x01),//#	The command is not a valid CTAP command.
			    CTAP1_ERR_INVALID_PARAMETER ((byte)0x02),//#	The command included an invalid parameter.
			    CTAP1_ERR_INVALID_LENGTH ((byte)0x03),//#	Invalid message or item length.
			    CTAP1_ERR_INVALID_SEQ ((byte)0x04),//# 	Invalid message sequencing.
			    CTAP1_ERR_TIMEOUT ((byte)0x05),//#	Message timed out.
			    CTAP1_ERR_CHANNEL_BUSY((byte)0x06),//# 	Channel busy.
			    CTAP1_ERR_LOCK_REQUIRED ((byte)0x0A),//#	Command requires channel lock.
			    CTAP1_ERR_INVALID_CHANNEL ((byte)0x0B),//#	Command not allowed on this cid.
			    CTAP2_ERR_CBOR_UNEXPECTED_TYPE ((byte)0x11),//#Invalid/unexpected CBOR error.
			    CTAP2_ERR_INVALID_CBOR ((byte)0x12),//# 	Error when parsing CBOR.
			    CTAP2_ERR_MISSING_PARAMETER  ((byte)0x14),//#	Missing non-optional parameter.
			    CTAP2_ERR_LIMIT_EXCEEDED ((byte)0x15),//# 	Limit for number of items exceeded.
			    CTAP2_ERR_UNSUPPORTED_EXTENSION  ((byte)0x16),//#	Unsupported extension.
			    CTAP2_ERR_CREDENTIAL_EXCLUDED  ((byte)0x19),//#	Valid credential found in the exclude list.
			    CTAP2_ERR_PROCESSING  ((byte)0x21),//#	Processing (Lengthy operation is in progress).
			    CTAP2_ERR_INVALID_CREDENTIAL  ((byte)0x22),//#	Credential not valid for the authenticator.
			    CTAP2_ERR_USER_ACTION_PENDING  ((byte)0x23),//#	Authentication is waiting for user interaction.
			    CTAP2_ERR_OPERATION_PENDING ((byte)0x24),//# 	Processing, lengthy operation is in progress.
			    CTAP2_ERR_NO_OPERATIONS  ((byte)0x25),//#	No request is pending.
			    CTAP2_ERR_UNSUPPORTED_ALGORITHM  ((byte)0x26),//#	Authenticator does not support requested algorithm.
			    CTAP2_ERR_OPERATION_DENIED  ((byte)0x27),//#	Not authorized for requested operation.
			    CTAP2_ERR_KEY_STORE_FULL ((byte)0x28),//# 	Internal key storage is full.
			    CTAP2_ERR_NO_OPERATION_PENDING ((byte)0x2A),//# 	No outstanding operations.
			    CTAP2_ERR_UNSUPPORTED_OPTION  ((byte)0x2B),//#	Unsupported option.
			    CTAP2_ERR_INVALID_OPTION  ((byte)0x2C),//#	Not a valid option for current operation.
			    CTAP2_ERR_KEEPALIVE_CANCEL ((byte)0x2D),//# 	Pending keep alive was cancelled.
			    CTAP2_ERR_NO_CREDENTIALS ((byte)0x2E),//# 	No valid credentials provided.
			    CTAP2_ERR_USER_ACTION_TIMEOUT  ((byte)0x2F),//#	Timeout waiting for user interaction.
			    CTAP2_ERR_NOT_ALLOWED  ((byte)0x30),//#	Continuation command, such as, authenticatorGetNextAssertion not allowed.
			    CTAP2_ERR_PIN_INVALID ((byte)0x31),//# 	PIN Invalid.
			    CTAP2_ERR_PIN_BLOCKED ((byte)0x32),//# 	PIN Blocked.
			    CTAP2_ERR_PIN_AUTH_INVALID  ((byte)0x33),//#	PIN authentication,pinUvAuthParam, verification failed.
			    CTAP2_ERR_PIN_AUTH_BLOCKED ((byte)0x34),//# 	PIN authentication,pinUvAuthParam, blocked. Requires power recycle to reset.
			    CTAP2_ERR_PIN_NOT_SET  ((byte)0x35),//#	No PIN has been set.
			    CTAP2_ERR_PIN_REQUIRED  ((byte)0x36),//#	PIN is required for the selected operation.
			    CTAP2_ERR_PIN_POLICY_VIOLATION  ((byte)0x37),//#	PIN policy violation. Currently only enforces minimum length.
			    CTAP2_ERR_PIN_TOKEN_EXPIRED  ((byte)0x38),//#	pinUvAuthToken expired on authenticator.
			    CTAP2_ERR_REQUEST_TOO_LARGE  ((byte)0x39),//#	Authenticator cannot handle this request due to memory constraints.
			    CTAP2_ERR_ACTION_TIMEOUT ((byte)0x3A),//# 	The current operation has timed out.
			    CTAP2_ERR_UP_REQUIRED ((byte)0x3B),//# 	User presence is required for the requested operation.
			    CTAP2_ERR_UV_BLOCKED  ((byte)0x3C),//#	Built in UV is blocked.
			    CTAP1_ERR_OTHER  ((byte)0x7F),//#	Other unspecified error.
			    CTAP2_ERR_SPEC_LAST  ((byte)0xDF),//#	CTAP 2 spec last error.
			    CTAP2_ERR_EXTENSION_FIRST  ((byte)0xE0),//#	Extension specific error.
			    CTAP2_ERR_EXTENSION_LAST  ((byte)0xEF),//#	Extension specific error.
			    CTAP2_ERR_VENDOR_FIRST ((byte)0xF0),//# 	Vendor specific error.
			    CTAP2_ERR_VENDOR_LAST  ((byte)0xFF);

				private byte value;

				CtapHIDStatusCode(byte value) {
					this.value=value;
				}
				public static CtapHIDStatusCode getByValue(byte b)
				{
					for (CtapHIDStatusCode code:CtapHIDStatusCode.values())
					{
						if (code.value==b) return code;
					}
					return CTAP1_ERR_OTHER;
				}
}
