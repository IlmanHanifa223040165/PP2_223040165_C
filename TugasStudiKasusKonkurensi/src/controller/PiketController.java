package controller;

import model.Piket;
import model.PiketDAO;

import java.util.List;

public class PiketController {
    private final PiketDAO piketDAO;

    public PiketController() {
        piketDAO = new PiketDAO();
    }

    public List<Piket> getAllPiket() {
        return piketDAO.getAll();
    }

    public void addPiket(Piket piket) {
        piketDAO.insert(piket);
    }

    public void updatePiket(Piket piket) {
        piketDAO.update(piket);
    }

    public void deletePiket(int id) {
        piketDAO.delete(id);
    }
}
