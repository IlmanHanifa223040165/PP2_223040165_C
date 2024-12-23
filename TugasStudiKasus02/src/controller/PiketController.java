package controller;

import model.Piket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiketController {

    public List<Piket> getAllPiket() {
        List<Piket> piketList = new ArrayList<>();
        String query = "SELECT * FROM piket";

        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Piket piket = new Piket();
                piket.setId(resultSet.getInt("id"));
                piket.setNama(resultSet.getString("nama"));
                piket.setTanggal(resultSet.getString("tanggal"));
                piket.setJabatan(resultSet.getString("jabatan"));
                piket.setKehadiran(resultSet.getString("kehadiran"));
                piket.setCatatan(resultSet.getString("catatan"));
                piketList.add(piket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piketList;
    }

    public int addPiket(Piket piket) {
        int result = 0;
        String sql = "INSERT INTO piket (nama, tanggal, jabatan, kehadiran, catatan) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, piket.getNama());
            statement.setString(2, piket.getTanggal());
            statement.setString(3, piket.getJabatan());
            statement.setString(4, piket.getKehadiran());
            statement.setString(5, piket.getCatatan());
            result = statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePiket(Piket piket) {
        String query = "UPDATE piket SET nama = ?, tanggal = ?, jabatan = ?, kehadiran = ?, catatan = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, piket.getNama());
            statement.setString(2, piket.getTanggal());
            statement.setString(3, piket.getJabatan());
            statement.setString(4, piket.getKehadiran());
            statement.setString(5, piket.getCatatan());
            statement.setInt(6, piket.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePiket(int id) {
        String query = "DELETE FROM piket WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
