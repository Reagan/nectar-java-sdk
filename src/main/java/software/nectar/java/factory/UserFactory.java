package software.nectar.java.factory;

import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.models.User;

public class UserFactory extends BaseFactory {

    public UserFactory(String key, String secret) {
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
}
