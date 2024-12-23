package controller;

import model.MyBatisUtil;
import model.Piket;
import model.PiketMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PiketController {
    public List<Piket> getAllPiket() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PiketMapper mapper = session.getMapper(PiketMapper.class);
            return mapper.getAllPiket();
        }
    }

    public void addPiket(Piket piket) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PiketMapper mapper = session.getMapper(PiketMapper.class);
            mapper.insertPiket(piket);
            session.commit();
        }
    }

    public void updatePiket(Piket piket) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PiketMapper mapper = session.getMapper(PiketMapper.class);
            mapper.updatePiket(piket);
            session.commit();
        }
    }

    public void deletePiket(int id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PiketMapper mapper = session.getMapper(PiketMapper.class);
            mapper.deletePiket(id);
            session.commit();
        }
    }
}
