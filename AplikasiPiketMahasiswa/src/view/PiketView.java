package view;

import controller.PiketController;
import model.Piket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PiketView extends JFrame {
    private JTextField txtNama, txtTanggal, txtJabatan, txtKehadiran, txtCatatan, txtId;
    private JTable table;
    private DefaultTableModel tableModel;
    private PiketController controller;

    public PiketView() {
        controller = new PiketController();
        setTitle("Aplikasi Piket Organisasi");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Form Input
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ID Field
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(new JLabel("ID:"));
        txtId = new JTextField(20);
        txtId.setEnabled(false); // ID hanya untuk display, tidak bisa diubah manual
        idPanel.add(txtId);
        formPanel.add(idPanel);

        // Nama Field
        JPanel namaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namaPanel.add(new JLabel("Nama:"));
        txtNama = new JTextField(20);
        namaPanel.add(txtNama);
        formPanel.add(namaPanel);

        // Tanggal Field
        JPanel tanggalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tanggalPanel.add(new JLabel("Tanggal:"));
        txtTanggal = new JTextField(20);
        tanggalPanel.add(txtTanggal);
        formPanel.add(tanggalPanel);

        // Jabatan Field
        JPanel jabatanPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jabatanPanel.add(new JLabel("Jabatan:"));
        txtJabatan = new JTextField(20);
        jabatanPanel.add(txtJabatan);
        formPanel.add(jabatanPanel);

        // Kehadiran Field
        JPanel kehadiranPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        kehadiranPanel.add(new JLabel("Kehadiran:"));
        txtKehadiran = new JTextField(20);
        kehadiranPanel.add(txtKehadiran);
        formPanel.add(kehadiranPanel);

        // Catatan Field
        JPanel catatanPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        catatanPanel.add(new JLabel("Catatan:"));
        txtCatatan = new JTextField(20);
        catatanPanel.add(txtCatatan);
        formPanel.add(catatanPanel);

        // Panel Tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAdd = new JButton("Tambah");
        btnAdd.addActionListener(this::addData);
        buttonPanel.add(btnAdd);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this::updateData);
        buttonPanel.add(btnUpdate);

        JButton btnDelete = new JButton("Hapus");
        btnDelete.addActionListener(this::deleteData);
        buttonPanel.add(btnDelete);

        formPanel.add(buttonPanel);
        add(formPanel, BorderLayout.NORTH);

        // Tabel Data
        tableModel = new DefaultTableModel(new String[] { "ID", "Nama", "Tanggal", "Jabatan", "Kehadiran", "Catatan" },
                0);
        table = new JTable(tableModel);

        // Listener untuk mengisi form berdasarkan data di tabel saat baris diklik
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtNama.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtTanggal.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtJabatan.setText(tableModel.getValueAt(selectedRow, 3).toString());
                txtKehadiran.setText(tableModel.getValueAt(selectedRow, 4).toString());
                txtCatatan.setText(tableModel.getValueAt(selectedRow, 5).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadTableData();
    }

    // Tambah Data
    private void addData(ActionEvent e) {
        Piket piket = new Piket();
        piket.setNama(txtNama.getText());
        piket.setTanggal(txtTanggal.getText());
        piket.setJabatan(txtJabatan.getText());
        piket.setKehadiran(txtKehadiran.getText());
        piket.setCatatan(txtCatatan.getText());
        controller.addPiket(piket);
        loadTableData();
        clearForm();
    }

    // Update Data
    private void updateData(ActionEvent e) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Piket piket = new Piket();
        piket.setId(Integer.parseInt(txtId.getText()));
        piket.setNama(txtNama.getText());
        piket.setTanggal(txtTanggal.getText());
        piket.setJabatan(txtJabatan.getText());
        piket.setKehadiran(txtKehadiran.getText());
        piket.setCatatan(txtCatatan.getText());
        controller.updatePiket(piket);
        loadTableData();
        clearForm();
    }

    // Hapus Data
    private void deleteData(ActionEvent e) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        controller.deletePiket(id);
        loadTableData();
        clearForm();
    }

    // Memuat data ke JTable
    private void loadTableData() {
        tableModel.setRowCount(0);
        List<Piket> data = controller.getAllPiket();
        for (Piket piket : data) {
            tableModel.addRow(new Object[] { piket.getId(), piket.getNama(), piket.getTanggal(), piket.getJabatan(),
                    piket.getKehadiran(), piket.getCatatan() });
        }
    }

    // Mengosongkan form input
    private void clearForm() {
        txtId.setText("");
        txtNama.setText("");
        txtTanggal.setText("");
        txtJabatan.setText("");
        txtKehadiran.setText("");
        txtCatatan.setText("");
    }
}
