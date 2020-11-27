package com.project.services;

import com.project.dao.MissionDao;
import com.project.exceptions.ServiceException;
import com.project.models.Mission;

import java.util.List;

public class MissionService implements EntityService<Mission> {

    private final MissionDao dao;

    public MissionService(MissionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Mission> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
            return null;
        }
    }

    @Override
    public Mission find(int id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Mission object) throws ServiceException {
        try {
            dao.save(object);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }

    @Override
    public void update(Mission object) throws ServiceException {
        try {
            dao.update(object);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }

    @Override
    public void updateAll(List<Mission> objects) throws ServiceException {
        try {
            dao.updateAll(objects);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }
}
