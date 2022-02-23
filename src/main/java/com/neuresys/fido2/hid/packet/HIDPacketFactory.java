package com.neuresys.fido2.hid.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.neuresys.fido2.hid.cte.CtapHIDCmd;

public enum HIDPacketFactory {

	INSTANCE;
	public HIDPacket getHIDPacket(byte [] data)
	{
		if ((data[4]&0x80)!=0)
		{
			return new HIDInitializationPacket(data);
		}else
		{
			return new HIDContinuationPacket(data);
		}
	}
	/*public HIDInitializationPacket mergePacket(HIDInitializationPacket init, HIDContinuationPacket continuousPacket )
	{
		List<HIDContinuationPacket> list=new ArrayList<>();
		list.add(continuousPacket);
		return mergePacket(init,list);
		
	}
	
	public HIDInitializationPacket mergePacket(HIDInitializationPacket init, List<HIDContinuationPacket> continuousPacket )
	{
		if (continuousPacket.isEmpty()) return init;
		else 
		{
			
			Collections.sort(continuousPacket, new Comparator<HIDContinuationPacket>() {

				@Override
				public int compare(HIDContinuationPacket arg0, HIDContinuationPacket arg1) {
					Integer seqArg0=arg0.getSeqNumber();
					Integer seqArg1=arg1.getSeqNumber();
					return seqArg0.compareTo(seqArg1);
					
				}
				
			});
			int size=init.getLength();
			byte [] data=new byte[size];
			System.arraycopy(init.getPayload(), 
	                 0,
	                 data,
	                 0,
	                 init.getPayload().length);
			int index=init.getPayload().length;
			size=size-init.getPayload().length;
			for (HIDContinuationPacket packet:continuousPacket)
			{
				int tocopy=packet.getPayload().length;
				if (size<64)
				{
					tocopy=size;
				}
				System.arraycopy(packet.getPayload(), 
		                 0,
		                 data,
		                 index,
		                 tocopy);
				index+=tocopy;
			}
			init.setPacket(data);
			return init;
		}
	}*/
}
