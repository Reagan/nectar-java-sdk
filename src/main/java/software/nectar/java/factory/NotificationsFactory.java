package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.ApiResponse;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.models.Notification;

import java.util.List;

public class NotificationsFactory extends BaseFactory {

    public NotificationsFactory(String key, String secret) {
        super(key, secret);
    }

    public Notification getNotification() {
        return null;
    }

    public List<Notification> getNotifications() {
        return null;
    }

    public ApiResponse extractFrom(JSONObject object) {
        return null;
    }
}
