package software.nectar.java;

import software.nectar.java.factory.TokenFactory;

public class Nectar {

    private String key;
    private String secret;

    public Nectar(String key, String secret) {
        setKey(key);
        setSecret(secret);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String  getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public TokenFactory getTokenFactory() {
        return new TokenFactory(key, secret);
    }
}
