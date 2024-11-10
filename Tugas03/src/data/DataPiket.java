package data;

import java.sql.Date;

public class DataPiket {
    private int id;
    private String nama;
    private Date tanggal;
    private String jabatan;
    private String kehadiran;
    private String tugas;
    private String catatan;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTugas() {
        return tugas;
    }
    public void setTugas(String tugas) {
        this.tugas = tugas;
    }
    public String getCatatan() {
        return catatan;
    }
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public Date getTanggal() {
        return tanggal;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    public String getJabatan() {
        return jabatan;
    }
    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    public String getKehadiran() {
        return kehadiran;
    }
    public void setKehadiran(String kehadiran) {
        this.kehadiran = kehadiran;
    }
    
}
