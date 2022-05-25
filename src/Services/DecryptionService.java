package Services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import Ciphers.BlockCiphers.BlockCipher;

public class DecryptionService {
	public static void Decryption(BlockCipher cipher, String encryptedData, String imageName, String key) throws IOException {

		cipher.setKey(Base64.decode(key));
		byte[] dec = cipher.decrypt(Base64.decode(encryptedData));
		ByteArrayInputStream bis = new ByteArrayInputStream(dec);
		BufferedImage bImage2 = ImageIO.read(bis);
		Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
		String path = root.toString() + "\\src\\" + "Result\\" + imageName;
		ImageIO.write(bImage2, "png", new File(path));
		System.out.println("image created");

	}
}