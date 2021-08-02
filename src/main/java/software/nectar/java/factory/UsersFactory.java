package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.User;
import software.nectar.java.utils.Payload;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersFactory extends BaseFactory<User> {

    private final String USERS_PATH = "/v1/users";

    public UsersFactory(String key, String secret) {
        super(key, secret);
    }

    public String createUser(String firstName, String lastName, String username,
                             String password, String phoneNo, String imageUrl,
                             String email, boolean activated)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", firstName);
        params.put("last_name", lastName);
        params.put("username", username);
        params.put("password", password);
        params.put("phone_no", phoneNo);
        params.put("image_url", imageUrl);
        params.put("email", email);
        params.put("activated", activated);

        JSONObject resp = post(USERS_PATH, new Payload(params), JSON_CONTENT_TYPE);
        if ((Integer) ((JSONObject) resp.get("status")).get("code") == 200)
            return (String) ((JSONObject) resp.get("data")).get("user_ref");
        else throw new ApiResponseException(String.format("Status Code %d %s",
                ((JSONObject) resp.get("status")).get("code"),
                ((JSONObject) resp.get("status")).get("message")));
    }

    public User getUser()
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        return extractFrom(get(USERS_PATH, "", JSON_CONTENT_TYPE));
    }

    public void updateUser(String firstName, String lastName, String username,
                           String password, String phoneNo, String imageUrl,
                           String email, boolean activated)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException{
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", firstName);
        params.put("last_name", lastName);
        params.put("username", username);
        params.put("password", password);
        params.put("phone_no", phoneNo);
        params.put("image_url", imageUrl);
        params.put("email", email);
        params.put("activated", activated);

        JSONObject resp = put(USERS_PATH, "", new Payload(params),JSON_CONTENT_TYPE);
    }

    public void deleteUser()
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        delete(USERS_PATH, "", JSON_CONTENT_TYPE);
    }

    public List<User> extractMultipleFrom(JSONObject responseObj)
        throws ApiResponseException {
        return null;
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
