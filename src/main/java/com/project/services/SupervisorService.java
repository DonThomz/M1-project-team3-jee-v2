package com.project.services;

import com.project.dao.SupervisorDao;
import com.project.exceptions.ServiceException;
import com.project.models.Supervisor;

import java.util.List;

public class SupervisorService implements EntityService<Supervisor> {


    private final SupervisorDao dao;

    public SupervisorService(SupervisorDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Supervisor> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
            return null;
        }
    }

    @Override
    public Supervisor find(int id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Supervisor object) throws ServiceException {
        try {
            dao.save(object);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }

    @Override
    public void update(Supervisor object) throws ServiceException {
        try {
            dao.update(object);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }

    @Override
    public void updateAll(List<Supervisor> objects) throws ServiceException {
        try {
            dao.updateAll(objects);
        } catch (com.project.exceptions.DaoException daoException) {
            daoException.printStackTrace();
        }
    }

}
