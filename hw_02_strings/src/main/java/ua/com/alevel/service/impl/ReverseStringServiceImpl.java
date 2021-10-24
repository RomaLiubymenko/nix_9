package ua.com.alevel.service.impl;

import ua.com.alevel.service.ReverseStringService;

public class ReverseStringServiceImpl implements ReverseStringService {

    @Override
    public String reverse(String str, boolean isAllStringReverse) {
        if (isAllStringReverse) {
            String result = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                result = result + str.charAt(i);
            }
            return result;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = str.trim().split("\\s+");
        for (String word : words) {
            String reversedWord = "";
            for (int i = word.length() - 1; i >= 0; i--) {
                reversedWord = reversedWord + word.charAt(i);
            }
            stringBuilder.append(reversedWord).append(' ');
        }
        return stringBuilder.toString();
    }

    @Override
    public String reverse(String str, String dest, boolean isAllStringReverse) {
        if (isAllStringReverse) {

        }
        return null;
    }

    @Override
    public String reverse(String str, int firstIndex, int lastIndex, boolean isAllStringReverse) {
        if (isAllStringReverse) {
            StringBuilder result = new StringBuilder();
            String[] words = str.trim().split("\\s+");

            for (int i = 0; i < words.length; i++) {
                if (i >= firstIndex && i <= lastIndex) {
                    result.append(reverse(words[i], true)).append(" ");
                } else {
                    result.append(words[i]).append(" ");
                }
            }
            return result.toString();
        }
        return null;
    }
}
