package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.Token;
import software.nectar.java.utils.Payload;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class TokensFactory extends BaseFactory<Token> {

    private final String TOKEN_PATH = "/v1/tokens";

    public TokensFactory(String key, String secret) {
        super(key, secret);
    }

    public Token getToken(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        String path = String.format("ref=%s", ref);
        return extractFrom(get(TOKEN_PATH, path, JSON_CONTENT_TYPE));
    }

    public Token generateToken(Map<String, Object> params)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        return extractFrom(post(TOKEN_PATH, new Payload(params), JSON_CONTENT_TYPE));
    }

    public List<Token> extractMultipleFrom(JSONObject responseObj)
            throws ApiResponseException {
        return null;
    }

    public Token extractFrom(JSONObject responseObj)
        throws ApiResponseException {
        if (responseObj.getJSONObject("status").getInt("code") == 200) {
            JSONObject token = responseObj.getJSONObject("data").getJSONObject("data");
            return new Token((String) token.get("ref"),
                    (String) token.get("token_no"),
                    (String) token.get("user_ref"),
                    (String) token.get("token_type"),
                    (String) token.get("meter_no"),
                    Instant.parse((String) token.get("created_at")));
        }
        throw new ApiResponseException(responseObj.getJSONObject("status").getString("message"));
    }
}
