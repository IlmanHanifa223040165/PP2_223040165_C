package main;

import javax.swing.*;

import data.DataPengelolaPiket;
import controller.AppController;
import data.DataTablePanel;
import panel.InputFormPanell;

public class MainFrame extends JFrame {
    private InputFormPanell inputFormPanel;
    private DataTablePanel dataTablePanel;
    private AppController appController;

    public MainFrame() {
        setTitle("Aplikasi Piket Pengurus Organisasi Mahasiswa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize panels
        DataPengelolaPiket dataPengelolaPiket = new DataPengelolaPiket();
        dataTablePanel = new DataTablePanel();
        appController = new AppController(dataPengelolaPiket, dataTablePanel);
        inputFormPanel = new InputFormPanell(dataTablePanel, appController);

        // Initialize Menu
        JMenuBar menuBar = new JMenuBar();

        JMenu menuPiket = new JMenu("Piket");
        JMenuItem menuItemInputData = new JMenuItem("Input Data Piket");
        JMenuItem menuItemLihatData = new JMenuItem("Lihat Data Piket");

        // Add action listeners
        menuItemInputData.addActionListener(e -> {
            setContentPane(inputFormPanel); // Switch to input panel
            revalidate();
        });

        menuItemLihatData.addActionListener(e -> {
            appController.loadAllDataToTable(); // Memuat data sebelum menampilkan tabel
            setContentPane(dataTablePanel); // Switch to table panel
            revalidate();
        });

        // Add menu items to menu
        menuPiket.add(menuItemInputData);
        menuPiket.add(menuItemLihatData);
        menuBar.add(menuPiket);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Set the default panel to inputFormPanel
        setContentPane(inputFormPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
