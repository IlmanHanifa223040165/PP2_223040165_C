import view.PiketView;

public class MainApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new PiketView().setVisible(true);
        });
    }
}
