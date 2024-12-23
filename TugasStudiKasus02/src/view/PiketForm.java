package view;

import controller.PiketController;
import model.Piket;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PiketForm extends JFrame {
    private JTextField txtNama, txtTanggal, txtJabatan, txtKehadiran, txtCatatan;
    private JTable table;
    private DefaultTableModel tableModel;
    private PiketController piketController;

    public PiketForm() {
        piketController = new PiketController();

        setTitle("Aplikasi Piket Organisasi Mahasiswa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Form Input
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        formPanel.add(txtNama);

        formPanel.add(new JLabel("Tanggal:"));
        txtTanggal = new JTextField();
        formPanel.add(txtTanggal);

        formPanel.add(new JLabel("Jabatan:"));
        txtJabatan = new JTextField();
        formPanel.add(txtJabatan);

        formPanel.add(new JLabel("Kehadiran:"));
        txtKehadiran = new JTextField();
        formPanel.add(txtKehadiran);

        formPanel.add(new JLabel("Catatan:"));
        txtCatatan = new JTextField();
        formPanel.add(txtCatatan);

        // Tombol
        JButton btnAdd = new JButton("Tambah");
        btnAdd.addActionListener(this::handleAdd);
        formPanel.add(btnAdd);

        JButton btnUpdate = new JButton("Perbarui");
        btnUpdate.addActionListener(this::handleUpdate);
        formPanel.add(btnUpdate);

        JButton btnDelete = new JButton("Hapus");
        btnDelete.addActionListener(this::handleDelete);
        formPanel.add(btnDelete);

        add(formPanel, BorderLayout.NORTH);

        // JTable
        tableModel = new DefaultTableModel(new String[] { "ID", "Nama", "Tanggal", "Jabatan", "Kehadiran", "Catatan" },
                0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadTableData();
    }

    private void handleAdd(ActionEvent e) {
        Piket piket = new Piket();
        piket.setNama(txtNama.getText());
        piket.setTanggal(txtTanggal.getText());
        piket.setJabatan(txtJabatan.getText());
        piket.setKehadiran(txtKehadiran.getText());
        piket.setCatatan(txtCatatan.getText());

        if (txtNama.getText().isEmpty() || txtTanggal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama dan Tanggal wajib diisi!", "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (piketController.addPiket(piket) > 0) {
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        loadTableData();
    }

    private void handleUpdate(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Piket piket = new Piket();
            piket.setId((int) tableModel.getValueAt(selectedRow, 0));
            piket.setNama(txtNama.getText());
            piket.setTanggal(txtTanggal.getText());
            piket.setJabatan(txtJabatan.getText());
            piket.setKehadiran(txtKehadiran.getText());
            piket.setCatatan(txtCatatan.getText());
            piketController.updatePiket(piket);
            loadTableData();
        }
    }

    private void handleDelete(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            piketController.deletePiket(id);
            loadTableData();
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        List<Piket> piketList = piketController.getAllPiket();
        for (Piket piket : piketList) {
            tableModel.addRow(new Object[] {
                    piket.getId(),
                    piket.getNama(),
                    piket.getTanggal(),
                    piket.getJabatan(),
                    piket.getKehadiran(),
                    piket.getCatatan()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PiketForm form = new PiketForm();
            form.setVisible(true);
        });
    }
}
