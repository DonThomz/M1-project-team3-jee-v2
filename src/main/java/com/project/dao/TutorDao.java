package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.Tutor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

public class TutorDao implements DaoResource<Tutor> {

    private static final Logger logger = Logger.getLogger(TutorDao.class.getName());

    private final EntityManager entityManager;

    public TutorDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<Tutor> findAll() throws DaoException {
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
        try {
            return entityManager.createQuery("SELECT t FROM Tutor t WHERE t.email = :email", Tutor.class).setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }
}
