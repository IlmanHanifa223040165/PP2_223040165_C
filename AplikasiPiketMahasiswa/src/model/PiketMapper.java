package model;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PiketMapper {
     // Mengambil semua data dari tabel piket
     @Select("SELECT * FROM piket")
     List<Piket> getAllPiket();

     // Menambahkan data baru ke tabel piket
     @Insert("INSERT INTO piket (nama, tanggal, jabatan, kehadiran, catatan) " +
               "VALUES (#{nama}, #{tanggal}, #{jabatan}, #{kehadiran}, #{catatan})")
     void insertPiket(Piket piket);

     // Memperbarui data yang ada di tabel piket
     @Update("UPDATE piket SET nama=#{nama}, tanggal=#{tanggal}, jabatan=#{jabatan}, kehadiran=#{kehadiran}, catatan=#{catatan} "
               +
               "WHERE id=#{id}")
     void updatePiket(Piket piket);

     // Menghapus data dari tabel piket berdasarkan ID
     @Delete("DELETE FROM piket WHERE id=#{id}")
     void deletePiket(int id);
}
