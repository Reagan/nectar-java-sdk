package software.nectar.java.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTime {

    public static String formatDate(Instant instant) {
        return DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm")
                .withLocale(Locale.UK)
                .withZone(ZoneId.systemDefault())
                .format(instant);
    }
}
