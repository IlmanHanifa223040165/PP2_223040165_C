package view.member;

import javax.swing.*;
import java.util.List;
import model.JenisMember;
import model.Member;
import dao.MemberDao;
import mapper.JenisMemberDao;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;
    private JTable table;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;

        // Muat data awal
        loadData();

        // Komponen Input
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 100, 20);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 100, 20);
        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 200, 30);
        populateComboJenis();

        // Tabel Data
        table = new JTable();
        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 550, 200);

        // Tombol
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 160, 100, 30);
        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(120, 160, 100, 30);
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(225, 160, 100, 30);

        // Tambahkan Komponen ke Frame
        this.setLayout(null);
        this.add(labelInput);
        this.add(textFieldNama);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(buttonSimpan);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(scrollableTable);

        // Listener
        buttonSimpan.addActionListener(new MemberButtonSimpanActionListener(this, memberDao));
        buttonUpdate.addActionListener(e -> updateMember());
        buttonDelete.addActionListener(e -> deleteMember());

        // Konfigurasi Frame
        this.setTitle("Manajemen Member");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
    
    }

    // Memuat data dari database
    private void loadData() {
        try {
            this.memberList = this.memberDao.findAll();
            this.jenisMemberList = this.jenisMemberDao.findAll();
        } catch (Exception e) {
            showAlert("Gagal memuat data: " + e.getMessage());
        }
    }

    // Mengisi data ke Combo Box
    public void populateComboJenis() {
        try {
            comboJenis.removeAllItems();
            for (JenisMember jenisMember : this.jenisMemberList) {
                comboJenis.addItem(jenisMember.getNama());
            }
        } catch (Exception e) {
            showAlert("Gagal memuat jenis member: " + e.getMessage());
        }
    }

    // Ambil input nama dari TextField
    public String getNama() {
        return textFieldNama.getText();
    }

    // Ambil jenis member yang dipilih dari Combo Box
    public JenisMember getJenisMember() {
        int selectedIndex = comboJenis.getSelectedIndex();
        if (selectedIndex >= 0) {
            return jenisMemberList.get(selectedIndex);
        }
        return null;
    }

    // Tambahkan member ke tabel
    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
        comboJenis.setSelectedIndex(0);
    }

    // Tampilkan alert
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Update Member
    private void updateMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showAlert("Pilih data yang ingin diupdate!");
            return;
        }

        try {
            Member member = memberList.get(selectedRow); // Ambil member dari list
            member.setNama(getNama());
            member.setJenisMember(getJenisMember());
            member.setJenisMemberId(member.getJenisMember().getId());

            memberDao.update(member); // Update ke database
            tableModel.update(selectedRow, member); // Update di tabel
            showAlert("Data berhasil diupdate!");
        } catch (Exception e) {
            showAlert("Gagal mengupdate data: " + e.getMessage());
        }
    }

    // Delete Member
    private void deleteMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showAlert("Pilih data yang ingin dihapus!");
            return;
        }

        try {
            Member member = memberList.get(selectedRow); // Ambil member dari list
            memberDao.delete(member); // Hapus dari database
            tableModel.remove(selectedRow); // Hapus dari tabel
            showAlert("Data berhasil dihapus!");
        } catch (Exception e) {
            showAlert("Gagal menghapus data: " + e.getMessage());
        }
    }
}
