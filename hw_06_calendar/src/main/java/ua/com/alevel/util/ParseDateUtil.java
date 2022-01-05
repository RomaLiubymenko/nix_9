package ua.com.alevel.util;

import ua.com.alevel.date.Calendar;
import ua.com.alevel.enumeration.DateTemplate;

import java.util.List;

public class ParseDateUtil {

    private static final String SLASH = "/";
    private static final String HYPHEN = "-";
    private static final String COLON = ":";

    private ParseDateUtil() {
    }

    public static Calendar parseDateByTemplate(String dateString, DateTemplate dateTemplate) {
        long year = 0;
        long month = 0;
        long day = 0;
        String[] stringDate;
        String[] dateTime = dateString.split(" ");
        switch (dateTemplate) {
            case DD_SLASH_MM_SLASH_YYYY -> {
                stringDate = dateTime[0].split(SLASH);
                switch (stringDate.length) {
                    case 1 -> {
                        year = Long.parseLong(stringDate[0]);
                    }
                    case 2 -> {
                        month = Long.parseLong(stringDate[0]);
                        year = Long.parseLong(stringDate[1]);
                    }
                    case 3 -> {
                        day = Long.parseLong(stringDate[0]);
                        month = Long.parseLong(stringDate[1]);
                        year = Long.parseLong(stringDate[2]);
                    }
                }
            }
            case M_SLASH_D_SLASH_YYYY -> {
                stringDate = dateTime[0].split(SLASH);
                switch (stringDate.length) {
                    case 1 -> {
                        year = Long.parseLong(stringDate[0]);
                    }
                    case 2 -> {
                        day = Long.parseLong(stringDate[0]);
                        year = Long.parseLong(stringDate[1]);
                    }
                    case 3 -> {
                        month = Long.parseLong(stringDate[0]);
                        day = Long.parseLong(stringDate[1]);
                        year = Long.parseLong(stringDate[2]);
                    }
                }
            }
            case MMM_HYPHEN_D_HYPHEN_YYYY -> {
                stringDate = dateTime[0].split(HYPHEN);
                switch (stringDate.length) {
                    case 1 -> {
                        year = Long.parseLong(stringDate[0]);
                    }
                    case 2 -> {
                        day = Long.parseLong(stringDate[0]);
                        year = Long.parseLong(stringDate[1]);
                    }
                    case 3 -> {
                        month = getMonthNumberByName(stringDate[0]);
                        day = Long.parseLong(stringDate[1]);
                        year = Long.parseLong(stringDate[2]);
                    }
                }
            }
            case DD_HYPHEN_MMM_HYPHEN_YYYY -> {
                stringDate = dateTime[0].split(HYPHEN);
                switch (stringDate.length) {
                    case 1 -> {
                        year = Long.parseLong(stringDate[0]);
                    }
                    case 2 -> {
                        month = getMonthNumberByName(stringDate[0]);
                        year = Long.parseLong(stringDate[1]);
                    }
                    case 3 -> {
                        day = Long.parseLong(stringDate[0]);
                        month = getMonthNumberByName(stringDate[1]);
                        year = Long.parseLong(stringDate[2]);
                    }
                }
            }
        }

        Calendar calendar = getTimeFromString(dateTime);
        return new Calendar(
                calendar.getMillisecond(),
                calendar.getSecond(),
                calendar.getMinute(),
                calendar.getHour(),
                day, month, year
        );
    }

    public static String createDateByTemplate(Calendar date, DateTemplate dateTemplate) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (dateTemplate) {
            case DD_SLASH_MM_SLASH_YYYY -> {
                if (date.getDay() < 10) stringBuilder.append("0");
                stringBuilder.append(date.getDay());
                stringBuilder.append(SLASH);
                if (date.getMonth() < 10) stringBuilder.append("0");
                stringBuilder.append(date.getMonth());
                stringBuilder.append(SLASH);
                stringBuilder.append(date.getYear());
            }
            case M_SLASH_D_SLASH_YYYY -> {
                stringBuilder.append(date.getMonth());
                stringBuilder.append(SLASH);
                stringBuilder.append(date.getDay());
                stringBuilder.append(SLASH);
                stringBuilder.append(date.getYear());
            }
            case MMM_HYPHEN_D_HYPHEN_YYYY -> {
                stringBuilder.append(getMonthFromNumber((int) date.getMonth()));
                stringBuilder.append(HYPHEN);
                stringBuilder.append(date.getDay());
                stringBuilder.append(HYPHEN);
                stringBuilder.append(date.getYear());
            }
            case DD_HYPHEN_MMM_HYPHEN_YYYY -> {
                if (date.getDay() < 10) stringBuilder.append("0");
                stringBuilder.append(date.getDay());
                stringBuilder.append(HYPHEN);
                stringBuilder.append(getMonthFromNumber((int) date.getMonth()));
                stringBuilder.append(HYPHEN);
                stringBuilder.append(date.getYear());
            }
        }
        stringBuilder.append(" ");
        stringBuilder.append(date.getHour());
        stringBuilder.append(COLON);
        stringBuilder.append(date.getMinute());
        stringBuilder.append(COLON);
        stringBuilder.append(date.getSecond());
        stringBuilder.append(COLON);
        stringBuilder.append(date.getMillisecond());
        return stringBuilder.toString();
    }

    public static List<Calendar> parseDateByTemplate(List<String> dateStrings, DateTemplate dateTemplate) {
        return dateStrings.stream()
                .map(dateString -> ParseDateUtil.parseDateByTemplate(dateString, dateTemplate))
                .toList();
    }

    public static List<String> createDateByTemplate(List<Calendar> dates, DateTemplate dateTemplate) {
        return dates.stream()
                .map(date -> ParseDateUtil.createDateByTemplate(date, dateTemplate))
                .toList();
    }

    private static Calendar getTimeFromString(String[] dateTimeString) {
        long millis = 0;
        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        if (dateTimeString.length > 1) {
            String[] dateTime = dateTimeString[1].strip().split(COLON);
            if (dateTime.length > 0 && !dateTime[0].isBlank()) {
                hours = Long.parseLong(dateTime[0]);
            }
            if (dateTime.length > 1 && !dateTime[1].isBlank()) {
                minutes = Long.parseLong(dateTime[1]);
            }
            if (dateTime.length > 2 && !dateTime[2].isBlank()) {
                seconds = Long.parseLong(dateTime[2]);
            }
            if (dateTime.length > 3 && !dateTime[3].isBlank()) {
                millis = Long.parseLong(dateTime[3]);
            }
        }
        return new Calendar(millis, seconds, minutes, hours, 0L, 0L, 0L);
    }

    private static int getMonthNumberByName(String nameOfMonth) {
        return switch (nameOfMonth) {
            case "january" -> 1;
            case "february" -> 2;
            case "march" -> 3;
            case "april" -> 4;
            case "may" -> 5;
            case "june" -> 6;
            case "july" -> 7;
            case "august" -> 8;
            case "september" -> 9;
            case "october" -> 10;
            case "november" -> 11;
            case "december" -> 12;
            default -> -1;
        };
    }

    private static String getMonthFromNumber(int numberOfMonth) {
        return switch (numberOfMonth) {
            case 1 -> "january";
            case 2 -> "february";
            case 3 -> "march";
            case 4 -> "april";
            case 5 -> "may";
            case 6 -> "june";
            case 7 -> "july";
            case 8 -> "august";
            case 9 -> "september";
            case 10 -> "october";
            case 11 -> "november";
            case 12 -> "december";
            default -> "error";
        };
    }
}
