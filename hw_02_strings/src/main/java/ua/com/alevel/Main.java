package ua.com.alevel;

import ua.com.alevel.form.ReverseStringForm;
import ua.com.alevel.service.impl.ReverseStringServiceImpl;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            for (var info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReverseStringForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new ReverseStringForm(new ReverseStringServiceImpl()).setVisible(true);
        });
    }
}
