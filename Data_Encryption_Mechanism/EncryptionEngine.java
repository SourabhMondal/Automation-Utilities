package com.encryptor;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.swing.text.AsyncBoxView.ChildLocator;

import org.apache.commons.codec.binary.Base64;

public class EncryptionEngine {

	private static final String UNICODE_FORMAT = "UFT-8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	private static KeySpec ks;
	private static SecretKeyFactory skf;
	private static Cipher cipher;
	static byte[] arrayBytes;
	private static String myEncryptionKey;
	private static String myEncryptionScheme;
	static SecretKey key;
	
	
	public EncryptionEngine(){
		
		myEncryptionKey = "123456543234567765435566556632298899";
		myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		try {
			arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
			ks = new DESedeKeySpec(arrayBytes);
			skf = SecretKeyFactory.getInstance(myEncryptionScheme);
			cipher = Cipher.getInstance(myEncryptionScheme);
			key = skf.generateSecret(ks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// method name: encrypt
	// purpose: use this method to encrypt any text(String)
	// created: Mar 09, 2019
	// Author: Sourabh Mondal
	// Input Required: String input
	// Return type: String (encrypted text)
	
	public String encrypt(String needsToEncryptString){
		String encryptedString = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = needsToEncryptString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64(encryptedText));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
	
	
	// method name: encrypt
	// purpose: use this method to decrypt the encrypted text(String)
	// created: Mar 09, 2019
	// Author: Sourabh Mondal
	// Input Required: String input (encrypted text)
	// Return type: String (decrypt text)
	
	public String decrypt(String encryptedString){
		String decryptedText = null;
		myEncryptionKey = "123456543234567765435566556632298899";
		myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		try {
			arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
			ks = new DESedeKeySpec(arrayBytes);
			skf = SecretKeyFactory.getInstance(myEncryptionScheme);
			key = skf.generateSecret(ks);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedtxt = encryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = Base64.decodeBase64(encryptedtxt);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = new String(plainText);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}
	
	// method name: Main class to test the encryption mechanism
	// purpose: use this method to test the epcryption mechanism
	// created: Mar 09, 2019
	// Author: Sourabh Mondal
	
	public static void main(String[] args) {
		EncryptionEngine encryptionEngine = new EncryptionEngine();
		String encrytedText = encryptionEngine.encrypt("Sourabh");
		System.out.println("Encrypted Text == "+encrytedText);
		
		String decryptedText = encryptionEngine.decrypt(encrytedText);
		System.out.println("Decrypted Text == "+decryptedText);
	}
}
