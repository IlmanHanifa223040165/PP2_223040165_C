package controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.DataPengelolaPiket;
import data.DataPiket;
import data.DataTablePanel;
import db.DatabaseConnection;

public class AppController {
    private DataPengelolaPiket dataPengelolaPiket;
    private DataTablePanel dataTablePanel;

    public int insert(DataPiket dataPiket) {
        int result = -1;

        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO data_piket (nama, tanggal, jabatan, kehadiran, tugas, catatan) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, dataPiket.getNama());
            statement.setDate(2, dataPiket.getTanggal());
            statement.setString(3, dataPiket.getJabatan());
            statement.setString(4, dataPiket.getKehadiran());
            statement.setString(5, dataPiket.getTugas());
            statement.setString(6, dataPiket.getCatatan());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public int update(DataPiket dataPiket) {
        int result = -1;
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE data_piket SET nama = ?, tanggal = ?, jabatan = ?, kehadiran = ?, tugas = ?, catatan = ? WHERE id = ?");
            statement.setString(1, dataPiket.getNama());
            statement.setDate(2, dataPiket.getTanggal());
            statement.setString(3, dataPiket.getJabatan());
            statement.setString(4, dataPiket.getKehadiran());
            statement.setString(5, dataPiket.getTugas());
            statement.setString(6, dataPiket.getCatatan());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public int delete(DataPiket dataPiket) {
        int result = -1;
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM data_piket WHERE id = ?");
            statement.setInt(1, dataPiket.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public List<DataPiket> findAll() {
        List<DataPiket> list = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM data_piket")) {
                while (resultSet.next()) {
                    DataPiket dataPiket = new DataPiket();
                    dataPiket.setId(resultSet.getInt("id"));
                    dataPiket.setNama(resultSet.getString("nama"));
                    dataPiket.setTanggal(resultSet.getDate("tanggal"));
                    dataPiket.setJabatan(resultSet.getString("jabatan"));
                    dataPiket.setKehadiran(resultSet.getString("kehadiran"));
                    dataPiket.setTugas(resultSet.getString("tugas"));
                    dataPiket.setCatatan(resultSet.getString("catatan"));
                    list.add(dataPiket);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public AppController(DataPengelolaPiket dataPengelolaPiket, DataTablePanel dataTablePanel) {
        this.dataPengelolaPiket = dataPengelolaPiket;
        this.dataTablePanel = dataTablePanel;
    }

    public void addDataToTable(DataPiket dataPiket) {
        // Menyimpan data ke dataPengelolaPiket
        dataPengelolaPiket.addDataPiket(dataPiket);

        // Tambahkan data ke tabel
        dataTablePanel.addRow(new Object[] {
                dataPiket.getNama(),
                dataPiket.getTanggal(),
                dataPiket.getJabatan(),
                dataPiket.getKehadiran(),
                dataPiket.getTugas(),
                dataPiket.getCatatan()
        });
    }

    // Method untuk mengisi ulang data dari dataPengelolaPiket ke tabel
    public void loadAllDataToTable() {
        dataTablePanel.clearTable(); // Bersihkan tabel sebelum memuat data baru
        for (DataPiket dataPiket : dataPengelolaPiket.getAllDataPiket()) {
            dataTablePanel.addRow(new Object[] {
                    dataPiket.getNama(),
                    dataPiket.getTanggal(),
                    dataPiket.getJabatan(),
                    dataPiket.getKehadiran(),
                    dataPiket.getTugas(),
                    dataPiket.getCatatan()
            });
        }
    }
}
