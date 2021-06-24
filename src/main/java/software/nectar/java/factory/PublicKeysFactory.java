package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.PublicKey;
import software.nectar.java.utils.Payload;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class PublicKeysFactory extends BaseFactory<PublicKey> {

    private final String PUBLIC_KEYS_PATH = "/v1/public_keys";

    public PublicKeysFactory(String key, String secret) {
        super(key, secret);
    }

    public List<PublicKey> getPublicKeys(boolean activated)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        String path = String.format("activated=%b", activated);
        return (List<PublicKey>) get(PUBLIC_KEYS_PATH, path, JSON_CONTENT_TYPE);
    }

    public PublicKey createPublicKey(Map<String, Object> params)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        return post(PUBLIC_KEYS_PATH, new Payload(params), JSON_CONTENT_TYPE);
    }

    public void activatePublicKey(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        put(String.format("%s?ref=%s", PUBLIC_KEYS_PATH, ref), null, JSON_CONTENT_TYPE);
    }

    public void deactivatePublicKey(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        delete(String.format("%s?ref=%s", PUBLIC_KEYS_PATH, ref), null, JSON_CONTENT_TYPE);
    }

    public PublicKey extractFrom(JSONObject responseObj)
        throws ApiResponseException {
        if (responseObj.getJSONObject("status").getInt("code") == 200) {
            JSONObject token = responseObj.getJSONObject("data").getJSONObject("data");
            return new PublicKey((String) token.get("key"),
                    (String) token.get("user_ref"),
                    (Boolean) token.get("activated"),
                    (String) token.get("ref"),
                    Instant.parse((String) token.get("created_at")),
                    Instant.parse((String) token.get("updated_at")));
        }
        throw new ApiResponseException(responseObj.getJSONObject("status").getString("message"));
    }
}
