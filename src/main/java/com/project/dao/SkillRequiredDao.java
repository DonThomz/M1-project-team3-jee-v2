package com.project.dao;

import com.project.database.Database;
import com.project.models.SkillRequired;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class SkillRequiredDao implements DaoResource<SkillRequired> {

    private final EntityManager entityManager;

    public SkillRequiredDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<SkillRequired> findAll() {
        return entityManager.createQuery("SELECT c FROM SkillRequired c", SkillRequired.class).getResultList();
    }

    @Override
    public SkillRequired find(int id) {
        return entityManager.find(SkillRequired.class, id);
    }

    @Override
    public void save(SkillRequired object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(SkillRequired... objects) {
        entityManager.getTransaction().begin();
        for (SkillRequired object : objects) {
            entityManager.persist(object);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(SkillRequired object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAll(List<SkillRequired> objects) {
        entityManager.getTransaction().begin();
        objects.forEach(object -> {
            entityManager.merge(object);
        });
        entityManager.getTransaction().commit();
    }
}
