package com.project.dao;

import com.project.database.Database;
import com.project.models.Supervisor;

import javax.persistence.EntityManager;
import java.util.List;

public class SupervisorDao implements DaoResource<Supervisor> {


    private final EntityManager entityManager;

    public SupervisorDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<Supervisor> findAll() {
        return entityManager.createQuery("SELECT c FROM Supervisor c", Supervisor.class).getResultList();
    }

    @Override
    public Supervisor find(int id) {
        return entityManager.find(Supervisor.class, id);
    }

    @Override
    public void save(Supervisor object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(Supervisor... objects) {
        entityManager.getTransaction().begin();
        for (Supervisor object : objects) {
            entityManager.persist(object);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Supervisor object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAll(List<Supervisor> objects) {
        entityManager.getTransaction().begin();
        objects.forEach(entityManager::merge);
        entityManager.getTransaction().commit();
    }
}
