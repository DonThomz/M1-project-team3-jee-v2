package com.project.services;

import com.project.dao.SkillRequiredDao;
import com.project.models.SkillRequired;

import java.sql.SQLException;
import java.util.List;

public class SkillRequiredService implements EntityService<SkillRequired> {

    private final SkillRequiredDao dao;

    public SkillRequiredService(SkillRequiredDao dao) {
        this.dao = dao;
    }

    @Override
    public List<SkillRequired> findAll() {
        return dao.findAll();
    }

    @Override
    public SkillRequired find(int id) {
        return dao.find(id);
    }

    @Override
    public void save(SkillRequired object) {
        dao.save(object);
    }

    @Override
    public void saveAll(SkillRequired... objects) {
        dao.saveAll(objects);
    }

    @Override
    public void update(SkillRequired object) {
        dao.update(object);
    }

    @Override
    public void updateAll(List<SkillRequired> objects) throws SQLException {
        dao.updateAll(objects);
    }
}
