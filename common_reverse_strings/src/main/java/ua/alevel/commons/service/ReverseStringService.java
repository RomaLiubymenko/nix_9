package ua.alevel.commons.service;

public interface ReverseStringService {

    String reverse(String str, boolean isAllStringReverse);

    String reverse(String str, String substring);

    String reverse(String str, int firstIndex, int lastIndex, boolean isAllStringReverse);
}
