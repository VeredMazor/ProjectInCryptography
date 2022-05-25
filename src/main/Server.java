package main;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import Ciphers.BlockCiphers.BlockCipher;
import MacAndElgamal.ElGamalECC;
import MacAndElgamal.EncryptedMessage;
import Services.MACService;

public class Server {

	private static ImageService imageService = new ImageService();
	private static UserService userService = new UserService();
	private static ArrayList<User> userList;
	private static User currentUser;
	private byte[] privatekey;
	private String macKey;
	private List<EncryptedMessage> ciphreKeyElGamal;
	private List<EncryptedMessage> macKeyElGamal;

	@SuppressWarnings("static-access")
	public Server() {
		User Alise = new User("Alise");
		userService.addUser(Alise);
		userList = userService.getInstance();
		privatekey = GenerateSecuredKey().getEncoded();
		macKey = Base64.encode(GenerateSecuredKey().getEncoded());
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static ArrayList<User> getUserList() {
		return userList;
	}

	public static void setUserList(ArrayList<User> userList) {
		Server.userList = userList;
	}

	public byte[] getPrivatekey() {
		return privatekey;
	}

	public String toStringPrivatekey() {
		return privatekey + "";
	}

	static boolean isUserValid(String username, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (userList.stream().anyMatch(user -> user.getUsername().equals(username))) {
			for (User user : userList) {
				if (user.getUsername().equalsIgnoreCase(username)) {
					currentUser = user;
				}
			}
			if (!new String(currentUser.getHashedPassword()).equals(encryptPassword(password))) {
				System.out.println(Constants.error + "Incorrect password, please try again");
				return false;
			}
		} else {
			System.out.println(Constants.error + "Incorrect username, please try again");
			return false;
		}
		System.out.println("Connected Successfully!");
		return true;
	}

	public static boolean IsImageExist(String input) {
		@SuppressWarnings("static-access")
		ArrayList<String> images = imageService.getInstance();
		if (images.stream().anyMatch(image -> image.equals(input))) {
			System.out.println("The Image: " + input + " has been chosed");
			return true;
		}
		System.out.println(Constants.error + "Incorrect name of image, please try again");
		return false;
	}

	private static String encryptPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = Constants.salt;
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] result = factory.generateSecret(spec).getEncoded();
		return new String(result);
	}

	public byte[] getPlainText(String imageName) throws IOException {
		Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
		String path = root.toString() + "\\src\\" + "ImageIO\\" + imageName;
		return Files.readAllBytes(Paths.get(path));
	}

	public void AddMac(String encryptedData, ElGamalECC elGamalService) {
		macKeyElGamal = elGamalService.encrypt(macKey);
		setMacKey(macKeyElGamal);
		MACService.addMAC(encryptedData, macKey);
	}

	public List<EncryptedMessage> getCipherKey() {
		return ciphreKeyElGamal;
	}

	public void setMacKey(List<EncryptedMessage> encryptedMessages) {
		macKeyElGamal = encryptedMessages;
	}
	
	public List<EncryptedMessage> GetMacKey() {
		return macKeyElGamal;
	}

	public String EncryptData(BlockCipher cipher, String imageName, ElGamalECC elGamalService) {
		String ivstr = "1234567890123456";
		byte[] IV = ivstr.getBytes();
		byte[] plain = null;
		try {
			plain = getPlainText(imageName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		cipher.setKey(privatekey);
		// push pk to ELGAMAL.
		ciphreKeyElGamal = elGamalService.encrypt(Base64.encode(privatekey));
		cipher.setIV(IV);

		byte[] enc = cipher.encrypt(plain);
		String res = Base64.encode(enc);
		AddMac(res,elGamalService);
		return res;
	}

	public static Key GenerateSecuredKey() {

		String cipher = "AES";
		int keySize = 128;
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance(cipher);
			keyGenerator.init(keySize);
			return keyGenerator.generateKey();
		}

		catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}