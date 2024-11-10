package data;

import java.util.ArrayList;
import java.util.List;

public class DataPengelolaPiket {
    private List<DataPiket> dataPiketList = new ArrayList<>();

    public void addDataPiket(DataPiket dataPiket) {
        dataPiketList.add(dataPiket);
    }

    public List<DataPiket> getAllDataPiket() {
        return dataPiketList;
    }

    // Implementasi lain jika perlu, seperti hapus data atau simpan ke file
}
