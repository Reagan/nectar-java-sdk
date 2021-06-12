package software.nectar.java.factory;

import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.models.Credentials;

public class CredentialsFactory extends BaseFactory {

    public CredentialsFactory(String key, String secret) {
        super(key, secret);
    }

    public Credentials getCredentials() {
        return null;
    }

    public void activateCredentials() {

    }

    public void deactivateCredentials() {

    }


}
