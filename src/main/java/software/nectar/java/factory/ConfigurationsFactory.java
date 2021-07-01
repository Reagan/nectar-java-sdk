package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.Configuration;
import software.nectar.java.utils.Payload;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class ConfigurationsFactory extends BaseFactory<Configuration> {

    private final String CONFIGURATIONS_PATH = "/v1/configurations";

    public ConfigurationsFactory(String key, String secret) {
        super(key, secret);
    }

    public Configuration createConfiguration(Map<String, Object> params)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        return post(CONFIGURATIONS_PATH, new Payload(params), JSON_CONTENT_TYPE);
    }

    public Configuration getConfigurations(String ref, boolean detailed)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        return get(CONFIGURATIONS_PATH, String.format("ref=%s&detailed=%b", ref, detailed),
                JSON_CONTENT_TYPE);
    }

    public void activateConfiguration(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        put(String.format("%s?ref=%s", CONFIGURATIONS_PATH, ref), null, JSON_CONTENT_TYPE);
    }

    public void deactivateConfiguration(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        delete(String.format("%s?ref=%s", CONFIGURATIONS_PATH, ref), null, JSON_CONTENT_TYPE);
    }

    public List<Configuration> extractMultipleFrom(JSONObject responseObj)
            throws ApiResponseException {
        return null;
    }

    public Configuration extractFrom(JSONObject responseObj)
        throws ApiResponseException {
        if (responseObj.getJSONObject("status").getInt("code") == 200) {
            JSONObject configuration = responseObj.getJSONObject("data").getJSONObject("data");
            return new Configuration((String) configuration.get("name"),
                    (String) configuration.get("user_ref"),
                    (Boolean) configuration.get("activated"),
                    (String) configuration.get("ref"),
                    Instant.parse((String) configuration.get("created_at")));
        }
        throw new ApiResponseException(responseObj.getJSONObject("status").getString("message"));
    }
}
