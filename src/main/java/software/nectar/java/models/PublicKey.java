package software.nectar.java.models;

import java.time.Instant;

public class PublicKey {
    private String key;
    private String userRef;
    private boolean activated;
    private String ref;
    private Instant createdAt;

    public PublicKey(String key, String userRef, boolean activated,
                     String ref, Instant createdAt) {
        setKey(key);
        setUserRef(userRef);
        setActivated(activated);
        setRef(ref);
        setCreatedAt(createdAt);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format("PublicKey { key: %s, user_ref: %s, activated: %b, " +
                "ref: %s, created_at: %s }\n", key, userRef,  activated,
                ref, createdAt.toString());
    }
}
