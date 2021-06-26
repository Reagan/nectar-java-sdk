package software.nectar.java.factory.base;

import org.json.JSONObject;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.utils.Authentication;
import software.nectar.java.utils.MD5;
import software.nectar.java.utils.Nonce;
import software.nectar.java.utils.Payload;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

abstract public class BaseFactory<T> {

    protected final String JSON_CONTENT_TYPE = "application/json";
    protected final String BASE_PATH = "http://localhost:2000";
    protected String key;
    protected String secret;
    protected enum Http {
        GET, POST, DELETE, PUT
    }

    public BaseFactory(String key, String secret) {
        setKey(key);
        setSecret(secret);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String  getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    protected Payload createPayload(HashMap<String, Object> params) {
        return new Payload(params);
    }

    protected String md5(String str) throws NoSuchAlgorithmException {
        return new MD5().md5(str);
    }

    protected String generateNonce() {
        return new Nonce().generateNonce();
    }

    protected String generateHMACAuth(String secret, Http verb, String urlPath, String md5, String contentType,
                                      Date date, String nonce)
            throws InvalidKeyException, NoSuchAlgorithmException  {
        return new Authentication().generateHMAC(secret, verb.name(), urlPath, md5,
                contentType, date.toString(), nonce);
    }

    protected T post(String path, Payload payload, String contentType)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        String md5 = md5(payload.toJson().toString());
        String nonce = generateNonce();
        Date currDate = new Date();
        String hmac = generateHMACAuth(secret, Http.POST, path, md5, contentType, currDate, nonce);
        return initateRequest(Http.POST, contentType, md5, path, hmac, nonce, payload.toJson().toString(), currDate);
    }

    protected T get(String path, String pathArgs, String contentType)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        String md5 = md5("");
        Date currDate = new Date();
        String nonce = generateNonce();
        String hmac = generateHMACAuth(secret, Http.GET, path, md5, contentType, currDate, nonce);
        return initateRequest(Http.GET, contentType, md5, String.format("%s?%s", path, pathArgs), hmac, nonce, null, currDate);
    }

    protected List<T> gets(String path, String pathArgs, String contentType)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        String md5 = md5("");
        Date currDate = new Date();
        String nonce = generateNonce();
        String hmac = generateHMACAuth(secret, Http.GET, path, md5, contentType, currDate, nonce);
        return initiateMultipleResponseRequest(Http.GET, contentType, md5, String.format("%s?%s", path, pathArgs), hmac, nonce, null, currDate);
    }

    protected T delete(String path, String pathArgs, String contentType)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        String md5 = md5("");
        Date currDate = new Date();
        String nonce = generateNonce();
        String hmac = generateHMACAuth(secret, Http.DELETE, path, md5, contentType, currDate, nonce);
        return initateRequest(Http.DELETE, contentType, md5, String.format("%s?%s", path, pathArgs), hmac, nonce, null, currDate);
    }

    protected T put(String path, Payload payload, String contentType)
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        String payloadStr = (null != payload) ? payload.toJson().toString() : "";
        String md5 = md5(payloadStr);
        String nonce = generateNonce();
        Date currDate = new Date();
        String hmac = generateHMACAuth(secret, Http.PUT, path, md5, contentType, currDate, nonce);
        return initateRequest(Http.PUT, contentType, md5, path, hmac, nonce, payloadStr, currDate);
    }

    protected List<T> initiateMultipleResponseRequest(Http method, String contentType, String md5,
                                              String endpoint, String hmac, String nonce,
                                              String payload, Date date)
            throws IOException, ApiResponseException {
        return extractMultipleFrom(makeRequest(method, contentType, md5,
                endpoint, hmac, nonce, payload, date));
    }

    protected T initateRequest(Http method, String contentType, String md5,
                                    String endpoint, String hmac, String nonce,
                                    String payload, Date date)
            throws IOException, ApiResponseException {

        return extractFrom(makeRequest(method, contentType, md5,
                endpoint, hmac, nonce, payload, date));
    }

    public abstract T extractFrom(JSONObject object) throws ApiResponseException;

    public abstract List<T> extractMultipleFrom(JSONObject object) throws ApiResponseException;

    private JSONObject makeRequest(Http method, String contentType, String md5,
                                   String endpoint, String hmac, String nonce,
                                   String payload, Date date)
            throws IOException, ApiResponseException {
        URL url = new URL(String.format("%s%s", BASE_PATH, endpoint));
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod(method.name());
        http.setDoOutput(true);

        http.setRequestProperty("Authorization", String.format("nectar %s:%s", key, hmac));
        http.setRequestProperty("Content-Type", contentType);
        http.setRequestProperty("Content-MD5", md5);
        http.setRequestProperty("Date", date.toString());
        http.setRequestProperty("nonce", nonce);
        http.setRequestProperty("User-Agent", "nectar-java-sdk");

        if (null != payload) {
            http.setFixedLengthStreamingMode(payload.length());
            DataOutputStream wr = new DataOutputStream(http.getOutputStream());
            wr.writeBytes(payload);
            wr.flush();
            wr.close();
        }

        int responseCode = http.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JSONObject(response.toString());
    }
}
