package ua.com.alevel.service;

import ua.com.alevel.date.Calendar;
import ua.com.alevel.enumeration.DateTemplate;
import ua.com.alevel.util.DateOperationUtil;
import ua.com.alevel.util.ParseDateUtil;

import java.util.Collections;
import java.util.List;

public class DateService {

    public String differenceBetweenDates(String firstDateString, String secondDateString, DateTemplate inputTemplate, int numberOfTimesResult) {
        Calendar firstDate = ParseDateUtil.parseDateByTemplate(firstDateString, inputTemplate);
        Calendar secondDate = ParseDateUtil.parseDateByTemplate(secondDateString, inputTemplate);
        Calendar resultDate = DateOperationUtil.subtraction(firstDate, secondDate);
        return switch (numberOfTimesResult) {
            case 1 -> String.valueOf(resultDate.getMillisecond());
            case 2 -> String.valueOf(resultDate.getSecond());
            case 3 -> String.valueOf(resultDate.getMinute());
            case 4 -> String.valueOf(resultDate.getHour());
            case 5 -> String.valueOf(resultDate.getDay());
            case 6 -> String.valueOf(resultDate.getYear());
            default -> String.valueOf(resultDate.getMillisecond());
        };
    }

    public String addToDateMilliseconds(String dateString, String times, DateTemplate inputTemplate, DateTemplate outputTemplate, int typeOfTime) {
        Calendar date = ParseDateUtil.parseDateByTemplate(dateString, inputTemplate);
        long time = Long.parseLong(times);
        switch (typeOfTime) {
            case 1 -> date.addMilliseconds(time);
            case 2 -> date.addSeconds(time);
            case 3 -> date.addMinutes(time);
            case 4 -> date.addHours(time);
            case 5 -> date.addDays(time);
            case 6 -> date.addYears(time);
            default -> date.addMilliseconds(time);
        }
        return ParseDateUtil.createDateByTemplate(date, outputTemplate);
    }

    public String subtractFromDateTime(String dateString, String times, DateTemplate inputTemplate, DateTemplate outputTemplate, int typeOfTime) {
        Calendar date = ParseDateUtil.parseDateByTemplate(dateString, inputTemplate);
        long time = Long.parseLong(times);
        switch (typeOfTime) {
            case 1 -> date.subtractMilliseconds(time);
            case 2 -> date.subtractSeconds(time);
            case 3 -> date.subtractMinutes(time);
            case 4 -> date.subtractHours(time);
            case 5 -> date.subtractDays(time);
            case 6 -> date.subtractYears(time);
            default -> date.subtractMilliseconds(time);
        }
        return ParseDateUtil.createDateByTemplate(date, outputTemplate);
    }

    public List<String> sortAscDates(List<String> dates, DateTemplate inputTemplate, DateTemplate outputTemplate) {
        List<Calendar> dateList = ParseDateUtil.parseDateByTemplate(dates, inputTemplate);
        List<Calendar> results = DateOperationUtil.sort(dateList, true);
        return ParseDateUtil.createDateByTemplate(results, outputTemplate);
    }

    public List<String> sortDescDates(List<String> dates, DateTemplate inputTemplate, DateTemplate outputTemplate) {
        List<Calendar> dateList = ParseDateUtil.parseDateByTemplate(dates, inputTemplate);
        List<Calendar> results = DateOperationUtil.sort(dateList, false);
        return ParseDateUtil.createDateByTemplate(results, outputTemplate);
    }
}
