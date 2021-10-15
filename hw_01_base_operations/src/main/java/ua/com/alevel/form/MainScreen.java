package ua.com.alevel.form;

import ua.com.alevel.entity.Task;
import ua.com.alevel.service.TaskService;

import javax.swing.*;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class MainScreen extends JFrame {
    private JPanel mainPanel;
    private JPanel panelWithTasks;
    private JLabel invitationLabel;
    private JButton goToSelectedTaskButton;
    private JComboBox<String> selectionTaskComboBox;
    private JLabel taskDescriptionLabel;
    private JPanel taskPanel;
    private JTextField inputTextField;
    private JLabel taskResultLabel;
    private JLabel currentTaskDescriptionLabel;
    private JButton resultButton;
    private JButton comeBackButton;
    private JButton clearResultButton;
    Map<Task, TaskService> tasks;
    private TaskService currentTaskService;

    public MainScreen(Map<Task, TaskService> tasks) {
        super("hw_01_base_operations");
        this.tasks = tasks;
        this.panelWithTasks.setVisible(true);
        this.taskPanel.setVisible(false);
        initTaskComboBox(tasks.keySet());
        listenComponents();
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    private void initTaskComboBox(Set<Task> tasks) {
        tasks.stream()
                .sorted(Comparator.comparing(Task::getId))
                .forEach(task -> {
                    if (task.getId() == 0) this.taskDescriptionLabel.setText(task.getDescription());
                    this.selectionTaskComboBox.addItem(task.getName());
                });
    }

    private void listenComponents() {
        this.selectionTaskComboBox.addActionListener(e -> {
            String selectedTaskName = (String) selectionTaskComboBox.getSelectedItem();
            Task currentTask = getCurrentTaskByName(selectedTaskName).get();
            taskDescriptionLabel.setText(currentTask.getDescription());
        });
        goToSelectedTaskButton.addActionListener(e -> {
            getCurrentTaskByName((String) this.selectionTaskComboBox.getSelectedItem()).ifPresent(task -> {
                this.panelWithTasks.setVisible(false);
                this.taskPanel.setVisible(true);
                currentTaskDescriptionLabel.setText(task.getDescription());
                this.currentTaskService = this.tasks.get(task);
            });
        });
        resultButton.addActionListener(e -> {
            String result = this.currentTaskService.getSolutionForTaskByString(this.inputTextField.getText());
            this.taskResultLabel.setText(result);
        });
        comeBackButton.addActionListener(e -> {
            this.currentTaskService = null;
            this.inputTextField.setText("");
            this.currentTaskDescriptionLabel.setText("");
            this.taskResultLabel.setText("");
            this.panelWithTasks.setVisible(true);
            this.taskPanel.setVisible(false);
        });
        clearResultButton.addActionListener(e -> {
            this.taskResultLabel.setText("");
        });
    }

    public Optional<Task> getCurrentTaskByName(String taskName) {
        return this.tasks.keySet()
                .stream()
                .filter(task -> task.getName().equals(taskName))
                .findFirst();
    }
}
