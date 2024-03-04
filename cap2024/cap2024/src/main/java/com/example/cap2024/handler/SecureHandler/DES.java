package com.example.cap2024.handler.SecureHandler;

import javax.crypto.Cipher;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DES {
    private final byte[] key;

    public DES(byte[] key) {
        this.key = key;
    }

    public String Encrypt(String text) {
        if (text == null || text.isEmpty() || key.length != 8) {
            return "";
        }

        try {
            Cipher cipher = Cipher.getInstance("DES");
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] inputBytes = text.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedBytes = cipher.doFinal(inputBytes);

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String Decrypt(String text) {
        if (text == null || text.isEmpty() || key.length != 8) {
            return "";
        }

        try {
            Cipher cipher = Cipher.getInstance("DES");
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key));
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] encryptedBytes = Base64.getDecoder().decode(text);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        byte[] key = {'H', 'O', 'S', 'E', 'O', 'N', 'G', '!'};
        DES des = new DES(key);

        String originalText = "Hello, World!";
        String encryptedText = des.Encrypt(originalText);
        String decryptedText = des.Decrypt(encryptedText);

        System.out.println("Original Text: " + originalText);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
