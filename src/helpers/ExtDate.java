package helpers;

import java.util.Date;

public class ExtDate {

    private ExtDate() {}

    public static Date dateTo(long seconds) {
        return new Date(new Date().getTime() + (seconds * 1000));
    }

    public static boolean hasPassed(Date date) {
        return date.before(new Date());
    }

}
