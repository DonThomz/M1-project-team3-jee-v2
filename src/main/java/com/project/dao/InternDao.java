package com.project.dao;


import com.project.database.Database;
import com.project.models.Intern;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Service class to handle Intern table
 */
public class InternDao implements DaoResource<Intern> {

    private final EntityManager entityManager;

    public InternDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<Intern> findAll() {
        return entityManager.createQuery("SELECT c FROM Intern c", Intern.class).getResultList();
    }

    @Override
    public Intern find(int id) {
        return entityManager.find(Intern.class, id);
    }

    @Override
    public void save(Intern object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }


    @Override
    public void saveAll(Intern... objects) {
        entityManager.getTransaction().begin();
        for (Intern object : objects) {
            entityManager.persist(object);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Intern object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAll(List<Intern> objects) {
        entityManager.getTransaction().begin();
        objects.forEach(entityManager::merge);
        entityManager.getTransaction().commit();
    }




}
