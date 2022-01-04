package ua.com.alevel.util;

import ua.com.alevel.date.Calendar;

import java.util.List;

public class DateUtil {

    private DateUtil() {
    }

    public static Calendar sum(Calendar firstDate, Calendar secondDate) {
        long firstTime = firstDate.getTimeInMillis();
        long secondTime = secondDate.getTimeInMillis();
        return new Calendar(firstTime + secondTime);
    }

    public static Calendar difference(Calendar firstDate, Calendar secondDate) {
        return  null;
    }

    public static Calendar subtraction(Calendar firstDate, Calendar secondDate) {
        return  null;

    }

    public static List<Calendar> sort(List<Calendar> dates) {
        return  null;

    }

}
