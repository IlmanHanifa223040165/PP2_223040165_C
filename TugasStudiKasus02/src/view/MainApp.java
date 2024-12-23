package view;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            PiketForm piketForm = new PiketForm();
            piketForm.setVisible(true); // Tampilkan form GUI
        });
    }
}
