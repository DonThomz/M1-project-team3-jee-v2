package com.project.services;

import com.project.dao.SupervisorDao;
import com.project.models.Supervisor;

import java.sql.SQLException;
import java.util.List;

public class SupervisorService implements EntityService<Supervisor> {


    private final SupervisorDao dao;

    public SupervisorService(SupervisorDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Supervisor> findAll() {
        return dao.findAll();
    }

    @Override
    public Supervisor find(int id) {
        return dao.find(id);
    }

    @Override
    public void save(Supervisor object) {
        dao.save(object);
    }

    @Override
    public void saveAll(Supervisor... objects) {
        dao.saveAll(objects);
    }

    @Override
    public void update(Supervisor object) {
        dao.update(object);
    }

    @Override
    public void updateAll(List<Supervisor> objects) throws SQLException {
        dao.updateAll(objects);
    }

}
