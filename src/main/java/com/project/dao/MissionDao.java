package com.project.dao;

import com.project.database.Database;
import com.project.models.Mission;

import javax.persistence.EntityManager;
import java.util.List;

public class MissionDao implements DaoResource<Mission> {

    private final EntityManager entityManager;

    public MissionDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<Mission> findAll() {
        return entityManager.createQuery("SELECT c FROM Mission c", Mission.class).getResultList();
    }

    @Override
    public Mission find(int id) {
        return entityManager.find(Mission.class, id);
    }

    @Override
    public void save(Mission object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(Mission... objects) {
        entityManager.getTransaction().begin();
        for (Mission object : objects) {
            entityManager.persist(object);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Mission object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAll(List<Mission> objects) {
        entityManager.getTransaction().begin();
        objects.forEach(entityManager::merge);
        entityManager.getTransaction().commit();
    }
}
