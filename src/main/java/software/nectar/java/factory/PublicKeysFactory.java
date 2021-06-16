package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.ApiResponse;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.models.PublicKey;

import java.util.List;

public class PublicKeysFactory extends BaseFactory {

    public PublicKeysFactory(String key, String secret) {
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

    public ApiResponse extractFrom(JSONObject object) {
        return null;
    }
}
