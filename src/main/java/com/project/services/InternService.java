package com.project.services;


import com.project.dao.InternDao;
import com.project.models.Intern;
import com.project.models.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class to handle Intern table
 */
public class InternService implements EntityService<Intern> {

    private final InternDao dao;

    public InternService(InternDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Intern> findAll() throws SQLException {
        return dao.findAll();
    }

    @Override
    public Intern find(int id) {
        return dao.find(id);
    }

    @Override
    public void save(Intern object) {
        dao.save(object);
    }

    @Override
    public void saveAll(Intern... objects) {
        dao.saveAll(objects);
    }

    @Override
    public void update(Intern object) {
        dao.update(object);
    }

    @Override
    public void updateAll(List<Intern> objects) throws SQLException {
        dao.updateAll(objects);
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

        fields.forEach(f -> intern.setAttributeFromJsonKey((JSONObject) f));

        return intern;
    }

}
