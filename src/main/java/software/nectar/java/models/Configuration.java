package software.nectar.java.models;

import java.time.Instant;

public class Configuration {

    private String userRef;
    private boolean activated;
    private String ref;
    private Instant createdAt;

    public Configuration(String userRef, boolean activated,
                         String ref, Instant createdAt) {
        setUserRef(userRef);
        setActivated(activated);
        setRef(ref);
        setCreatedAt(createdAt);
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
}
