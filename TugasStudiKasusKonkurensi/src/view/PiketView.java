package view;

import controller.PiketController;
import model.Piket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PiketView extends JFrame {
    private JTextField txtNama, txtTanggal, txtJabatan, txtKehadiran, txtCatatan, txtId;
    private JTable table;
    private DefaultTableModel tableModel;
    private PiketController controller;
    private JProgressBar progressBar;

    public PiketView() {
        controller = new PiketController();
        setTitle("Aplikasi Piket Organisasi");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(8, 2));

        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEnabled(false); // ID hanya untuk display, tidak bisa diubah manual
        panel.add(txtId);

        panel.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        panel.add(new JLabel("Tanggal:"));
        txtTanggal = new JTextField();
        panel.add(txtTanggal);

        panel.add(new JLabel("Jabatan:"));
        txtJabatan = new JTextField();
        panel.add(txtJabatan);

        panel.add(new JLabel("Kehadiran:"));
        txtKehadiran = new JTextField();
        panel.add(txtKehadiran);

        panel.add(new JLabel("Catatan:"));
        txtCatatan = new JTextField();
        panel.add(txtCatatan);

        // Tombol Tambah
        JButton btnAdd = new JButton("Tambah");
        btnAdd.addActionListener(e -> addData());
        panel.add(btnAdd);

        // Tombol Update
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> updateData());
        panel.add(btnUpdate);

        // Tombol Delete
        JButton btnDelete = new JButton("Hapus");
        btnDelete.addActionListener(e -> deleteData());
        panel.add(btnDelete);

        add(panel, BorderLayout.NORTH);

        // Progress Bar untuk menampilkan loading
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        add(progressBar, BorderLayout.SOUTH);

        // Tabel untuk menampilkan data
        tableModel = new DefaultTableModel(new String[] { "ID", "Nama", "Tanggal", "Jabatan", "Kehadiran", "Catatan" },
                0);
        table = new JTable(tableModel);
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
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Memuat data secara asinkron saat aplikasi dimulai
        loadTableDataAsync();
    }

    // Tambah Data
    private void addData() {
        Piket piket = new Piket();
        piket.setNama(txtNama.getText());
        piket.setTanggal(txtTanggal.getText());
        piket.setJabatan(txtJabatan.getText());
        piket.setKehadiran(txtKehadiran.getText());
        piket.setCatatan(txtCatatan.getText());
        controller.addPiket(piket);
        loadTableDataAsync();
        clearForm();
    }

    // Update Data
    private void updateData() {
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
        loadTableDataAsync();
        clearForm();
    }

    // Hapus Data
    private void deleteData() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        controller.deletePiket(id);
        loadTableDataAsync();
        clearForm();
    }

    // Memuat data secara asinkron menggunakan SwingWorker
    private void loadTableDataAsync() {
        progressBar.setValue(0);
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                tableModel.setRowCount(0); // Kosongkan tabel
                List<Piket> data = controller.getAllPiket();

                int progress = 0;
                int total = data.size();
                for (int i = 0; i < total; i++) {
                    Piket piket = data.get(i);
                    tableModel.addRow(
                            new Object[] { piket.getId(), piket.getNama(), piket.getTanggal(), piket.getJabatan(),
                                    piket.getKehadiran(), piket.getCatatan() });

                    progress = (i + 1) * 100 / total;
                    publish(progress);
                    try {
                        Thread.sleep(10); // Simulasi delay untuk visualisasi progress bar
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                // Perbarui progress bar
                progressBar.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                progressBar.setValue(100);
            }
        };
        worker.execute();
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
