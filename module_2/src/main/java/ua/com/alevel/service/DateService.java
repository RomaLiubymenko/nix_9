package ua.com.alevel.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

public class DateService {

    public String getDatesWithCorrectFormat(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("input/" + fileName)))) {
            String dates = reader.lines().collect(Collectors.joining("\n"));
            String[] dateArray = dates.split("\\s+");
            DateTimeFormatter yearFirstDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter dayFirstDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter monthFirstDateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateValidator yearFirstDateValidator = new DateValidator(yearFirstDateTimeFormatter);
            DateValidator dayFirstDateValidator = new DateValidator(dayFirstDateTimeFormatter);
            DateValidator monthFirstDateValidator = new DateValidator(monthFirstDateTimeFormatter);
            String resultDates = "";
            for (String date : dateArray) {
                if (yearFirstDateValidator.isValid(date)) {
                    resultDates += LocalDate.parse(date, yearFirstDateTimeFormatter).format(outputDateTimeFormatter) + " ";
                }
                if (dayFirstDateValidator.isValid(date)) {
                    resultDates += LocalDate.parse(date, dayFirstDateTimeFormatter).format(outputDateTimeFormatter) + " ";
                }
                if (monthFirstDateValidator.isValid(date)) {
                    resultDates += LocalDate.parse(date, monthFirstDateTimeFormatter).format(outputDateTimeFormatter) + " ";
                }
            }
            if (!resultDates.isEmpty()) {
                return resultDates;
            }
            return "Not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Not found";
        }
    }

    private class DateValidator {

        private final DateTimeFormatter dateFormatter;

        public DateValidator(DateTimeFormatter dateFormatter) {
            this.dateFormatter = dateFormatter;
        }

        public boolean isValid(String dateStr) {
            try {
                this.dateFormatter.parse(dateStr);
            } catch (DateTimeParseException e) {
                return false;
            }
            return true;
        }
    }
}
