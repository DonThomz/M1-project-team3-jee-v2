package com.project.services;

import com.project.dao.SkillDao;
import com.project.models.Skill;

import java.util.List;

public class SkillService implements EntityService<Skill> {

    private final SkillDao dao;

    public SkillService(SkillDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Skill> findAll() {
        return dao.findAll();
    }

    @Override
    public Skill find(int id) {
        return dao.find(id);
    }

    @Override
    public void save(Skill object) {
        dao.save(object);
    }

    @Override
    public void saveAll(Skill... objects) {
        dao.saveAll(objects);
    }

    @Override
    public void update(Skill object) {
        dao.update(object);
    }

    @Override
    public void updateAll(List<Skill> objects) {
        dao.updateAll(objects);
    }
}
