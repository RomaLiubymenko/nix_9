package ua.com.alevel.service;

public interface ReverseStringService {
    String reverse(String str, boolean isAllStringReverse);

    String reverse(String str, String dest, boolean isAllStringReverse);

    String reverse(String str, int firstIndex, int lastIndex, boolean isAllStringReverse);
}
