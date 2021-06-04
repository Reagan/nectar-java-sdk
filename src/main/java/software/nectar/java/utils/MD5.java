package software.nectar.java.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public String md5(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuffer buffer = new StringBuffer();
        for (byte b : digest) {
            String st = String.format("%02X", b);
            buffer.append(st);
        }
        return buffer.toString().toUpperCase();
    }
}
