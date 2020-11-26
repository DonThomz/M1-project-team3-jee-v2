package com.project.dao;

import com.project.database.Database;
import com.project.models.Tutor;

import javax.persistence.EntityManager;
import java.util.List;

public class TutorDao implements DaoResource<Tutor> {

    private final EntityManager entityManager;

    public TutorDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<Tutor> findAll() {
        try {
            return entityManager.createQuery("SELECT c FROM Tutor c", Tutor.class).getResultList();
        } catch (Exception e) {
            System.err.printf("[%s] Find all : %s%n", this.getClass().getName(), e.getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public Tutor find(int id) {
        try {
            return entityManager.createQuery("select c from Tutor c where c.tutorId = :id", Tutor.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            System.err.printf("[%s] Find by Id : %s%n", this.getClass().getName(), e.getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public void save(Tutor object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.printf("[%s] Save : %s%n", this.getClass().getName(), e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void saveAll(Tutor... objects) {
        try {
            entityManager.getTransaction().begin();
            for (Tutor object : objects) {
                entityManager.persist(object);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.printf("[%s] Save all : %s%n", this.getClass().getName(), e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Tutor object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("[Tutor] Update all : " + e.getMessage());
        } finally {
            entityManager.close();
        }

    }

    @Override
    public void updateAll(List<Tutor> objects) {
        try {
            entityManager.getTransaction().begin();
            objects.forEach(entityManager::merge);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.printf("[Tutor] Update all : %s", e.getMessage());
        } finally {
            entityManager.close();
        }

    }

    public Tutor findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT t FROM Tutor t WHERE t.email = :email", Tutor.class).setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            System.err.printf("[%s] Find By Email : %s%n", this.getClass().getName(), e.getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }
}
