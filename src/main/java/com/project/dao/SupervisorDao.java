package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.Supervisor;

import java.util.List;

public class SupervisorDao extends DaoResource<Supervisor> {

    public SupervisorDao(Database database) {
        this.database = database;
        this.entityManager = this.database.getConnection();
    }

    @Override
    public List<Supervisor> findAll() throws DaoException {
        isOpen();
        try {
            return entityManager.createQuery("SELECT c FROM Supervisor c", Supervisor.class).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Supervisor find(int id) throws DaoException {
        isOpen();
        try {
            return entityManager.find(Supervisor.class, id);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Supervisor object) throws DaoException {
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

    public void saveAll(Supervisor... objects) throws DaoException {
        isOpen();
        try {
            entityManager.getTransaction().begin();
            for (Supervisor object : objects) {
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
    public void update(Supervisor object) throws DaoException {
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
    public void updateAll(List<Supervisor> objects) throws DaoException {
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
