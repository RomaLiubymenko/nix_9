package ua.com.alevel.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FirstTaskServiceImplTest {

    private FirstTaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        this.taskService = new FirstTaskServiceImpl();
    }

    @Test
    void shouldReturnEmptyResultString() {
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString(""));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("     "));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("avbbfdslorrewqw"));
        Assertions.assertEquals("Empty result", taskService.getSolutionForTaskByString("/.?,@#$%^&*()-= _+/.\\]|/*"));
    }

    @Test
    void shouldReturnSumOfNumberContainingInString() {
        Assertions.assertEquals("280.55", taskService.getSolutionForTaskByString("assas55ds/213.55////12"));
        Assertions.assertEquals("2.0", taskService.getSolutionForTaskByString("12.5as//|.,---+++-5.5vcx``~-5"));
    }
}
