package ua.com.alevel;

import ua.com.alevel.date.Calendar;

public class Main {

    public static void main(String[] args) {
        Calendar calendar = new Calendar(100,11,1,5,10,11,2021);
//        Calendar calendar = new Calendar(63826549271100L);
        long millis = calendar.getTimeInMillis();

        System.out.println("calendar.getTimeInMillis() = " + millis);

        System.out.println(calendar.getMillisFromMillis(millis));
        System.out.println(calendar.getSecondFromMillis(millis));
        System.out.println(calendar.getMinuteFromMillis(millis));
        System.out.println(calendar.getHourFromMillis(millis));
        System.out.println(calendar.getDayFromMillis(millis));
        System.out.println(calendar.getMonthFromMillis(millis));
        System.out.println(calendar.getYearFromMillis(millis));
        System.out.println("___-------___-------___");
    }

}
