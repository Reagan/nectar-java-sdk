package software.nectar.java.models;

import java.util.List;

public class Credentials {
    private String key;
    private String secret;
    private String ref;
    private boolean activated;
    private User user;
    private List<Permissions> permissions;

    public Credentials() {}

    public Credentials(String key, String secret, String ref,
                       boolean activated, User user, List<Permissions> permissions) {
        setKey(key);
        setSecret(secret);
        setRef(ref);
        setActivated(activated);
        setUser(user);
        setPermissions(permissions);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return String.format("Credentials { key: %s, secret; %s, ref: %s, " +
                "activated: %b, \n\t%s, Permissions: [ %s ] }\n",
                key, secret, ref, activated, user, permissions);
    }
}
