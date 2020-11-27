package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.Tutor;

import java.util.List;

public class TutorDao extends DaoResource<Tutor> {

    public TutorDao(Database database) {
        this.database = database;
        this.entityManager = this.database.getConnection();
    }

    @Override
    public List<Tutor> findAll() throws DaoException {
        isOpen();
        try {
            return entityManager.createQuery("SELECT c FROM Tutor c", Tutor.class).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Tutor find(int id) throws DaoException {
        isOpen();
        try {
            return entityManager.createQuery("select c from Tutor c where c.tutorId = :id", Tutor.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Tutor object) throws DaoException {
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

    public void saveAll(Tutor... objects) throws DaoException {
        isOpen();
        try {
            entityManager.getTransaction().begin();
            for (Tutor object : objects) {
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
    public void update(Tutor object) throws DaoException {
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
    public void updateAll(List<Tutor> objects) throws DaoException {
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

    public Tutor findByEmail(String email) throws DaoException {
        isOpen();
        try {
            return entityManager.createQuery("SELECT t FROM Tutor t WHERE t.email = :email", Tutor.class).setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }
}
