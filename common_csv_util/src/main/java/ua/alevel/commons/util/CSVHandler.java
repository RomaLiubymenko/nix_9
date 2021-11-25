package ua.alevel.commons.util;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CSVHandler {

    private static final String COMMA_DELIMITER = ",";

    private CSVHandler() {
    }

    public static List<List<String>> readFromCSV(String fileName) {
        String correctPath = getCorrectPath(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(correctPath))) {
            return reader.lines()
                    .skip(1)
                    .filter(Objects::nonNull)
                    .map(string -> string.split(COMMA_DELIMITER))
                    .map(strings -> Arrays.stream(strings).collect(Collectors.toList()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static boolean writeToCSV(List<List<String>> entities, String fileName) {
        String correctPath = getCorrectPath(fileName);
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(correctPath, true)))) {
            entities.stream()
                    .map(CSVHandler::convertToCSV)
                    .forEach(writer::println);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean clearCSV(String fileName) {
        String correctPath = getCorrectPath(fileName);
        try (PrintWriter writer = new PrintWriter(correctPath)) {
            writer.write("");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isEmpty(String fileName) {
        String correctPath = getCorrectPath(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(correctPath))) {
            return br.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    private static String convertToCSV(List<String> entityList) {
        return entityList.stream()
                .map(CSVHandler::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    private static String getCorrectPath(String fileName) {
        String path = CSVHandler.class.getClassLoader().getResource(fileName).getPath();
        String substring = path.substring(0, path.lastIndexOf("target")).replace("file:/", "");
        return substring.concat("src/main/resources/").concat(fileName);
    }
}
