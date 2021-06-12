package software.nectar.java.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Authentication {

    public String generateHMAC(String secret, String method, String path,
                                String md5, String contentType, String date,
                                String  nonce) throws NoSuchAlgorithmException, InvalidKeyException {
        final String str = String.format("%s%s%s%s%s%s", method, path, md5, contentType, date, nonce);
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256Hmac.init(secretKey);
        String hashed = new BigInteger(1, sha256Hmac.doFinal(str.getBytes(StandardCharsets.UTF_8))).toString(16);
        return Base64.getEncoder().encodeToString(hashed.getBytes(StandardCharsets.UTF_8));
    }
}
