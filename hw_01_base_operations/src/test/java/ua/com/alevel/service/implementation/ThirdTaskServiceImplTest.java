package ua.com.alevel.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThirdTaskServiceImplTest {
    ThirdTaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        this.taskService = new ThirdTaskServiceImpl();
    }

    @Test
    void shouldReturnEmptyResultString() {
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString(""));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("     "));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("1226697.7500-.12"));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("/.?,@#$%^&*()-= _+/.\\]|/*"));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("11"));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("11 44"));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("0"));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("00"));
    }

    @Test
    void shouldReturnCyrillicAndLatinAlphabetSymbolsWithEnteringInString() {
        Assertions.assertEquals("9 45", taskService.getSolutionForTaskByString("1"));
        Assertions.assertEquals("10 35", taskService.getSolutionForTaskByString("2"));
        Assertions.assertEquals("11 35", taskService.getSolutionForTaskByString("3"));
        Assertions.assertEquals("13 25", taskService.getSolutionForTaskByString("5"));
        Assertions.assertEquals("14 15", taskService.getSolutionForTaskByString("6"));
        Assertions.assertEquals("17 55", taskService.getSolutionForTaskByString("10"));
    }
}
