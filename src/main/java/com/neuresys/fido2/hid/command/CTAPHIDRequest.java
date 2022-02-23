package com.neuresys.fido2.hid.command;

import java.util.List;

import com.neuresys.fido2.hid.packet.HIDPacket;

public interface CTAPHIDRequest {

	List<HIDPacket> getPacket();

}
