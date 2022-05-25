package Services;

import java.io.IOException;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import Ciphers.BlockCiphers.BlockCipher;
import main.Server;

public class EncryptionService {

	public static String Encryption(BlockCipher cipher, byte[] plain, Long privateKey) throws IOException {

		String ivstr = "1234567890123456";
		byte[] KEY = String.valueOf(privateKey).getBytes();
		byte[] IV = ivstr.getBytes();

		cipher.setKey(KEY);
		//push pk to ELGAMAL.
		cipher.setIV(IV);

		byte[] enc = cipher.encrypt(plain);
		return Base64.encode(enc);
	}
}
