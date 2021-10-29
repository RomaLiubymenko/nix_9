package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import ua.alevel.commons.service.TaskService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class KnightMovingOnChessboardTaskServiceImpl implements TaskService {

    @Override
    public String getSolutionForTaskByString(String str) {
        Pattern pointsPattern = Pattern.compile("\\[.*?]");
        Matcher matcher = pointsPattern.matcher(str);
        List<String> pointList = new ArrayList<>();
        while (matcher.find()) {
            pointList.add(matcher.group().replaceAll("\\[", "").replaceAll("]", ""));
        }
        boolean isAllInteger = pointList.stream().allMatch(StringUtils::isNumericSpace);
        if (pointList.isEmpty() || !isAllInteger) return "Incorrect input";
        List<List<Integer>> integerPoints = new ArrayList<>();
        for (String poin : pointList) {
            integerPoints.add(Arrays.stream(poin.split("\\s+"))
                    .map(NumberUtils::toInt)
                    .collect(Collectors.toList()));
        }
        if (integerPoints.size() != 2) return "Incorrect input";
        double dx = Math.abs(integerPoints.get(0).get(0) - integerPoints.get(1).get(0));
        double dy = Math.abs(integerPoints.get(0).get(1) - integerPoints.get(1).get(1));
        if (dx == 1 && dy == 2 || dx == 2 && dy == 1) {
            return "Yes, the knight can move to this chess cell";
        }
        return "No, the knight can not move to here";
    }
}
