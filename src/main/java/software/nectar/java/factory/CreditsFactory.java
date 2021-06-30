package software.nectar.java.factory;

import org.json.JSONArray;
import org.json.JSONObject;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.Credits;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CreditsFactory extends BaseFactory<Credits> {

    private final String CREDITS_PATH = "/v1/credits";
    private final String TRANSACTIONS_PATH = "/v1/transactions";

    public CreditsFactory(String key, String secret) {
        super(key, secret);
    }

    public Credits getCredits()
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        return get(CREDITS_PATH, "", JSON_CONTENT_TYPE);
    }

    public Credits getTransactions()
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        return get(TRANSACTIONS_PATH, "", JSON_CONTENT_TYPE);
    }

    public List<Credits> extractMultipleFrom(JSONObject responseObj)
            throws ApiResponseException {
        return null;
    }

    public Credits extractFrom(JSONObject responseObj)
        throws ApiResponseException{
        if (responseObj.getJSONObject("status").getInt("code") == 200) {
            JSONObject credits = responseObj.getJSONObject("data").getJSONObject("data");
            return new Credits(((BigDecimal) credits.get("credits")).doubleValue(),
                    extractPurchases(credits),
                    extractConsumption(credits));
        }
        throw new ApiResponseException(responseObj.getJSONObject("status").getString("message"));
    }

    private List<Credits.Purchase> extractPurchases(JSONObject credits) {
        List<Credits.Purchase> extractedPurchases = new ArrayList<>();
        if (credits.has("purchases")) {
            JSONArray purchases = (JSONArray) credits.get("purchases");
            for (Object purchase : purchases) {
                extractedPurchases.add(new Credits.Purchase(
                        (String) ((JSONObject) purchase).get("ref"),
                        (String) ((JSONObject) purchase).get("user_ref"),
                        ((BigDecimal)((JSONObject) purchase).get("value")).doubleValue(),
                        ((BigDecimal)((JSONObject) purchase).get("units")).doubleValue(),
                        (String) ((JSONObject) purchase).get("currency"),
                        Instant.parse((String) ((JSONObject) purchase).get("purchase_date"))
                ));
            }
        }
        return extractedPurchases;
    }

    private List<Credits.Consumption> extractConsumption(JSONObject credits) {
        List<Credits.Consumption> extractedConsumptions = new ArrayList<>();
        if (credits.has("consumptions")) {
            JSONArray consumptions = (JSONArray) credits.get("consumptions");
            consumptions.forEach(consumption -> {
                extractedConsumptions.add(new Credits.Consumption(
                        (String) ((JSONObject) consumption).get("ref"),
                        ((BigDecimal)((JSONObject) consumption).get("units")).doubleValue(),
                        Instant.parse((String) ((JSONObject) consumption).get("consumption_date")),
                        (String) ((JSONObject) consumption).get("user_ref"),
                        (String) ((JSONObject) consumption).get("token_ref")
                ));
            });
        }
        return extractedConsumptions;
    }
}
