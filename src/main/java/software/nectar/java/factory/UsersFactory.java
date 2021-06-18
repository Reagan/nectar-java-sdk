package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.User;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class UsersFactory extends BaseFactory<User> {

    private final String USERS_PATH = "/v1/users";

    public UsersFactory(String key, String secret) {
        super(key, secret);
    }

    public User createUser() {
        return null;
    }

    public User getUser()
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        return get(USERS_PATH, "", JSON_CONTENT_TYPE);
    }

    public User updateUser() {
        return null;
    }

    public void deleteUser() {

    }

    public User extractFrom(JSONObject responseObj)
        throws ApiResponseException {
        if (responseObj.getJSONObject("status").getInt("code") == 200) {
            JSONObject user = responseObj.getJSONObject("data").getJSONObject("data");
            return new User((String) user.get("first_name"),
                    (String) user.get("last_name"),
                    (String) user.get("username"),
                    "", // empty password
                    (String) user.get("phone_no"),
                    user.get("image_url") != JSONObject.NULL ? (String) user.get("image_url") : "",
                    (String) user.get("ref"),
                    (String) user.get("email"),
                    (Boolean) user.get("activated"),
                    Instant.parse((String) user.get("created_at")));
        }
        throw new ApiResponseException(responseObj.getJSONObject("status").getString("message"));
    }
}
