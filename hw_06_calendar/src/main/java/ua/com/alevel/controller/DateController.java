package ua.com.alevel.controller;

import ua.com.alevel.enumeration.DateTemplate;
import ua.com.alevel.service.DateService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.enumeration.DateTemplate.*;

public class DateController {

    private final DateService dateService = new DateService();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private DateTemplate format = DD_SLASH_MM_SLASH_YYYY;

    private static final String SOMETHING_WENT_WRONG = "Something went wrong";
    private static final String WRONG_CHOSE = "Wrong chose";

    public void start() {
        System.out.println("Please, select your option");
        String option;
        try {
            runNavigation();
            while ((option = reader.readLine()) != null) {

                getOperationByOption(option);


                option = reader.readLine();
                if (option.equals("0")) {
                    System.exit(0);
                }
                getOperationByOption(option);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        String menu = """
                Change format (default is dd/mm/yy hh:mm:ss:msmsms) --- enter 1
                Find the difference between dates --------------------- enter 2
                Add to date time -------------------------------------- enter 3
                Subtract from date time ------------------------------- enter 4
                Sorting ascending the dates --------------------------- enter 5
                Sorting descending the dates -------------------------- enter 6
                Exit -------------------------------------------------- enter 0
                """;
        System.out.println(menu);
    }

    private void getOperationByOption(String option) {
        switch (option) {
            case "0" -> System.exit(0);
            case "1" -> changeFormat();
            case "2" -> differenceDates();
            case "3" -> addToDateTime();
            case "4" -> subtractFromDateTime();
            case "5" -> sortAscDates();
            case "6" -> sortDescDates();
            default -> System.out.println(WRONG_CHOSE);
        }
        runNavigation();
    }

    private void sortDescDates() {
        List<String> dates = readDates();
        DateTemplate outputFormat = choseOutputFormat();
        List<String> result = dateService.sortDescDates(dates, format, outputFormat);
        for (String date : result) {
            System.out.println(date);
        }
    }

    private void sortAscDates() {
        List<String> dates = readDates();
        DateTemplate outputFormat = choseOutputFormat();
        List<String> result = dateService.sortAscDates(dates, format, outputFormat);
        for (String date : result) {
            System.out.println(date);
        }
    }

    private void subtractFromDateTime() {
        System.out.println("Please, enter date. For example: 05/05/2021 10:10:10:555");
        String firstDate = null;
        try {
            firstDate = reader.readLine();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        System.out.println("Choose the time to subtract. Default it is millisecond");
        System.out.println("if you want add millisecond, enter 1");
        System.out.println("if you want add second,      enter 2");
        System.out.println("if you want add minute,      enter 3");
        System.out.println("if you want add hour,        enter 4");
        System.out.println("if you want add day,         enter 5");
        System.out.println("if you want add year,        enter 6");
        String typeOfTime = "1";
        try {
            typeOfTime = reader.readLine();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        System.out.println("Please, enter your time");
        String time = null;
        try {
            time = reader.readLine();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        DateTemplate outputFormat = choseOutputFormat();
        try {
            switch (typeOfTime) {
                case "1" -> System.out.println(dateService.subtractFromDateTime(firstDate, time, format, outputFormat, 1));
                case "2" -> System.out.println(dateService.subtractFromDateTime(firstDate, time, format, outputFormat, 2));
                case "3" -> System.out.println(dateService.subtractFromDateTime(firstDate, time, format, outputFormat, 3));
                case "4" -> System.out.println(dateService.subtractFromDateTime(firstDate, time, format, outputFormat, 4));
                case "5" -> System.out.println(dateService.subtractFromDateTime(firstDate, time, format, outputFormat, 5));
                case "6" -> System.out.println(dateService.subtractFromDateTime(firstDate, time, format, outputFormat, 6));
                default -> System.out.println(WRONG_CHOSE);
            }
        } catch (Exception e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
    }

    private void addToDateTime() {
        System.out.println("Please, enter date. For example: 05/05/2021 10:10:10:555");
        String firstDate = null;
        try {
            firstDate = reader.readLine();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        System.out.println("Choose the time to add. Default it is millisecond");
        System.out.println("if you want add millisecond, enter 1");
        System.out.println("if you want add second,      enter 2");
        System.out.println("if you want add minute,      enter 3");
        System.out.println("if you want add hour,        enter 4");
        System.out.println("if you want add day,         enter 5");
        System.out.println("if you want add year,        enter 6");
        String typeOfTime = "1";
        try {
            typeOfTime = reader.readLine();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        System.out.println("Please, enter your time");
        String time = null;
        try {
            time = reader.readLine();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        DateTemplate outputFormat = choseOutputFormat();
        try {
            switch (typeOfTime) {
                case "1" -> System.out.println(dateService.addToDateTime(firstDate, time, format, outputFormat, 1));
                case "2" -> System.out.println(dateService.addToDateTime(firstDate, time, format, outputFormat, 2));
                case "3" -> System.out.println(dateService.addToDateTime(firstDate, time, format, outputFormat, 3));
                case "4" -> System.out.println(dateService.addToDateTime(firstDate, time, format, outputFormat, 4));
                case "5" -> System.out.println(dateService.addToDateTime(firstDate, time, format, outputFormat, 5));
                case "6" -> System.out.println(dateService.addToDateTime(firstDate, time, format, outputFormat, 6));
                default -> System.out.println(WRONG_CHOSE);
            }
        } catch (Exception e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
    }

    private void differenceDates() {
        System.out.println("Please, enter first date. For example: 05/10/2021 10:10:10:555");
        String firstDate = null;
        try {
            firstDate = reader.readLine();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        System.out.println("Please, enter second date. For example: 01/08/2019 05:05:05:555");
        String secondDate = null;
        try {
            secondDate = reader.readLine();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        System.out.println("if you want result in millisecond, enter 1");
        System.out.println("if you want result in second,      enter 2");
        System.out.println("if you want result in minute,      enter 3");
        System.out.println("if you want result in hour,        enter 4");
        System.out.println("if you want result in day,         enter 5");
        System.out.println("if you want result in year,        enter 6");
        try {
            String answer = reader.readLine();
            switch (answer) {
                case "1" -> System.out.println(dateService.differenceBetweenDates(firstDate, secondDate, format, 1) + " milliseconds");
                case "2" -> System.out.println(dateService.differenceBetweenDates(firstDate, secondDate, format, 2) + " seconds");
                case "3" -> System.out.println(dateService.differenceBetweenDates(firstDate, secondDate, format, 3) + " minutes");
                case "4" -> System.out.println(dateService.differenceBetweenDates(firstDate, secondDate, format, 4) + " hours");
                case "5" -> System.out.println(dateService.differenceBetweenDates(firstDate, secondDate, format, 5) + " days");
                case "6" -> System.out.println(dateService.differenceBetweenDates(firstDate, secondDate, format, 6) + " years");
                default -> System.out.println(WRONG_CHOSE);
            }
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
    }

    private void changeFormat() {
        String choice = "1";
        System.out.println("if you want format dd/mm/yyyy  hh:mm:ss:msmsms, please enter 1");
        System.out.println("if you want format m/d/yyyy    hh:mm:ss:msmsms, please enter 2");
        System.out.println("if you want format mmm-d-yyyy  hh:mm:ss:msmsms, please enter 3");
        System.out.println("if you want format dd-mmm-yyyy hh:mm:ss:msmsms, please enter 4");
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        switch (choice) {
            case "1" -> format = DD_SLASH_MM_SLASH_YYYY;
            case "2" -> format = M_SLASH_D_SLASH_YYYY;
            case "3" -> format = MMM_HYPHEN_D_HYPHEN_YYYY;
            case "4" -> format = DD_HYPHEN_MMM_HYPHEN_YYYY;
            default -> System.out.println(WRONG_CHOSE);
        }
    }

    private DateTemplate choseOutputFormat() {
        DateTemplate outputFormat = format;
        System.out.println("Select date output format. Default it is input format");
        String choice = "1";
        System.out.println("if you want format dd/mm/yyyy  hh:mm:ss:msmsms, please enter 1");
        System.out.println("if you want format m/d/yyyy    hh:mm:ss:msmsms, please enter 2");
        System.out.println("if you want format mmm-d-yyyy  hh:mm:ss:msmsms, please enter 3");
        System.out.println("if you want format dd-mmm-yyyy hh:mm:ss:msmsms, please enter 4");
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        switch (choice) {
            case "1" -> outputFormat = DD_SLASH_MM_SLASH_YYYY;
            case "2" -> outputFormat = M_SLASH_D_SLASH_YYYY;
            case "3" -> outputFormat = MMM_HYPHEN_D_HYPHEN_YYYY;
            case "4" -> outputFormat = DD_HYPHEN_MMM_HYPHEN_YYYY;
            default -> System.out.println(WRONG_CHOSE);
        }
        return outputFormat;
    }

    private List<String> readDates() {
        System.out.println("Input dates and press enter");
        System.out.println("If you want to stop entered dates, input 0 and press enter");
        List<String> dates = new ArrayList<>();
        try {
            while (true) {
                String maybeDate = reader.readLine();
                if (maybeDate.strip().equals("0")) {
                    break;
                }
                dates.add(maybeDate);
            }
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG);
            e.printStackTrace();
        }
        return dates;
    }
}
