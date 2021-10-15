package ua.com.alevel;

import ua.com.alevel.entity.Task;
import ua.com.alevel.form.MainScreen;
import ua.com.alevel.service.TaskService;
import ua.com.alevel.service.implementation.FirstTaskServiceImpl;
import ua.com.alevel.service.implementation.SecondTaskServiceImpl;
import ua.com.alevel.service.implementation.ThirdTaskServiceImpl;

import java.awt.*;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Task, TaskService> tasks = Map.of(
                new Task(0, "First task", "Some description for First task"), new FirstTaskServiceImpl(),
                new Task(1, "Second task", "Some description for Second task"), new SecondTaskServiceImpl(),
                new Task(2, "Third task", "Some description for Third task"), new ThirdTaskServiceImpl()
        );
        EventQueue.invokeLater(() -> new MainScreen(tasks).setVisible(true));
    }
}
