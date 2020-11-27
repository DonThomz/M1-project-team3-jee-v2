package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentDao implements DaoResource<Student> {

    private final EntityManager entityManager;

    public StudentDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<Student> findAll() throws DaoException {
        return entityManager.createQuery("SELECT c FROM Student c", Student.class).getResultList();
    }

    @Override
    public Student find(int id) throws DaoException {
        try {
            return entityManager.find(Student.class, id);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Student object) throws DaoException {
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

    public void saveAll(Student... objects) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            for (Student object : objects) {
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
    public void update(Student object) throws DaoException {
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
    public void updateAll(List<Student> objects) throws DaoException {
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
