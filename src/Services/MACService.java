package Services;

import MacAndElgamal.Mac;

public class MACService {
	private static String macTag;

	public static void addMAC(String g,String macKey) {
		macTag = Mac.addMac(g, macKey, g.length());
	}

	public static boolean checkMAC(String macKey,String g) {
		return Mac.checkMac(macTag, macKey, g.length());
	}
	
}
