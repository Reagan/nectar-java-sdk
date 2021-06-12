package software.nectar.java.factory;

import software.nectar.java.factory.base.ApiResponse;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.Token;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static software.nectar.java.utils.DateTimeUtils.convert;

public class TokenFactory extends BaseFactory {

    private final String TOKEN_PATH = "/v1/token";

    public TokenFactory(String key, String secret) {
        super(key, secret);
    }

    public Token getToken(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        String path = String.format("ref=%s", ref);
        ApiResponse response = super.get(TOKEN_PATH, path, CONTENT_TYPE);

        if (response.getStatus().getCode() == 200) {
            return new Token((String) response.getData().get("ref"),
                    (String) response.getData().get("token_no"),
                    (String) response.getData().get("user_ref"),
                    (String) response.getData().get("token_type"),
                    (String) response.getData().get("meter_no"),
                    convert((String) response.getData().get("created_at")));
        } else {
            throw new ApiResponseException(response.getStatus().getMessage());
        }
    }

    public Token generateToken() {
        return null;
    }

    public void deleteToken() {

    }
}
