package panel;

import javax.swing.*;

import controller.AppController;
import data.DataPiket;
import data.DataTablePanel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class InputFormPanell extends JPanel {
    private JTextField txtNama;
    private JComboBox<String> cmbJabatan;
    private JRadioButton rbHadir, rbTidakHadir;
    private JCheckBox cbTugas1, cbTugas2;
    private JTextArea txtCatatan;
    private JSpinner spinnerTanggal;
    private JButton btnSimpan;
    private ButtonGroup bgKehadiran;
    private AppController appController;
    // Menyimpan instance dari DataTablePanel

    public InputFormPanell(DataTablePanel dataTablePanel, AppController appController) {
        this.appController = appController;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin untuk setiap komponen

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nama
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nama:"), gbc);

        txtNama = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(txtNama, gbc);

        // Tanggal Piket
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Tanggal Piket:"), gbc);

        spinnerTanggal = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggal, "dd-MM-yyyy");
        spinnerTanggal.setEditor(dateEditor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(spinnerTanggal, gbc);

        // Jabatan
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Jabatan:"), gbc);

        cmbJabatan = new JComboBox<>(new String[] { "Ketua", "Sekretaris", "Bendahara", "Anggota" });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(cmbJabatan, gbc);

        // Kehadiran
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Kehadiran:"), gbc);

        rbHadir = new JRadioButton("Hadir");
        rbTidakHadir = new JRadioButton("Tidak Hadir");
        bgKehadiran = new ButtonGroup();
        bgKehadiran.add(rbHadir);
        bgKehadiran.add(rbTidakHadir);

        JPanel panelKehadiran = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelKehadiran.add(rbHadir);
        panelKehadiran.add(rbTidakHadir);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(panelKehadiran, gbc);

        // Tugas
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Tugas:"), gbc);

        cbTugas1 = new JCheckBox("Bersih Ruangan");
        cbTugas2 = new JCheckBox("Susun Dokumen");

        JPanel panelTugas = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTugas.add(cbTugas1);
        panelTugas.add(cbTugas2);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(panelTugas, gbc);

        // Catatan
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Catatan:"), gbc);

        txtCatatan = new JTextArea(3, 20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(txtCatatan), gbc);

        // Tombol Simpan
        btnSimpan = new JButton("Simpan Data");
        btnSimpan.addActionListener(this::simpanData);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnSimpan, gbc);
    }

    private void simpanData(ActionEvent e) {
        // Ambil data dari form
        String nama = txtNama.getText();
        String tanggal = ((JSpinner.DateEditor) spinnerTanggal.getEditor()).getFormat()
                .format(spinnerTanggal.getValue());
        String jabatan = (String) cmbJabatan.getSelectedItem();
        String kehadiran = rbHadir.isSelected() ? "Hadir" : "Tidak Hadir";
        String tugas = (cbTugas1.isSelected() ? "Bersih Ruangan " : "") +
                (cbTugas2.isSelected() ? "Susun Dokumen" : "");
        String catatan = txtCatatan.getText();

        // Buat objek DataPiket untuk menyimpan data yang diinputkan
        DataPiket dataPiket = new DataPiket();

        // Tambahkan data ke controller
        appController.addDataToTable(dataPiket);

        // Reset form
        txtNama.setText("");
        bgKehadiran.clearSelection();
        cbTugas1.setSelected(false);
        cbTugas2.setSelected(false);
        txtCatatan.setText("");
    }

}
