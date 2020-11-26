package com.project.services;

import com.project.dao.CompanyDao;
import com.project.models.Company;

import java.sql.SQLException;
import java.util.List;

public class CompanyService implements EntityService<Company> {

    private final CompanyDao dao;

    public CompanyService(CompanyDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Company> findAll() {
        return dao.findAll();
    }

    @Override
    public Company find(int id) {
        return dao.find(id);
    }

    @Override
    public void save(Company object) {
        dao.save(object);
    }

    @Override
    public void saveAll(Company... objects) {
        dao.saveAll(objects);
    }

    @Override
    public void update(Company object) {
        dao.update(object);
    }

    @Override
    public void updateAll(List<Company> objects) throws SQLException {
        dao.updateAll(objects);
    }

}
