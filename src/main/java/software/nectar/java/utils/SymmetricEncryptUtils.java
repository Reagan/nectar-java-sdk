package software.nectar.java.utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.Charset;
import java.security.Security;
import java.util.Random;

public class SymmetricEncryptUtils {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final int AES_NIVBITS = 128; // CBC Initialization Vector (same as cipher block size) [16 bytes]

    private static void init() {
        Security.addProvider(new BouncyCastleProvider());
        Security.setProperty("crypto.policy", "unlimited");
    }

    public static String encrypt(String data, String key)
            throws Exception {
        byte[] bytesData = data.getBytes(UTF8);
        byte[] bytesAll = encrypt(bytesData, key);
        return new String(Base64.encodeBase64(bytesAll), UTF8);
    }

    public static byte[] encrypt(byte[] bytesData, String key)
            throws Exception {

        init();

        byte[] ivData = new byte[AES_NIVBITS / 8];
        Random r = new Random();
        r.nextBytes(ivData);

        BlockCipherPadding padding = new PKCS7Padding();
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()), padding);

        KeyParameter keyParam = new KeyParameter(key.getBytes());
        CipherParameters params = new ParametersWithIV(keyParam, ivData);

        cipher.reset();
        cipher.init(true, params);

        int buflen = cipher.getOutputSize(bytesData.length);
        byte[] bytesEnc = new byte[buflen];
        int nBytesEnc = cipher.processBytes(bytesData, 0, bytesData.length, bytesEnc, 0);
        nBytesEnc += cipher.doFinal(bytesEnc, nBytesEnc);

        if (nBytesEnc != bytesEnc.length) {
            throw new IllegalStateException("Unexpected behaviour : getOutputSize value incorrect");
        }

        byte[] bytesAll = new byte[ivData.length + bytesEnc.length];
        System.arraycopy(ivData, 0, bytesAll, 0, ivData.length);
        System.arraycopy(bytesEnc, 0, bytesAll, ivData.length, bytesEnc.length);

        return bytesAll;
    }

    public static String decrypt(String enc, String key)
            throws Exception {

        byte[] bytesEnc = Base64.decodeBase64(enc.getBytes(UTF8));
        byte[] bytesDec = decrypt(bytesEnc, key.getBytes());
        return new String(bytesDec, UTF8);
    }

    public static byte[] decrypt(byte[] bytesEnc, byte[] keyBytes)
            throws Exception {

        init();

        int nIvBytes = AES_NIVBITS / 8;
        byte[] ivBytes = new byte[nIvBytes];
        System.arraycopy(bytesEnc, 0, ivBytes, 0, nIvBytes);

        KeyParameter keyParam = new KeyParameter(keyBytes);
        CipherParameters params = new ParametersWithIV(keyParam, ivBytes);
        BlockCipherPadding padding = new PKCS7Padding();
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()), padding);

        cipher.reset();
        cipher.init(false, params);

        byte[] bytesDec;

        int buflen = cipher.getOutputSize(bytesEnc.length - nIvBytes);
        byte[] workingBuffer = new byte[buflen];
        int len = cipher.processBytes(bytesEnc, nIvBytes, bytesEnc.length - nIvBytes, workingBuffer, 0);
        len += cipher.doFinal(workingBuffer, len);

        bytesDec = new byte[len];
        System.arraycopy(workingBuffer, 0, bytesDec, 0, len);

        return bytesDec;
    }
}
