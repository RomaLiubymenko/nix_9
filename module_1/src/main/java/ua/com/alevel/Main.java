package ua.com.alevel;

import ua.alevel.commons.entity.Task;
import ua.alevel.commons.service.TaskService;
import ua.com.alevel.service.impl.KnightMovingOnChessboardTaskServiceImpl;
import ua.com.alevel.service.impl.TriangleAreaTaskServiceImpl;
import ua.com.alevel.service.impl.UniqueNumberTaskServiceImpl;

import javax.swing.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String firstTaskDescription = """
                <html>
                  <p>Enter integer numbers separated by a space and click on "Get result"</p><br>
                  <p>Example: "1 5 6 9 6 1"</p><br>
                  <p>As a result, you will get the count of unique numbers in the string</p>
                 </html>
                 """;
        String secondTaskDescription = """
                <html>
                  <p>Enter 3 points of the triangle</p><br>
                  <p>Each with which is enclosed in square brackets</p><br>
                  <p>Point coordinates must be entered separated by a space and click on "Get result"</p><br>
                  <p>Example: "[0 0] [1 0] [0 1]"</p><br>
                  <p>As a result, you will get triangle area</p>
                 </html>
                """;
        String thirdTaskDescription = """
                <html>
                  <p>Enter the current position of the knight and the cell to which you want to move it in 1 move"</p><br>
                  <p>and click on "Get result"</p><br>
                  <p>Example: "[1 1] [3 6]"</p><br>
                  <p>As a result, the program will answer whether it is possible to move the knight to this cell</p>
                 </html>
                """;
        Map<Task, TaskService> tasks = Map.of(
                new Task(0, "Search unique numbers count in string", firstTaskDescription), new UniqueNumberTaskServiceImpl(),
                new Task(1, "Triangle area by points", secondTaskDescription), new TriangleAreaTaskServiceImpl(),
                new Task(2, "Knight's move on an endless chessboard", thirdTaskDescription), new KnightMovingOnChessboardTaskServiceImpl()
        );
        SwingUtilities.invokeLater(() -> {
            new MainForm("First module", tasks).setVisible(true);
        });
    }
}
