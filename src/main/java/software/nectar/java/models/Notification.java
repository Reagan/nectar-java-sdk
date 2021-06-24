package software.nectar.java.models;

import java.time.Instant;

public class Notification {

    private String ref;
    private String subject;
    private String text;
    private String type;
    private String userRef;
    private String affected;
    private boolean read;
    private Instant readDate;
    private Instant createdDate;

    public Notification() {}

    public Notification(String ref, String subject, String text, String type,
                        String userRef, String affected, Boolean read,
                        Instant readDate, Instant createdDate) {
        setRef(ref);
        setSubject(subject);
        setText(text);
        setType(type);
        setUserRef(userRef);
        setAffected(affected);
        setRead(read);
        setReadDate(readDate);
        setCreatedDate(createdDate);
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    public String getAffected() {
        return affected;
    }

    public void setAffected(String affected) {
        this.affected = affected;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Instant getReadDate() {
        return readDate;
    }

    public void setReadDate(Instant readDate) {
        this.readDate = readDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
}
