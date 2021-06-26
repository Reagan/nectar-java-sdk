package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.factory.base.exceptions.ApiResponseException;
import software.nectar.java.models.Notification;
import software.nectar.java.utils.Payload;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationsFactory extends BaseFactory<Notification> {

    private final String NOTIFICATIONS_PATH = "/v1/notifications";

    public NotificationsFactory(String key, String secret) {
        super(key, secret);
    }

    public List<Notification> getNotifications()
            throws NoSuchAlgorithmException, InvalidKeyException,
                    IOException, ApiResponseException {
        return (List<Notification>) get(NOTIFICATIONS_PATH, "", JSON_CONTENT_TYPE);
    }

    public void setNotificationReadStatus(String notificationRef,
                                                        boolean status,
                                                        long timestamp)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IOException, ApiResponseException {
        Map<String, Object> params = new HashMap<>();
        params.put("notification_ref", notificationRef);
        params.put("status", status);
        params.put("timestamp", timestamp);
        put(NOTIFICATIONS_PATH, new Payload(params), JSON_CONTENT_TYPE);
    }

    public List<Notification> extractMultipleFrom(JSONObject responseObj)
            throws ApiResponseException {
        return null;
    }

    public Notification extractFrom(JSONObject responseObj)
            throws ApiResponseException {
        if (responseObj.getJSONObject("status").getInt("code") == 200) {
            JSONObject token = responseObj.getJSONObject("data").getJSONObject("notification");
            return new Notification((String) token.get("ref"),
                    (String) token.get("subject"),
                    (String) token.get("text"),
                    (String) token.get("type"),
                    (String) token.get("user_ref"),
                    (String) token.get("affected"),
                    (Boolean) token.get("read"),
                    Instant.parse((String) token.get("read_date")),
                    Instant.parse((String) token.get("created_at")));
        }
        throw new ApiResponseException(responseObj.getJSONObject("status").getString("message"));
    }
}
