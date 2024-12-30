package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiketDAO {
    private final Connection connection;

    public PiketDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Piket> getAll() {
        List<Piket> piketList = new ArrayList<>();
        String query = "SELECT * FROM piket";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Piket piket = new Piket();
                piket.setId(rs.getInt("id"));
                piket.setNama(rs.getString("nama"));
                piket.setTanggal(rs.getString("tanggal"));
                piket.setJabatan(rs.getString("jabatan"));
                piket.setKehadiran(rs.getString("kehadiran"));
                piket.setCatatan(rs.getString("catatan"));
                piketList.add(piket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piketList;
    }

    public void insert(Piket piket) {
        String query = "INSERT INTO piket (nama, tanggal, jabatan, kehadiran, catatan) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, piket.getNama());
            pstmt.setString(2, piket.getTanggal());
            pstmt.setString(3, piket.getJabatan());
            pstmt.setString(4, piket.getKehadiran());
            pstmt.setString(5, piket.getCatatan());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Piket piket) {
        String query = "UPDATE piket SET nama = ?, tanggal = ?, jabatan = ?, kehadiran = ?, catatan = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, piket.getNama());
            pstmt.setString(2, piket.getTanggal());
            pstmt.setString(3, piket.getJabatan());
            pstmt.setString(4, piket.getKehadiran());
            pstmt.setString(5, piket.getCatatan());
            pstmt.setInt(6, piket.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM piket WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
