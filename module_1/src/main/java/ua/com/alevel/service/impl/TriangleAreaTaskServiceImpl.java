package ua.com.alevel.service.impl;

import org.apache.commons.lang3.math.NumberUtils;
import ua.alevel.commons.service.TaskService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TriangleAreaTaskServiceImpl implements TaskService {

    @Override
    public String getSolutionForTaskByString(String str) {
        Pattern pointsPattern = Pattern.compile("\\[.*?]");
        Matcher matcher = pointsPattern.matcher(str);
        List<String> pointList = new ArrayList<>();
        while (matcher.find()) {
            pointList.add(matcher.group().replaceAll("\\[", "").replaceAll("]", ""));
        }
        boolean isAllInteger = pointList.stream().allMatch(TriangleAreaTaskServiceImpl::isNumeric);
        if (pointList.isEmpty() || !isAllInteger) return "Incorrect input";
        List<List<Double>> doublePoints = new ArrayList<>();
        for (String poin : pointList) {
            doublePoints.add(Arrays.stream(poin.split("\\s+"))
                    .map(NumberUtils::toDouble)
                    .collect(Collectors.toList()));
        }
        if (doublePoints.size() != 3) return "Incorrect input";
        double firstSide = Math.sqrt(
                Math.pow((doublePoints.get(0).get(0) - doublePoints.get(1).get(0)), 2)
                        + Math.pow((doublePoints.get(0).get(1) - doublePoints.get(1).get(1)), 2)
        );
        double secondSide = Math.sqrt(
                Math.pow((doublePoints.get(0).get(0) - doublePoints.get(2).get(0)), 2)
                        + Math.pow((doublePoints.get(0).get(1) - doublePoints.get(2).get(1)), 2)
        );
        double thirdSide = Math.sqrt(
                Math.pow((doublePoints.get(1).get(0) - doublePoints.get(2).get(0)), 2)
                        + Math.pow((doublePoints.get(1).get(1) - doublePoints.get(2).get(1)), 2)
        );
        if ((firstSide >= secondSide + thirdSide) || (secondSide >= firstSide + thirdSide) || (thirdSide >= firstSide + secondSide))
            return "Triangle does not exist";
        double halfPerimeter = (firstSide + secondSide + thirdSide) / 2.0;
        double square = Math.sqrt(halfPerimeter * (halfPerimeter - firstSide) * (halfPerimeter - secondSide) * (halfPerimeter - thirdSide));
        return "Square: " + square;

    }

    private static Boolean isNumeric(String str) {
        boolean isDouble;
        try {
            for (String stringWithDouble : str.split("\\s+")) {
                Double.valueOf(stringWithDouble);
            }
            isDouble = true;
        } catch (Exception e) {
            isDouble = false;
        }
        return isDouble;
    }
}
