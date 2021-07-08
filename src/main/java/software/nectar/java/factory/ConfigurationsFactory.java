package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.Configuration;
import software.nectar.java.utils.AsymmetricEncryptUtils;
import software.nectar.java.utils.Payload;
import software.nectar.java.utils.SymmetricEncryptUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationsFactory extends BaseFactory<Configuration> {

    private final String CONFIGURATIONS_PATH = "/v1/configurations";
    final String NECTAR_PUBLIC_KEY = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAvgJOsQeQ63PT/hu50eKqz7gRTe6X9re+Hy5vQdAbna4cbuzmxQ5scBYBW/tDXVoS40MRQYdqdoxJValoAT76U/xgV0yTlAYihU13Mvh61ZUyOU/QBD+w3LI/1mHQhMW6sQJ3TzSEKfH+6xzTsRktTywXQqHAMgtvhq+XO+b+7jBi4NRxZWgYhSMuGetdLh9rG/GUcyCOO8Oqix6paX5wMKsGUXVbA5VTsmycTsaHgI7NQKNnY46BmWcZB1XMnx+9PY7FgDJ7bsDIsfKtQj9sEIpnuD2leU5ZNJM/VBkJ3JfsBZ/UV3Q0kWbcPyoXhl0a4R2ywyA94BN6FSYrqq1NtdsxUATSHYu0dc/kwyTKAWVaatcLzmLRKF/Xacle0Kwkn1rGakwu1wB0YTIjUMsFGOlbffnAUOB5GmvYDWmliax2p5sG6Ar7NUABISZEwiw1SwHXhGdFxVHlcOna5LBZ9xvMfJM4L7vESKmvy5ChL/IoszIkOgPE5XUOox32DOsE+SU4Gjf5kwCFWRNPokB/bIssty3/4y/7FnNgGJsyBwSgq+/rAwHBFjwawVHbqWzhMiuEulEvukk2CjcbdgYW5az/Ikc/BBsxUlKJkWa0m7wlmMxbXngPpOmFg4qfDtqMflVfPw48Rfx2XOttXjFvFpkxrYVOzg9nP0llktJNpe8CAwEAAQ==";

    public ConfigurationsFactory(String key, String secret) {
        super(key, secret);
    }

    public Configuration createConfiguration(String yamlConfig,
                                             String symmetricKey,
                                             String privateKey)
            throws Exception {
        return extractFrom(post(CONFIGURATIONS_PATH,
                new Payload(encryptParams(yamlConfig, symmetricKey, privateKey)), JSON_CONTENT_TYPE));
    }

    public Configuration getConfiguration(String ref, boolean detailed)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        return extractFrom(get(CONFIGURATIONS_PATH, String.format("ref=%s&detailed=%b", ref, detailed),
                JSON_CONTENT_TYPE));
    }

    public void activateConfiguration(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        put(CONFIGURATIONS_PATH, String.format("ref=%s", ref), null, JSON_CONTENT_TYPE);
    }

    public void deactivateConfiguration(String ref)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        delete(CONFIGURATIONS_PATH, String.format("ref=%s", ref), JSON_CONTENT_TYPE);
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

    private Map<String, Object> encryptParams(String yamlConfig,
                                              String symmetricKey,
                                              String privateKey)
            throws Exception {
        byte[] cipherTextAfterMockUserSymmetricKeyEncryption = SymmetricEncryptUtils.encrypt(yamlConfig.getBytes(), symmetricKey);
        String cipherTextAfterMockUserSymmetricKeyEncryptionBase64 = AsymmetricEncryptUtils.encodeBASE64(cipherTextAfterMockUserSymmetricKeyEncryption);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(yamlConfig.getBytes());
        byte[] digest = md.digest();


        byte[] digestAfterMockUserPrivateKeyEncryption = AsymmetricEncryptUtils.encrypt(digest, AsymmetricEncryptUtils.getPrivateKeyFromString(privateKey));
        String digestAfterMockUserPrivateKeyEncryptionBase64 = AsymmetricEncryptUtils.encodeBASE64(digestAfterMockUserPrivateKeyEncryption);
        byte[] symmetricKeyAfterNectarPublicKeyEncryption = AsymmetricEncryptUtils.encrypt(symmetricKey.getBytes(), AsymmetricEncryptUtils.getPublicKeyFromString(NECTAR_PUBLIC_KEY));
        String symmetricKeyAfterNectarPublicKeyEncryptionBase64 = AsymmetricEncryptUtils.encodeBASE64(symmetricKeyAfterNectarPublicKeyEncryption);

        Map<String, Object> configParams = new HashMap<>();
        configParams.put("data", cipherTextAfterMockUserSymmetricKeyEncryptionBase64);
        configParams.put("digest", digestAfterMockUserPrivateKeyEncryptionBase64);
        configParams.put("key", symmetricKeyAfterNectarPublicKeyEncryptionBase64);
        return configParams;
    }
}
