package ua.com.alevel.date;

import ua.com.alevel.exception.DateValidationException;

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
        if (validateDate(millisecond, second, minute, hour, day, month, year)) {
            this.timeInMillis = toTimeMillis(millisecond, second, minute, hour, day, month, year);
        }
    }

    public Calendar(long millisecond) {
        if (millisecond < 0) {
            System.out.println("milliseconds cannot be less than 0");
        }
        fillTimeByMillis(millisecond);
    }

    private boolean validateDate(long millisecond, long second, long minute, long hour, long day, long month, long year) {
        if (isValidMillis(millisecond)) {
            this.millisecond = millisecond;
        } else {
            System.out.println("Milliseconds out of bounds");
            return false;
        }
        if (isValidSeconds(second)) {
            this.second = second;
        } else {
            System.out.println("Seconds out of bounds");
            return false;
        }
        if (isValidMinutes(minute)) {
            this.minute = minute;
        } else {
            System.out.println("Minutes out of bounds");
            return false;
        }
        if (isValidHours(hour)) {
            this.hour = hour;
        } else {
            System.out.println("Hours out of bounds");
            return false;
        }
        if (isValidDays(day)) {
            this.day = day;
        } else {
            System.out.println("Days out of bounds");
            return false;
        }
        if (isValidMonths(month)) {
            this.month = month;
        } else {
            System.out.println("Months out of bounds");
            return false;
        }
        if (isValidYears(year)) {
            this.year = year;
        } else {
            System.out.println("Years out of bounds");
            return false;
        }
        return true;
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

    private void fillTimeByMillis(long millis) {
        this.timeInMillis = millis;
        this.millisecond = getMillisFromMillis(millis);
        this.second = getSecondFromMillis(millis);
        this.minute = getMinuteFromMillis(millis);
        this.hour = getHourFromMillis(millis);
        this.day = getDayFromMillis(millis);
        this.month = getMonthFromMillis(millis);
        this.year = getYearFromMillis(millis);
    }

    private long toMillisFromYear(long year) {
        long time = 0;
        time += toMillisFromDay(year * DAYS_IN_YEAR);
        time += toMillisFromDay(year / 4);
        return time;
    }

    private long toMillisFromMonth(long month, long year) {
        long time = 0;
        if (month == 1 || month == 0) return 0;
        for (int i = 1; i < month; i++) {
            time += toMillisFromDay(getCountDaysInMonth(i, (int) year));
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

    private long getMillisFromMillis(long millis) {
        return millis % MILLIS_IN_SECOND;
    }

    private long getSecondFromMillis(long millis) {
        return (millis / MILLIS_IN_SECOND) % SECONDS_IN_MINUTE;
    }

    private long getMinuteFromMillis(long millis) {
        return (millis / ((long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE)) % MINUTES_IN_HOUR;
    }

    private long getHourFromMillis(long millis) {
        return (millis / ((long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR)) % HOURS_IN_DAY;
    }

    private long getDayFromMillis(long millis) {
        long daysInThisYear = (getAllDaysFromMillis(millis)) % (DAYS_IN_YEAR * 3L + DAYS_IN_LEAP_YEAR) % 365;
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

    private long getMonthFromMillis(long millis) {
        long currentYear = getYearFromMillis(millis);
        long millisWithoutYear = millis - toMillisFromYear(currentYear);
        long allDays = getAllDaysFromMillis(millisWithoutYear);
        long monthCount = 0;
        for (int i = 1; i <= 12; i++) {
            int countDaysInMonth = getCountDaysInMonth(i, (int) currentYear);
            if (allDays >= countDaysInMonth) {
                allDays -= countDaysInMonth;
                monthCount++;
            } else {
                monthCount++;
                return monthCount;
            }
        }
        return monthCount;
    }

    private long getYearFromMillis(long millis) {
        long yearCount = 0;
        long days = getAllDaysFromMillis(millis);
        while (days > 0) {
            if (isLeapYear(yearCount)) {
                if (days >= DAYS_IN_LEAP_YEAR) {
                    days -= DAYS_IN_LEAP_YEAR;
                    yearCount++;
                } else {
                    return yearCount;
                }
            } else if (days >= DAYS_IN_YEAR) {
                days -= DAYS_IN_YEAR;
                yearCount++;
            } else {
                return yearCount;
            }
        }
        return yearCount;
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

    public void addMilliseconds(long millis) {
        timeInMillis += millis;
        fillTimeByMillis(timeInMillis);
    }

    public void addSeconds(long seconds) {
        timeInMillis += toMillisFromSecond(seconds);
        fillTimeByMillis(timeInMillis);
    }

    public void addMinutes(long minutes) {
        timeInMillis += toMillisFromMinute(minutes);
        fillTimeByMillis(timeInMillis);
    }

    public void addHours(long hours) {
        timeInMillis += toMillisFromHour(hours);
        fillTimeByMillis(timeInMillis);
    }

    public void addDays(long days) {
        timeInMillis += toMillisFromDay(days);
        fillTimeByMillis(timeInMillis);
    }

    public void addYears(long years) {
        timeInMillis += toMillisFromYear(years);
        fillTimeByMillis(timeInMillis);
    }

    public void subtractMilliseconds(long millis) {
        timeInMillis -= millis;
        fillTimeByMillis(timeInMillis);
    }

    public void subtractSeconds(long seconds) {
        timeInMillis -= toMillisFromSecond(seconds);
        fillTimeByMillis(timeInMillis);
    }

    public void subtractMinutes(long minutes) {
        timeInMillis -= toMillisFromMinute(minutes);
        fillTimeByMillis(timeInMillis);
    }

    public void subtractHours(long hours) {
        timeInMillis -= toMillisFromHour(hours);
        fillTimeByMillis(timeInMillis);
    }

    public void subtractDays(long days) {
        timeInMillis -= toMillisFromDay(days);
        fillTimeByMillis(timeInMillis);
    }

    public void subtractYears(long years) {
        timeInMillis -= toMillisFromYear(years);
        fillTimeByMillis(timeInMillis);
    }

    private boolean isValidMillis(long millis) {
        return millis >= 0 && millis <= 999;
    }

    private boolean isValidSeconds(long seconds) {
        return seconds >= 0 && seconds <= 59;
    }

    private boolean isValidMinutes(long minutes) {
        return minutes >= 0 && minutes <= 59;
    }

    private boolean isValidHours(long hours) {
        return hours >= 0 && hours <= 23;
    }

    private boolean isValidDays(long days) {
        return days >= 0;
    }

    private boolean isValidMonths(long months) {
        return months >= 0 && months <= 12;
    }

    private boolean isValidYears(long years) {
        return years >= 0;
    }
}
