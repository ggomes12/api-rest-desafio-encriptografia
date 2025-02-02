package com.ggomes.api_encryption.utils;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {
	
	private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;
    private static SecretKey secretKey;

    
    static {
        String storedKey = System.getenv("AES_SECRET_KEY");
        if (storedKey != null) {
            loadKeyFromBase64(storedKey);
        } else {
            secretKey = generateKey();
            System.out.println("Generated AES Key: " + getKeyAsBase64());
        }
    }
    
    
    private static SecretKey generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(KEY_SIZE, new SecureRandom());
            return keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Error generating AES key", e);
        }
    }
    
    
    public static String getKeyAsBase64() {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    
    public static void loadKeyFromBase64(String base64Key) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        secretKey = new SecretKeySpec(decodedKey, ALGORITHM);
    }
    
    
    public static String encrypt(String value) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }


    public static String decrypt(String encryptedValue) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedValue)));
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }

    
}
