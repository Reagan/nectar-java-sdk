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
import java.util.HashMap;
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

    public Token generateElectricityToken(Instant tokenId, double amount,
                                          int randomNo, boolean isStid,
                                          String drn, String configRef,
                                          boolean debug)
        throws NoSuchAlgorithmException, InvalidKeyException,
                IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "0");
        params.put("subclass", "0");
        params.put("token_id", tokenId.toString());
        params.put("amount", amount);
        params.put("random_no", randomNo);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }

    public Token generateWaterToken(Instant tokenId, double amount,
                                    int randomNo, boolean isStid,
                                    String drn, String configRef,
                                    boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "0");
        params.put("subclass", "1");
        params.put("token_id", tokenId.toString());
        params.put("amount", amount);
        params.put("random_no", randomNo);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }

    public Token generateGasToken(Instant tokenId, double amount,
                               int randomNo, boolean isStid,
                               String drn, String configRef, boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "0");
        params.put("subclass", "2");
        params.put("token_id", tokenId.toString());
        params.put("amount", amount);
        params.put("random_no", randomNo);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }

    public Token generateInitiateMeterTestDisplay10Token(Instant tokenId, String control,
                                                         int manufacturerCode, boolean isStid,
                                                         String drn, String configRef,
                                                         boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "1");
        params.put("subclass", "0");
        params.put("token_id", tokenId.toString());
        params.put("control", control);
        params.put("manufacturer_code", manufacturerCode);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }

    public Token generateInitiateMeterTestDisplay11Token(Instant tokenId, String control,
                                                         int manufacturerCode, boolean isStid,
                                                         String drn, String configRef,
                                                         boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "1");
        params.put("subclass", "1");
        params.put("token_id", tokenId.toString());
        params.put("control", control);
        params.put("manufacturer_code", manufacturerCode);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }

    public Token generateSetMaximumPowerLimitToken(Instant tokenId, int maximumPowerLimit,
                                                   int randomNo, boolean isStid,
                                                   String drn, String configRef,
                                                   boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "2");
        params.put("subclass", "0");
        params.put("token_id", tokenId.toString());
        params.put("maximum_power_limit", maximumPowerLimit);
        params.put("random_no", randomNo);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }

    public Token generateClearCreditToken(Instant tokenId, int register,
                                          int randomNo, boolean isStid,
                                          String drn, String configRef,
                                          boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "2");
        params.put("subclass", "1");
        params.put("token_id", tokenId.toString());
        params.put("register", register);
        params.put("random_no", randomNo);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }

    public Token generateSetTariffRateToken(Instant tokenId, int tariffRate,
                                            int randomNo, boolean isStid,
                                            String drn, String configRef,
                                            boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "2");
        params.put("subclass", "2");
        params.put("token_id", tokenId.toString());
        params.put("tariff_rate", tariffRate);
        params.put("random_no", randomNo);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }

    public Token generateSet1stSectionDecoderKeyToken(Instant tokenId, String newVendingKey,
                                                      String newSupplyGroupCode, String newTariffIndex,
                                                      int newKeyRevisionNo, int newKeyType, int newKeyExpiryNo,
                                                      String newDrn, String newIssuerIdentificationNo, int ro,
                                                      boolean isStid, String drn, String configRef,
                                                      boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "2");
        params.put("subclass", "3");
        params.put("token_id", tokenId.toString());
        params.put("new_vending_key", newVendingKey);
        params.put("new_supply_group_code", newSupplyGroupCode);
        params.put("new_tariff_index", newTariffIndex);
        params.put("new_key_revision_no", newKeyRevisionNo);
        params.put("new_key_type", newKeyType);
        params.put("new_key_expiry_no", newKeyExpiryNo);
        params.put("new_drn", newDrn);
        params.put("new_issuer_identification_no", newIssuerIdentificationNo);
        params.put("ro", ro);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }


    public Token generateSet2ndSectionDecoderKeyToken(Instant tokenId, String newVendingKey,
                                                      String newSupplyGroupCode, String newTariffIndex,
                                                      int newKeyRevisionNo, int newKeyType, int newKeyExpiryNo,
                                                      String newDrn, String newIssuerIdentificationNo, int ro,
                                                      boolean isStid, String drn, String configRef,
                                                      boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "2");
        params.put("subclass", "4");
        params.put("token_id", tokenId.toString());
        params.put("new_vending_key", newVendingKey);
        params.put("new_supply_group_code", newSupplyGroupCode);
        params.put("new_tariff_index", newTariffIndex);
        params.put("new_key_revision_no", newKeyRevisionNo);
        params.put("new_key_type", newKeyType);
        params.put("new_key_expiry_no", newKeyExpiryNo);
        params.put("new_drn", newDrn);
        params.put("new_issuer_identification_no", newIssuerIdentificationNo);
        params.put("ro", ro);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }


    public Token generateClearTamperConditionToken(Instant tokenId, int pad,
                                                   int randomNo, boolean isStid,
                                                   String drn, String configRef,
                                                   boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "2");
        params.put("subclass", "5");
        params.put("token_id", tokenId.toString());
        params.put("pad", pad);
        params.put("random_no", randomNo);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }

    public Token generateSetMaximumPhasePowerUnbalanceLimitToken(Instant tokenId,
                                                                 int mppul, int randomNo,
                                                                 boolean isStid, String drn,
                                                                 String configRef, boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "2");
        params.put("subclass", "6");
        params.put("token_id", tokenId.toString());
        params.put("mppul", mppul);
        params.put("random_no", randomNo);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
    }

    public Token generateSetWaterMeterFactorToken(Instant tokenId, int wmFactor,
                                                  int randomNo, boolean isStid,
                                                  String drn, String configRef,
                                                  boolean debug)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "2");
        params.put("subclass", "7");
        params.put("token_id", tokenId.toString());
        params.put("wm_factor", wmFactor);
        params.put("random_no", randomNo);
        params.put("is_stid", isStid);
        params.put("drn", drn);
        params.put("config_ref", configRef);
        params.put("debug", debug);
        return generateToken(params);
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

    private Token generateToken(Map<String, Object> params)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        return extractFrom(post(TOKEN_PATH, new Payload(params), JSON_CONTENT_TYPE));
    }
}
