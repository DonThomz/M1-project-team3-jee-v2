package com.project.dao;


import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.Intern;

import java.util.List;

/**
 * Service class to handle Intern table
 */
public class InternDao extends DaoResource<Intern> {

    public InternDao(Database database) {
        this.database = database;
        this.entityManager = this.database.getConnection();
    }

    @Override
    public List<Intern> findAll() throws DaoException {
        isOpen();
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
        isOpen();
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
        isOpen();
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
        isOpen();
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
        isOpen();
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
        isOpen();
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
