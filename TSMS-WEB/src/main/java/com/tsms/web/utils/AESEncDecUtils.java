package com.tsms.web.utils;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncDecUtils {
	private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final int AES_KEY_SIZE = 256;

	public static SecretKey generateSecretKey() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(AES_KEY_SIZE);
		return keyGen.generateKey();
	}

	public static IvParameterSpec generateIv() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	public static String encrypt(String input, SecretKey key, IvParameterSpec iv) throws Exception {
		Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] cipherText = cipher.doFinal(input.getBytes());
		return Base64.getEncoder().encodeToString(cipherText);
	}

	public static String decrypt(String cipherText, SecretKey key, IvParameterSpec iv)
			throws Exception {
		Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}
	
    public static String generateAESPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
    
    public static String encodeSecretKey(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    
    public static SecretKey decodeSecretKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
    
    public static String encodeIv(IvParameterSpec iv) {
        return Base64.getEncoder().encodeToString(iv.getIV());
    }
    
    public static IvParameterSpec decodeIv(String encodedIv) {
        byte[] decodedIv = Base64.getDecoder().decode(encodedIv);
        return new IvParameterSpec(decodedIv);
    }
public static void main(String[] args) throws Exception {
	//Q5P1TZc1WRTn0X7A2gpf1w== n0R1Yqg6Gg0WIYKNs/G9GHWq6d9hdHGpf9xYtKaQ7oQ= 0vBuw7LSwDC8wNj+U9E0Lg==
	SecretKey decodeSecretKey=decodeSecretKey("n0R1Yqg6Gg0WIYKNs/G9GHWq6d9hdHGpf9xYtKaQ7oQ=");
	IvParameterSpec decodeIv= decodeIv("0vBuw7LSwDC8wNj+U9E0Lg==");
	String s =decrypt("Q5P1TZc1WRTn0X7A2gpf1w==",decodeSecretKey, decodeIv);
}
}
