package com.project.services;

import com.project.dao.TutorDao;
import com.project.models.Tutor;

import java.util.List;

public class TutorService implements EntityService<Tutor> {

    private final TutorDao dao;

    public TutorService(TutorDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Tutor> findAll() {
        return dao.findAll();
    }

    @Override
    public Tutor find(int id) {
        return dao.find(id);
    }

    @Override
    public void save(Tutor object) {
        dao.save(object);
    }

    @Override
    public void saveAll(Tutor... objects) {
        dao.saveAll(objects);
    }

    @Override
    public void update(Tutor object) {
        dao.update(object);
    }

    @Override
    public void updateAll(List<Tutor> objects) {
        dao.updateAll(objects);
    }

    public Tutor findByEmail(String email) {
        return dao.findByEmail(email);
    }
}
