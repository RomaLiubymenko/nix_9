package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import ua.alevel.commons.service.TaskService;

import java.util.Arrays;
import java.util.HashSet;

public class UniqueNumberTaskServiceImpl implements TaskService {

    @Override
    public String getSolutionForTaskByString(String str) {
        if (StringUtils.isNumericSpace(str)) {
            String[] numbers = str.trim().split("\\s+");
            return "Unique number count: " + new HashSet<>(Arrays.asList(numbers)).size();
        }
        return "Incorrect input; The string does not contain integer numbers";
    }
}
