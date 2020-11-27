package com.project.services;


import com.project.dao.InternDao;
import com.project.exceptions.ServiceException;
import com.project.models.Intern;
import com.project.models.Student;
import org.json.JSONArray;
import org.json.JSONObject;

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
    public List<Intern> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
            return null;
        }
    }

    @Override
    public Intern find(int id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Intern object) throws ServiceException {
        try {
            dao.save(object);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }

    @Override
    public void update(Intern object) throws ServiceException {
        try {
            dao.update(object);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }

    @Override
    public void updateAll(List<Intern> objects) throws ServiceException {
        try {
            dao.updateAll(objects);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
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
