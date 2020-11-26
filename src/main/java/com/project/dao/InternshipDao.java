package com.project.dao;

import com.project.database.Database;
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
    public List<Internship> findAll() {
        return entityManager.createQuery("SELECT c FROM Internship c", Internship.class).getResultList();
    }

    @Override
    public Internship find(int id) {
        return entityManager.find(Internship.class, id);
    }


    @Override
    public void save(Internship object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(Internship... objects) {
        entityManager.getTransaction().begin();
        for (Internship object : objects) {
            entityManager.persist(object);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Internship object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAll(List<Internship> objects) {
        entityManager.getTransaction().begin();
        objects.forEach(entityManager::merge);
        entityManager.getTransaction().commit();
    }

    public void remove(int id) {
        entityManager.getTransaction().begin();
        Internship internship = entityManager.find(Internship.class, id);
        entityManager.remove(internship);
        entityManager.getTransaction().commit();
    }

    // =======================
    // Custom Queries Methods
    // =======================

    public List<Internship> findInternshipsByTutorId(Tutor tutor) {
        return entityManager.createQuery("select i from Internship i where i.intern.tutor = :tutor_id", Internship.class)
                .setParameter("tutor_id", tutor).getResultList();
    }

    public List<Internship> findByYear(Tutor tutor, int year) {
        return entityManager.createQuery("select i from Internship i where i.intern.tutor = :tutor and function('year', i.startDate) = :year", Internship.class)
                .setParameter("tutor", tutor).setParameter("year", year).getResultList();
    }

    public Internship findInternshipByTutorId(Tutor tutor) {
        return entityManager.createQuery("select i from Internship i where i.intern.tutor = :tutor_id", Internship.class)
                .setParameter("tutor_id", tutor).getSingleResult();
    }

    // =======================
    // Custom Queries Methods
    // =======================
}
