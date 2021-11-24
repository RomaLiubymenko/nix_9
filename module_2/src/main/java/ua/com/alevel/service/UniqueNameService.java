package ua.com.alevel.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class UniqueNameService {

    public String getFirstUniqueName(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("input/" + fileName)))) {
            String names = reader.lines().collect(Collectors.joining("\n"));
            String[] nameArray = names.split("\\s+");
            int uniqueIndex = -1;
            int lastIndex = 0;
            String[] strings = new String[nameArray.length];
            int[] counts = new int[nameArray.length];
            int[] firstIndices = new int[nameArray.length];
            for (int index = 0; index < nameArray.length; index++) {
                boolean found = false;
                int frequencyIndex = 0;
                while (frequencyIndex < nameArray.length && !found) {
                    if (nameArray[index].equals(strings[frequencyIndex])) {
                        counts[frequencyIndex]++;
                        found = true;
                    }
                    frequencyIndex++;
                }
                if (!found) {
                    strings[lastIndex] = nameArray[index];
                    counts[lastIndex] = 1;
                    firstIndices[lastIndex] = index;
                    lastIndex++;
                }
            }
            for (int index = 0; index <= lastIndex; index++) {
                if (counts[index] == 1) {
                    uniqueIndex = firstIndices[index];
                    break;
                }
            }
            if (uniqueIndex != -1) {
                return nameArray[uniqueIndex];
            }
            return "Not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Not found";
        }
    }
}
