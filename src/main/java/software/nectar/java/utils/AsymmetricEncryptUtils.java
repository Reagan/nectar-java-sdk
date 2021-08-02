package software.nectar.java.utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class AsymmetricEncryptUtils {

    protected static final String ALGORITHM = "RSA";

    public static void init() {
        Security.addProvider(new BouncyCastleProvider());
        Security.setProperty("crypto.policy", "unlimited");
    }

    public static String encrypt(String text, Key key) throws Exception {
        String encryptedText;
        byte[] cipherText = encrypt(text.getBytes("UTF8"), key);
        encryptedText = encodeBASE64(cipherText);
        return encryptedText;
    }

    public static byte[] encrypt(byte[] text, Key key) throws Exception {
        init();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text);
    }

    public static String decrypt(String text, Key key) throws Exception {
        String result;
        byte[] decryptedText = decrypt(decodeBASE64(text), key);
        result = new String(decryptedText, "UTF8");
        return result;
    }

    public static byte[] decrypt(byte[] text, Key key) throws Exception {
        init();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(text);
    }

    public static String getKeyAsString(Key key) {
        byte[] keyBytes = key.getEncoded();
        return encodeBASE64(keyBytes);
    }

    public static PrivateKey getPrivateKeyFromString(String key) throws Exception {
        init();
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodeBASE64(key));
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        return privateKey;
    }

    public static PublicKey getPublicKeyFromString(String key) throws Exception {
        init();
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodeBASE64(key));
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        return publicKey;
    }

    public static String encodeBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] decodeBASE64(String text) {
        return Base64.decodeBase64(text);
    }
}
