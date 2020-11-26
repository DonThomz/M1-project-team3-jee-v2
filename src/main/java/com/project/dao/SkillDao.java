package com.project.dao;

import com.project.database.Database;
import com.project.models.Skill;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class SkillDao implements DaoResource<Skill> {

    private final EntityManager entityManager;

    public SkillDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<Skill> findAll() {
        return entityManager.createQuery("SELECT c FROM Skill c", Skill.class).getResultList();
    }

    @Override
    public Skill find(int id) {
        return entityManager.find(Skill.class, id);
    }

    @Override
    public void save(Skill object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(Skill... objects) {
        entityManager.getTransaction().begin();
        for (Skill object : objects) {
            entityManager.persist(object);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Skill object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAll(List<Skill> objects) {
        entityManager.getTransaction().begin();
        objects.forEach(object -> {
            entityManager.merge(object);
        });
        entityManager.getTransaction().commit();
    }
}
