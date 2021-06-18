package software.nectar.java.models;

import java.time.Instant;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNo;
    private String imageUrl;
    private String ref;
    private String email;
    private boolean activated;
    private Instant createdAt;

    public User(String firstName, String lastName, String username,
                String password, String phoneNo, String imageUrl,
                String ref, String email, boolean activated, Instant createdAt){
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setPhoneNo(phoneNo);
        setImageUrl(imageUrl);
        setRef(ref);
        setEmail(email);
        setActivated(activated);
        setCreatedAt(createdAt);
    }

    @Override
    public String toString() {
        return String.format("User { firstName: %s, lastName: %s, username: %s, " +
                "password: %s, phoneNo: %s, imageurl: %s, ref: %s, " +
                "email: %s, activated: %b, createdAt: %s }", getFirstName(), getLastName(),
                getUsername(), getPassword(), getPhoneNo(), getImageUrl(), ref, getEmail(), isActivated(),
                getCreatedAt());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
