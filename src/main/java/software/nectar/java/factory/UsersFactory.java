package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.ApiResponse;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.models.User;

public class UsersFactory extends BaseFactory {

    public UsersFactory(String key, String secret) {
        super(key, secret);
    }

    public User createUser() {
        return null;
    }

    public User getUser() {
        return null;
    }

    public User updateUser() {
        return null;
    }

    public void deleteUser() {

    }

    public ApiResponse extractFrom(JSONObject object) {
        return null;
    }
}
