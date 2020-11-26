package com.project.dao;


import com.project.database.Database;
import com.project.models.Intern;
import com.project.models.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Service class to handle Intern table
 */
public class InternDao implements DaoResource<Intern> {

    private final EntityManager entityManager;

    public InternDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<Intern> findAll() {
        return entityManager.createQuery("SELECT c FROM Intern c", Intern.class).getResultList();
    }

    @Override
    public Intern find(int id) {
        return entityManager.find(Intern.class, id);
    }

    @Override
    public void save(Intern object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }


    @Override
    public void saveAll(Intern... objects) {
        entityManager.getTransaction().begin();
        for (Intern object : objects) {
            entityManager.persist(object);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Intern object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAll(List<Intern> objects) {
        entityManager.getTransaction().begin();
        objects.forEach(object -> {
            entityManager.merge(object);
        });
        entityManager.getTransaction().commit();
    }


    /**
     * Mapping a jsonObject to an intern object
     *
     * @param internJson the intern json object
     * @return Intern object
     */
    public Intern mappingJson(JSONObject internJson) {
        Intern intern = new Intern();
        intern.setInternId(internJson.getInt("id"));
        intern.setStudent(new Student());
        intern.getStudent().setStudentId(internJson.getInt("studentId"));
        JSONArray fields = internJson.getJSONArray("fields");

        fields.forEach(f -> {
            intern.setAttributeFromJsonKey((JSONObject) f);
        });

        return intern;
    }

}
