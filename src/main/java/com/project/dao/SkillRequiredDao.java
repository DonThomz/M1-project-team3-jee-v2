package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.SkillRequired;

import javax.persistence.EntityManager;
import java.util.List;

public class SkillRequiredDao implements DaoResource<SkillRequired> {

    private final EntityManager entityManager;

    public SkillRequiredDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<SkillRequired> findAll() throws DaoException {
        try {
            return entityManager.createQuery("SELECT c FROM SkillRequired c", SkillRequired.class).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public SkillRequired find(int id) throws DaoException {
        try {
            return entityManager.find(SkillRequired.class, id);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(SkillRequired object) throws DaoException {
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

    public void saveAll(SkillRequired... objects) throws DaoException {
        try {
            entityManager.getTransaction().begin();
            for (SkillRequired object : objects) {
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
    public void update(SkillRequired object) throws DaoException {
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
    public void updateAll(List<SkillRequired> objects) throws DaoException {
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
