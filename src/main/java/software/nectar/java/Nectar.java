package software.nectar.java;

import software.nectar.java.factory.*;

/**
 * This is the entry point to the Nectar API Java SDK. Implementations of the Nectar Java SDK must iintialize this class.
 *
 * @author Reagan Mbitiru (reagan@nectar.software)
 *
 */
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

    public TokensFactory getTokenFactory() {
        return new TokensFactory(key, secret);
    }

    public UsersFactory getUsersFactory() {
        return new UsersFactory(key, secret);
    }

    public PublicKeysFactory getPublicKeysFactory() {
        return new PublicKeysFactory(key, secret);
    }

    public NotificationsFactory getNotificationsFactory() {
        return new NotificationsFactory(key, secret);
    }

    public CreditsFactory getCreditsFactory() {
        return new CreditsFactory(key, secret);
    }

    public CredentialsFactory getCredentialsFactory() {
        return new CredentialsFactory(key, secret);
    }

    public ConfigurationsFactory getConfigurationsFactory() {
        return new ConfigurationsFactory(key, secret);
    }
}
