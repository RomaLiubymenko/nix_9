package ua.com.alevel;

import ua.alevel.commons.entity.Task;
import ua.alevel.commons.form.MainScreen;
import ua.alevel.commons.service.TaskService;
import ua.com.alevel.tree.BinaryTreeForm;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JRadioButton firstLevelBtn;
    private JRadioButton secondLevelBtn;
    private ButtonGroup radioButtonGroup;
    private JFrame firstLevelFrame;
    private BinaryTreeForm binaryTreeFrame;

    public MainForm(String title, Map<Task, TaskService> tasks) {
        super(title);
        configureComponents();
        listenComponents();
        firstLevelFrame = new MainScreen("First module", tasks);
        binaryTreeFrame = new BinaryTreeForm();
        this.setContentPane(this.mainPanel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setResizable(false);
        this.setSize(width / 2, height / 2);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    private void configureComponents() {
        mainPanel = new JPanel();
        firstLevelBtn = new JRadioButton("<- Select first level");
        secondLevelBtn = new JRadioButton("<- Select second level");
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(firstLevelBtn);
        radioButtonGroup.add(secondLevelBtn);
        mainPanel.add(firstLevelBtn);
        mainPanel.add(secondLevelBtn);
    }

    private void listenComponents() {
        firstLevelBtn.addActionListener(actionListener -> {
            this.setVisible(false);
            firstLevelFrame.setVisible(true);
        });
        secondLevelBtn.addActionListener(actionListener -> {
            this.setVisible(false);
            binaryTreeFrame.setVisible(true);
            binaryTreeFrame.showPreviewMessageDialog();
        });
    }
}
