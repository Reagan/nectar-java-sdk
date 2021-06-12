package software.nectar.java.utils;

import java.util.Random;

public class Nonce {

    public String generateNonce() {
        final int NO_OF_CHARS = 32;
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < NO_OF_CHARS)
            sb.append(Integer.toHexString(r.nextInt()));
        return sb.toString().substring(0, NO_OF_CHARS);
    }
}
