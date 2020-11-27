package com.project.services;

import com.project.dao.SkillRequiredDao;
import com.project.exceptions.ServiceException;
import com.project.models.SkillRequired;

import java.util.List;

public class SkillRequiredService implements EntityService<SkillRequired> {

    private final SkillRequiredDao dao;

    public SkillRequiredService(SkillRequiredDao dao) {
        this.dao = dao;
    }

    @Override
    public List<SkillRequired> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
            return null;
        }
    }

    @Override
    public SkillRequired find(int id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(SkillRequired object) throws ServiceException {
        try {
            dao.save(object);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }

    @Override
    public void update(SkillRequired object) throws ServiceException {
        try {
            dao.update(object);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }

    @Override
    public void updateAll(List<SkillRequired> objects) throws ServiceException {
        try {
            dao.updateAll(objects);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }
}
