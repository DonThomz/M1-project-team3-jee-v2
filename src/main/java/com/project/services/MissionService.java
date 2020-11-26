package com.project.services;

import com.project.dao.MissionDao;
import com.project.models.Mission;

import java.sql.SQLException;
import java.util.List;

public class MissionService implements EntityService<Mission> {

    private final MissionDao dao;

    public MissionService(MissionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Mission> findAll() {
        return dao.findAll();
    }

    @Override
    public Mission find(int id) {
        return dao.find(id);
    }

    @Override
    public void save(Mission object) {
        dao.save(object);
    }

    @Override
    public void saveAll(Mission... objects) {
        dao.saveAll(objects);
    }

    @Override
    public void update(Mission object) {
        dao.update(object);
    }

    @Override
    public void updateAll(List<Mission> objects) throws SQLException {
        dao.updateAll(objects);
    }
}
