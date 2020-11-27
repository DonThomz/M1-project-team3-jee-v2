package com.project.dao;


import com.project.database.Database;
import com.project.exceptions.DaoException;
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
    public List<Intern> findAll() throws DaoException {
        try {
            return entityManager.createQuery("SELECT c FROM Intern c", Intern.class).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Intern find(int id) throws DaoException {
        try {
            return entityManager.find(Intern.class, id);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Intern object) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }


    public void saveAll(Intern... objects) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            for (Intern object : objects) {
                entityManager.persist(object);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Intern object) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateAll(List<Intern> objects) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            objects.forEach(entityManager::merge);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }




}
