package it.tbridge;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.Test;

public class TestVari {
	
	@Test
	public void test1() {
		String hashedPassword=null;
		try {
			byte[] bytes = get_SHA_256_SecurePassword("password");
			hashedPassword = byteArrayToHex(bytes);
			Base64.getEncoder().encodeToString(bytes);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public byte[] get_SHA_256_SecurePassword(String passwordToHash) throws UnsupportedEncodingException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			//md.update(salt.getBytes("UTF-8"));
			byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
			return bytes;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String byteArrayToHex(byte[] a) {
		   StringBuilder sb = new StringBuilder(a.length * 2);
		   for(byte b: a)
		      sb.append(String.format("%02x", b));
		   return sb.toString();
		}
}
