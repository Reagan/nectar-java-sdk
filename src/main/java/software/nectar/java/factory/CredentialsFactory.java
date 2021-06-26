package software.nectar.java.factory;

import org.json.JSONArray;
import org.json.JSONObject;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.Credentials;
import software.nectar.java.models.Permissions;
import software.nectar.java.models.User;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CredentialsFactory extends BaseFactory<Credentials> {

    private final String CREDENTIALS_PATH = "/v1/credentials";

    public CredentialsFactory(String key, String secret) {
        super(key, secret);
    }

    public Credentials getCredentials(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException  {
        return get(CREDENTIALS_PATH, String.format("ref=%s", ref), JSON_CONTENT_TYPE);
    }

    public void activateCredentials(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        put(String.format("%s?ref=%s", CREDENTIALS_PATH, ref), null, JSON_CONTENT_TYPE);
    }

    public void deactivateCredentials(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        delete(String.format("%s?ref=%s", CREDENTIALS_PATH, ref), null, JSON_CONTENT_TYPE);
    }

    public List<Credentials> extractMultipleFrom(JSONObject responseObj)
            throws ApiResponseException {
        return null;
    }

    public Credentials extractFrom(JSONObject responseObj)
        throws ApiResponseException {
        if (responseObj.getJSONObject("status").getInt("code") == 200) {
            JSONObject credentials = responseObj.getJSONObject("data").getJSONObject("data");
            return new Credentials((String) credentials.get("key"),
                    (String) credentials.get("secret"),
                    (String) credentials.get("ref"),
                    (Boolean) credentials.get("activated"),
                    extractUser((JSONObject) credentials.get("user")),
                    extractPermissions((JSONArray) credentials.get("permissions")));
        }
        throw new ApiResponseException(responseObj.getJSONObject("status").getString("message"));
    }

    private User extractUser(JSONObject user) {
        return new User((String) user.get("first_name"),
                (String) user.get("last_name"),
                (String) user.get("username"),
                "", // empty password
                (String) user.get("phone_no"),
                (String) user.get("image_url"),
                (String) user.get("ref"),
                (String) user.get("email"),
                (Boolean) user.get("activated"),
                Instant.parse((String) user.get("created_at")));
    }

    private List<Permissions> extractPermissions(JSONArray permissions) {
        ArrayList<Permissions> generated = new ArrayList<>();
        for (Object currPermission : permissions) {
            Permissions p = new Permissions();
            p.setName((String) ((JSONObject) currPermission).get("name"));
            p.setIdentifier((String) ((JSONObject) currPermission).get("identifier"));
            p.setRef((String) ((JSONObject) currPermission).get("ref"));
            p.setNotes((String) ((JSONObject) currPermission).get("notes"));
            generated.add(p);
        }
        return generated;
    }

}
