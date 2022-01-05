package ua.com.alevel.util;

import ua.alevel.commons.util.sort.MathCollectionUtils;
import ua.com.alevel.date.Calendar;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DateOperationUtil {

    private DateOperationUtil() {
    }

    public static Calendar subtraction(Calendar firstDate, Calendar secondDate) {
        long firstTime = firstDate.getTimeInMillis();
        long secondTime = secondDate.getTimeInMillis();
        return new Calendar(firstTime - secondTime);
    }

    public static List<Calendar> sort(List<Calendar> dates, boolean isAscending) {
        Long[] millis = dates.stream()
                .map(Calendar::getTimeInMillis)
                .toArray(Long[]::new);
        MathCollectionUtils.mergeSort(millis);
        List<Calendar> resultDates = Arrays.stream(millis)
                .map(Calendar::new)
                .toList();
        if (isAscending) return resultDates;
        Collections.reverse(resultDates);
        return resultDates;
    }
}
