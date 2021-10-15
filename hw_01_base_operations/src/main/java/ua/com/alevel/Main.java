package ua.com.alevel;

import ua.com.alevel.entity.Task;
import ua.com.alevel.form.MainScreen;
import ua.com.alevel.service.TaskService;
import ua.com.alevel.service.implementation.FirstTaskServiceImpl;
import ua.com.alevel.service.implementation.SecondTaskServiceImpl;
import ua.com.alevel.service.implementation.ThirdTaskServiceImpl;

import javax.swing.*;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String commonMessage = "<html><p>Enter a string in the input field and click on \"Get result\".</p><br>";
        String firstTaskDescription = "<p>As a result, you should get the sum of all the numbers in the line.</p><br>" +
                "<p>If no numbers are found in the string - \"Empty result\"</p><br>";
        String secondTaskDescription = "<p>As a result, you should get sorted characters with the number of occurrences " +
                "<br>of each character in the string.</p><br>" +
                "<p>If no letters are found in the string - \"Empty result\"</p><br>";
        String thirdTaskDescription = "<html><p>In some school, classes start at 9:00.<br>" +
                "<p>The duration of the lesson is 45 minutes, after the 1st, 3rd, 5th, etc. lessons,<br>" +
                "a break of 5 minutes, and after the 2nd, 4th, 6th, etc. - 15 minutes.<br>" +
                "Enter a number of lesson from 1-10 in the input field and click on \"Get result\".<br>" +
                "As a result, you will get the end time of this lesson</p><br>";
        Map<Task, TaskService> tasks = Map.of(
                new Task(0, "First task", commonMessage + firstTaskDescription), new FirstTaskServiceImpl(),
                new Task(1, "Second task", commonMessage + secondTaskDescription), new SecondTaskServiceImpl(),
                new Task(2, "Third task", thirdTaskDescription), new ThirdTaskServiceImpl()
        );
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new MainScreen("hw_01_base_operations", tasks);
            mainFrame.setVisible(true);
        });
    }
}
