package ua.com.alevel.service.implementation;

import ua.com.alevel.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstTaskServiceImpl implements TaskService {

    @Override
    public String getSolutionForTaskByString(String stringToSearch) {
        Pattern decimalNumPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = decimalNumPattern.matcher(stringToSearch);
        List<String> decimalNumList = new ArrayList<>();
        while (matcher.find()) {
            decimalNumList.add(matcher.group());
        }
        if (decimalNumList.isEmpty()) return "Empty result";
        return decimalNumList.stream().mapToDouble(Double::parseDouble).sum() + "";
    }
}
