package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.Student;

import java.util.List;

public class StudentDao extends DaoResource<Student> {

    public StudentDao(Database database) {
        this.database = database;
        this.entityManager = this.database.getConnection();
    }

    @Override
    public List<Student> findAll() throws DaoException {
        return entityManager.createQuery("SELECT c FROM Student c", Student.class).getResultList();
    }

    @Override
    public Student find(int id) throws DaoException {
        isOpen();
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

    public void saveAll(Student... objects) throws DaoException {
        isOpen();
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
    public void updateAll(List<Student> objects) throws DaoException {
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
