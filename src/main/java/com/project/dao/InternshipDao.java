package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.Internship;
import com.project.models.Tutor;

import javax.persistence.EntityManager;
import java.util.List;

public class InternshipDao implements DaoResource<Internship> {

    private final EntityManager entityManager;

    public InternshipDao(Database database) {
        this.entityManager = database.getConnection();
    }


    @Override
    public List<Internship> findAll() throws DaoException {
        try {
            return entityManager.createQuery("SELECT c FROM Internship c", Internship.class).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Internship find(int id) throws DaoException {
        try {
            return entityManager.find(Internship.class, id);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void save(Internship object) throws DaoException {
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

    public void saveAll(Internship... objects) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            for (Internship object : objects) {
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
    public void update(Internship object) throws DaoException {
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
    public void updateAll(List<Internship> objects) throws DaoException {
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

    public void remove(int id) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            Internship internship = entityManager.find(Internship.class, id);
            entityManager.remove(internship);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    // =======================
    // Custom Queries Methods
    // =======================

    public List<Internship> findInternshipsByTutorId(Tutor tutor) throws DaoException {
        try {
            return entityManager.createQuery("select i from Internship i where i.intern.tutor = :tutor_id", Internship.class)
                    .setParameter("tutor_id", tutor).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    public List<Internship> findByYear(Tutor tutor, int year) throws DaoException {
        try {
            return entityManager.createQuery("select i from Internship i where i.intern.tutor = :tutor and function('year', i.startDate) = :year", Internship.class)
                    .setParameter("tutor", tutor).setParameter("year", year).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    public Internship findInternshipByTutorId(Tutor tutor) throws DaoException {
        try {
            return entityManager.createQuery("select i from Internship i where i.intern.tutor = :tutor_id", Internship.class)
                    .setParameter("tutor_id", tutor).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    // =======================
    // Custom Queries Methods
    // =======================
}
