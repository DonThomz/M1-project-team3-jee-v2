package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.Skill;

import java.util.List;

public class SkillDao extends DaoResource<Skill> {

    public SkillDao(Database database) {
        this.database = database;
        this.entityManager = this.database.getConnection();
    }

    @Override
    public List<Skill> findAll() throws DaoException {
        isOpen();
        try {
            return entityManager.createQuery("SELECT c FROM Skill c", Skill.class).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Skill find(int id) throws DaoException {
        isOpen();
        try {
            return entityManager.find(Skill.class, id);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Skill object) throws DaoException {
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

    public void saveAll(Skill... objects) throws DaoException {
        isOpen();
        try {
            entityManager.getTransaction().begin();
            for (Skill object : objects) {
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
    public void update(Skill object) throws DaoException {
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
    public void updateAll(List<Skill> objects) throws DaoException {
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
