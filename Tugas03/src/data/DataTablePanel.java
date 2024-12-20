package data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public DataTablePanel() {
        setLayout(new BorderLayout());

        // Model tabel dengan kolom yang sesuai
        tableModel = new DefaultTableModel(new Object[]{"Nama", "Tanggal", "Jabatan", "Kehadiran", "Tugas", "Catatan"}, 0);
        table = new JTable(tableModel);

        // Menambahkan tabel ke panel dengan scroll
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Menambahkan baris data ke tabel
    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    // Bersihkan tabel
    public void clearTable() {
        tableModel.setRowCount(0);
    }
}
