package software.nectar.java.factory;

import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.models.PublicKey;

import java.util.List;

public class PublicKeyFactory extends BaseFactory {

    public PublicKeyFactory(String key, String secret) {
        super(key, secret);
    }

    public List<PublicKey> getPublicKeys() {
        return null;
    }

    public PublicKey createPublicKey() {
        return null;
    }

    public void activatePublicKey() {

    }

    public void deactivatePublicKey() {

    }
}
