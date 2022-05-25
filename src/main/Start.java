package main;

import Ciphers.BlockCiphers.BlockCipher;
import Ciphers.BlockCiphers.BlockCiphersList;
import MacAndElgamal.ElGamalECC;
import MacAndElgamal.EllipticCurve;
import MacAndElgamal.Point;
import Services.DecryptionService;
import Services.MACService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class Start {
	private static String username;
	private static String password;
	private static String imageName;
	private static Server server;
	private static ElGamalECC elGamalService;

	@SuppressWarnings("static-access")
	public static void main(String arrg[]) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		Scanner input = new Scanner(System.in);
		server = new Server();
		EllipticCurve ec = new EllipticCurve(-1, 188, 65537);

		userCredentialInput(input);
		while (!server.isUserValid(username, password)) {
			userCredentialInput(input);
		}
		imageInput(input);
		while (!server.IsImageExist(imageName)) {
			imageInput(input);
		}
		elGamalService = new ElGamalECC(ec, server.getCurrentUser().getPrivateKey());
		getImageFromServer();
	}

	public static void userCredentialInput(Scanner input) {
		System.out.println("Enter username:");
		username = input.nextLine();
		System.out.println("Enter password:");
		password = input.nextLine();
	}

	private static void imageInput(Scanner input) {
		System.out.println("Please choose image from Image collection\nImage collection:\n"
				+ Constants.pictureCollection + "\n\nWrite Image Name:");
		imageName = input.nextLine();
	}

	private static void getImageFromServer() throws IOException {
		imageName = imageName + ".png";
		Point p = new Point(1, 6);
		elGamalService.setBasePoint(p);
		BlockCipher cipher = BlockCipher.getInstance(BlockCiphersList.CAST6(), BlockCipher.CBC);
		String encryptedData = server.EncryptData(cipher, imageName, elGamalService);

		// Alise SIDE:
		String cipherKey = elGamalService.decrypt(server.getCipherKey());
		String macKey = elGamalService.decrypt(server.GetMacKey());
		if (MACService.checkMAC(macKey, encryptedData)) {
			DecryptionService.Decryption(cipher, encryptedData, imageName, cipherKey);
		} else {
			System.out.println("MAC verification failed.\n");
		}
	}
}