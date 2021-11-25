package ua.com.alevel;

import ua.com.alevel.controller.MainControllerForm;
import ua.com.alevel.service.impl.CarServiceImpl;
import ua.com.alevel.service.impl.OwnerServiceImpl;

public class Main {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainControllerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new MainControllerForm(new CarServiceImpl(), new OwnerServiceImpl(), "hw_05_io_nio").setVisible(true));
    }
}
