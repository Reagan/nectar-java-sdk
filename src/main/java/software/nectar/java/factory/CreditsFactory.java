package software.nectar.java.factory;

import org.json.JSONArray;
import org.json.JSONObject;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.Credits;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CreditsFactory extends BaseFactory<Credits> {

    private final String CREDITS_PATH = "/v1/credits";
    private final String TRANSACTIONS_PATH = "/v1/transactions";

    public CreditsFactory(String key, String secret) {
        super(key, secret);
    }

    public List<Credits> getCredits()
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        return (List<Credits>) get(CREDITS_PATH, "", JSON_CONTENT_TYPE);
    }

    public Credits getTransactions()
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        return get(CREDITS_PATH, "", JSON_CONTENT_TYPE);
    }

    public Credits extractFrom(JSONObject responseObj)
        throws ApiResponseException{
        if (responseObj.getJSONObject("status").getInt("code") == 200) {
            JSONObject credits = responseObj.getJSONObject("data").getJSONObject("data");
            return new Credits((Double) credits.get("credits"),
                    extractPurchases(credits),
                    extractConsumption(credits));
        }
        throw new ApiResponseException(responseObj.getJSONObject("status").getString("message"));
    }

    private List<Credits.Purchase> extractPurchases(JSONObject credits) {
        JSONArray purchases = (JSONArray) credits.get("purchase");
        List<Credits.Purchase> extractedPurchases = new ArrayList<>();
        for (Object purchase : purchases) {
            extractedPurchases.add(new Credits.Purchase(
                    (String) ((JSONObject) purchase).get("ref"),
                    (String) ((JSONObject) purchase).get("user_ref"),
                    (Double) ((JSONObject) purchase).get("value"),
                    (Double) ((JSONObject) purchase).get("units"),
                    (String) ((JSONObject) purchase).get("currency"),
                    Instant.parse((String) ((JSONObject) purchase).get("purchase_date"))
            ));
        }
        return extractedPurchases;
    }

    private List<Credits.Consumption> extractConsumption(JSONObject credits) {
        List<LinkedHashMap> consumptions = (List<LinkedHashMap>) credits.get("purchase");
        List<Credits.Consumption> extractedConsumptions = new ArrayList<>();
        consumptions.forEach(consumption -> {
            extractedConsumptions.add(new Credits.Consumption(
                    (String) consumption.get("ref"),
                    (Double) consumption.get("units"),
                    Instant.parse((String) consumption.get("consumption_date")),
                    (String) consumption.get("user_ref"),
                    (String) consumption.get("token_ref")
            ));
        });
        return extractedConsumptions;
    }
}
