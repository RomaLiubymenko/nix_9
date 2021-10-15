package ua.com.alevel.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SecondTaskServiceImplTest {

    private SecondTaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        this.taskService = new SecondTaskServiceImpl();
    }

    @Test
    void shouldReturnEmptyResultString() {
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString(""));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("     "));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("1226697.7500-.12"));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("/.?,@#$%^&*()-= _+/.\\]|/*"));
    }

    @Test
    void shouldReturnCyrillicAndLatinAlphabetSymbolsWithEnteringInString() {
        Assertions.assertEquals("{a=4, e=1, f=2, w=1, ф=2, ъ=2, ї=3}", taskService.getSolutionForTaskByString("ффaa  we  ;' aa ъъ їїїff"));
        Assertions.assertEquals("{a=3, c=2, f=2, а=4, с=4}", taskService.getSolutionForTaskByString("12a54a*&^21a21аа221./5ffаассccсс"));
    }
}
