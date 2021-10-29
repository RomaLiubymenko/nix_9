package ua.com.alevel.tree;

import javax.swing.*;

public final class BinaryTreeForm extends JFrame {

    public BinaryTreeForm() {
        super("Binary Tree");
        BinaryTree tree = new BinaryTree();
        this.getContentPane().add(new BinaryTreePanel(tree));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(50, 50, 700, 700);
    }

    public void showPreviewMessageDialog() {
        JOptionPane.showMessageDialog(BinaryTreePanel.BinaryTreeFrame, """
                Welcome

                This program works typing some letters from your keyboard
                So, the operations you can use are:
                 a --- Add an integer number
                 f --- Add from file
                 s --- Search an integer number
                 d --- Delete an integer number
                 h --- Help (if you forgot this)
                 g --- Get max depth""");
    }
}
