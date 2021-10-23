package ua.com.alevel.service;

public interface ReverseStringService {
    String reverse(String str, boolean isAllString);

    String reverse(String str, String dest, boolean isAllString);

    String reverse(String str, int firstIndex, int lastIndex);
}
