package ua.com.alevel.service.impl;

import ua.alevel.commons.service.TaskService;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class SecondTaskServiceImpl implements TaskService {

    @Override
    public String getSolutionForTaskByString(String stringToSearch) {
        Map<Character, Integer> symbolWithEntry = new TreeMap<>();
        Character.UnicodeBlock cyrillicUnicode = Character.UnicodeBlock.CYRILLIC;
        String latinRegex = "[a-zA-Z]";
        for (int i = 0; i < stringToSearch.length(); i++) {
            char currentCharacter = stringToSearch.charAt(i);
            if (Character.UnicodeBlock.of(currentCharacter).equals(cyrillicUnicode) || Pattern.matches(latinRegex, String.valueOf(currentCharacter))) {
                symbolWithEntry.merge(stringToSearch.charAt(i), 1, Integer::sum);
            }
        }
        if (symbolWithEntry.isEmpty()) return "Empty result";
        return symbolWithEntry.toString();
    }
}
