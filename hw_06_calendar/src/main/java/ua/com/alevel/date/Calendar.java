package ua.com.alevel.date;

public class Calendar {

    private static int MILLIS_IN_SECOND = 1000;
    private static int SECONDS_IN_MINUTE = 60;
    private static int MINUTES_IN_HOUR = 60;
    private static int HOURS_IN_DAY = 24;
    private static int DAYS_IN_YEAR = 365;
    private static int DAYS_IN_LEAP_YEAR = 366;

    private long millisecond;
    private long second;
    private long minute;
    private long hour;
    private long day;
    private long month;
    private long year;
    private long timeInMillis;

    public Calendar(long millisecond, long second, long minute, long hour, long day, long month, long year) {
        // todo: add exception on wrong date and format
        this.millisecond = millisecond;
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
        this.timeInMillis = toTimeMillis(millisecond, second, minute, hour, day, month, year);
    }

    public Calendar(long millisecond) {
        // todo: add exception on wrong date and format
        this.timeInMillis = millisecond;
        this.millisecond = millisecond;
        this.second = getSecondFromMillis(millisecond);
        this.minute = getMinuteFromMillis(millisecond);
        this.hour = getHourFromMillis(millisecond);
        this.day = getDayFromMillis(millisecond);
        this.month = getMonthFromMillis(millisecond);
        this.year = getYearFromMillis(millisecond);
    }

    public long toTimeMillis(long millisecond, long second, long minute, long hour, long day, long month, long year) {
        this.timeInMillis += millisecond;
        this.timeInMillis += toMillisFromSecond(second);
        this.timeInMillis += toMillisFromMinute(minute);
        this.timeInMillis += toMillisFromHour(hour);
        this.timeInMillis += toMillisFromDay(day);
        this.timeInMillis += toMillisFromMonth(month, year);
        this.timeInMillis += toMillisFromYear(year);
        return timeInMillis;
    }

    private long toMillisFromMonth(long month, long year) {
        long time = 0;
        if (month == 1) return 0;
        for (int i = 1; i < month; i++) {
            time += toMillisFromDay(getCountDaysInMonth(i, (int) year));
        }
        return time;
    }

    private long toMillisFromYear(long year) {
        long dayInMillis = (long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY;
        long time = 0;
        for (int i = 1; i < year; i++) {
            final long dayInYear = getDaysFromYear(i);
            long test = dayInYear * dayInMillis;
            time = time + test;
        }
        for (int i = 1; i <= year / 100; i++) {
            if (year >= ((100L * i) + 1)) {
                time += dayInMillis;
            }
            if (year >= ((400L * i) + 1)) {
                time -= dayInMillis;
            }
        }
        return time;
    }

    private long toMillisFromDay(long dayCount) {
        return toMillisFromHour(HOURS_IN_DAY * dayCount);
    }

    private long toMillisFromHour(long hourCount) {
        return toMillisFromMinute(MINUTES_IN_HOUR * hourCount);
    }

    private long toMillisFromMinute(long minuteCount) {
        return toMillisFromSecond(SECONDS_IN_MINUTE * minuteCount);
    }

    private long toMillisFromSecond(long secondCount) {
        return MILLIS_IN_SECOND * secondCount;
    }

    public long getMillisFromMillis(long millis) {
        return millis % MILLIS_IN_SECOND;
    }

    public long getSecondFromMillis(long millis) {
        return (millis / MILLIS_IN_SECOND) % SECONDS_IN_MINUTE;
    }

    public long getMinuteFromMillis(long millis) {
        return (millis / ((long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE)) % MINUTES_IN_HOUR;
    }

    public long getHourFromMillis(long millis) {
        return (millis / ((long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR)) % HOURS_IN_DAY;
    }

    public long getDayFromMillis(long millis) {
        long daysInThisYear = ((getAllDaysFromMillis(millis)) % (DAYS_IN_YEAR * 3L + DAYS_IN_LEAP_YEAR)) % 365;
        long currentYear = getYearFromMillis(millis);
        for (int currentMonth = 1; currentMonth <= 12; currentMonth++) {
            if (daysInThisYear >= getCountDaysInMonth(currentMonth, (int) currentYear)) {
                daysInThisYear = daysInThisYear - getCountDaysInMonth(currentMonth, (int) currentYear);
            } else {
                break;
            }
        }
        return daysInThisYear;
    }

    public long getMonthFromMillis(long millis) {
        long allDays = getAllDaysFromMillis(millis);
        long currentYear = getYearFromMillis(millis);
        for (long yearIndex = 1; yearIndex < currentYear; yearIndex++) {
            allDays -= getDaysFromYear(yearIndex);
        }
        int monthNumber = 1;
        for (int i = 1; i <= 12; i++) {
            int daysInMonth = getCountDaysInMonth(i, (int) currentYear);
            if (allDays <= daysInMonth) {
                break;
            } else {
                allDays -= daysInMonth;
                monthNumber++;
            }
        }
        return monthNumber;
    }

    public long getYearFromMillis(long millis) {
        long numberOfYears = 1;
        int count = 0;
        long days = getAllDaysFromMillis(millis);
        while (true) {
            if (days >= DAYS_IN_YEAR) {
                switch (++count) {
                    case 1, 2, 3 -> {
                        numberOfYears++;
                        days = days - DAYS_IN_YEAR;
                    }
                    case 4 -> {
                        numberOfYears++;
                        days = days - DAYS_IN_LEAP_YEAR;
                        count = 0;
                    }
                }
            } else {
                break;
            }
        }
        return numberOfYears;
    }

    private long getAllDaysFromMillis(long millis) {
        return millis / ((long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY);
    }

    private long getDaysFromYear(long year) {
        if (isLeapYear(year)) {
            return DAYS_IN_LEAP_YEAR;
        }
        return DAYS_IN_YEAR;
    }

    private boolean isLeapYear(long year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else return year % 100 != 0;
    }

    private int getCountDaysInMonth(int month, int year) {
        switch (month) {
            case 4, 6, 9, 11 -> {
                return 30;
            }
            case 2 -> {
                if (isLeapYear(year)) {
                    return 29;
                }
                return 28;
            }
            default -> {
                return 31;
            }
        }
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public long getMillisecond() {
        return millisecond;
    }

    public long getSecond() {
        return second;
    }

    public long getMinute() {
        return minute;
    }

    public long getHour() {
        return hour;
    }

    public long getDay() {
        return day;
    }

    public long getMonth() {
        return month;
    }

    public long getYear() {
        return year;
    }
}
