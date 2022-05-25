package main;

import java.nio.ByteBuffer;

public class User {

	private String username;
	private String password;
	private byte[] hashedPassword;
	private Long privateKey = bytesToLong(Server.GenerateSecuredKey().getEncoded());

	public User(String username) {
		this.username = username;
		hashedPassword = Constants.AlisePass;
	}
	

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public Long getPrivateKey() {
		return privateKey;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getHashedPassword() {
		return hashedPassword;
	}
	
	public static long bytesToLong(final byte[] b) {
	    long result = 0;
	    for (int i = 0; i < Long.BYTES; i++) {
	        result <<= Byte.SIZE;
	        result |= (b[i] & 0xFF);
	    }
	    return result;
	}

}
