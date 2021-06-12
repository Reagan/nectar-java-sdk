package software.nectar.java.factory;

import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.models.Notification;

import java.util.List;

public class NotificationFactory extends BaseFactory {

    public NotificationFactory(String key, String secret) {
        super(key, secret);
    }

    public Notification getNotification() {
        return null;
    }

    public List<Notification> getNotifications() {
        return null;
    }
}
