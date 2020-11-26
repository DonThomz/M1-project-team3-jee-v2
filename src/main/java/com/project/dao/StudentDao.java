package com.project.dao;

import com.project.database.Database;
import com.project.models.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentDao implements DaoResource<Student> {

    private final EntityManager entityManager;

    public StudentDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("SELECT c FROM Student c", Student.class).getResultList();
    }

    @Override
    public Student find(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void save(Student object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(Student... objects) {
        entityManager.getTransaction().begin();
        for (Student object : objects) {
            entityManager.persist(object);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Student object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAll(List<Student> objects) {
        entityManager.getTransaction().begin();
        objects.forEach(object -> {
            entityManager.merge(object);
        });
        entityManager.getTransaction().commit();
    }

}
