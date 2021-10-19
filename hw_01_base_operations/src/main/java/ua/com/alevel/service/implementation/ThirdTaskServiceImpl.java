package ua.com.alevel.service.implementation;

import ua.alevel.commons.service.TaskService;

import java.util.regex.Pattern;

public class ThirdTaskServiceImpl implements TaskService {

    @Override
    public String getSolutionForTaskByString(String stringToSearch) {
        String regexFromOneToTen = "[1-9]|10";
        if (Pattern.matches(regexFromOneToTen, stringToSearch)) {
            int numberOfLesson = Integer.parseInt(stringToSearch);
            int numberOfRecess = numberOfLesson - 1;
            int timeOfRecess = (numberOfRecess / 2 * 20) + (numberOfRecess % 2) * 5;
            int timeOfLesson = numberOfLesson * 45;
            int allTimeInMinutes = timeOfRecess + timeOfLesson;
            int hours = allTimeInMinutes / 60;
            int minutes = allTimeInMinutes % 60;
            return hours + 9 + " " + minutes;
        }
        return "Empty result";
    }
}
