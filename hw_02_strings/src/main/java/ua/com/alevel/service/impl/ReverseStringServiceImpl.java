package ua.com.alevel.service.impl;

import ua.com.alevel.service.ReverseStringService;

public class ReverseStringServiceImpl implements ReverseStringService {

    @Override
    public String reverse(String str, boolean isAllStringReverse) {
        if (isAllStringReverse) {
            StringBuilder result = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                result.append(str.charAt(i));
            }
            return result.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = str.trim().split("\\s+");
        for (String word : words) {
            StringBuilder reversedWord = new StringBuilder();
            for (int i = word.length() - 1; i >= 0; i--) {
                reversedWord.append(word.charAt(i));
            }
            stringBuilder.append(reversedWord).append(' ');
        }
        return stringBuilder.toString();
    }

    @Override
    public String reverse(String str, int firstIndex, int lastIndex, boolean isAllStringReverse) {
        if (firstIndex < 0 || lastIndex >= str.length() || firstIndex > lastIndex)
            return "Invalid range!";
        if (isAllStringReverse) {
            char[] strChars = str.toCharArray();
            while (firstIndex < lastIndex) {
                char c = strChars[firstIndex];
                strChars[firstIndex] = strChars[lastIndex];
                strChars[lastIndex] = c;
                firstIndex++;
                lastIndex--;
            }
            return new String(strChars);
        }
        StringBuilder reversedWordsBuilder = new StringBuilder();
        StringBuilder substringBuilder = new StringBuilder();
        for (int i = firstIndex; i <= lastIndex; i++) {
            substringBuilder.append(str.charAt(i));
        }
        String[] sliceStr = substringBuilder.toString().trim().split("\\s+");
        for (String word : sliceStr) {
            reversedWordsBuilder.append(reverse(word, true));
        }
        StringBuilder result = new StringBuilder();
        int reversedWordsIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ' && ((i >= firstIndex) && (i <= lastIndex))) {
                result.append(reversedWordsBuilder.charAt(reversedWordsIndex));
                reversedWordsIndex++;
            } else {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    @Override
    public String reverse(String str, String substring) {
        if (!str.contains(substring)) {
            return "Invalid substring!";
        }
        int startIndex = str.indexOf(substring);
        int endIndex = str.indexOf(substring) + substring.length() - 1;
        return reverse(str, startIndex, endIndex, true);
    }
}
