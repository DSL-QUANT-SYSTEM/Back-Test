package com.example.cap2024.handler.SecureHandler;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SecureObject {
    private final byte[] key;

    public SecureObject() {
        key = new byte[]{'H', 'O', 'S', 'E', 'O', 'N', 'G', '!'};
    }

    public String GetEncryptString(String targetString) {
        return GetEncryptString(key, targetString);
    }

    private String GetEncryptString(byte[] key, String targetString) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(targetString.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String GetDecryptString(String targetString) {
        return GetDecryptString(key, targetString);
    }

    private String GetDecryptString(byte[] key, String targetString) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key));
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(targetString);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        SecureObject secureObject = new SecureObject();
        String originalString = "Hello, World!";
        String encryptedString = secureObject.GetEncryptString(originalString);
        String decryptedString = secureObject.GetDecryptString(encryptedString);

        System.out.println("Original String: " + originalString);
        System.out.println("Encrypted String: " + encryptedString);
        System.out.println("Decrypted String: " + decryptedString);
    }
}
