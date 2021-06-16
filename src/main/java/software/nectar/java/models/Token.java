package software.nectar.java.models;

import java.time.Instant;

public class Token {

    private String ref;
    private String tokenNo;
    private String userRef;
    private String tokenType;
    private String meterNo;
    private Instant createdAt;

    public Token(String ref, String tokenNo, String userRef,
                 String tokenType, String meterNo, Instant createdAt) {
        setRef(ref);
        setTokenNo(tokenNo);
        setUserRef(userRef);
        setTokenType(tokenType);
        setMeterNo(meterNo);
        setCreatedAt(createdAt);
    }

    @Override
    public String toString() {
        return String.format("Token { ref: %s, token_no: %s, user_ref: %s," +
                "token_type: %s, meter_no: %s, created_at: %s }",
                ref, tokenNo, userRef, tokenType, meterNo, createdAt.toString());
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
