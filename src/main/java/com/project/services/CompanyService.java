package com.project.services;

import com.project.dao.CompanyDao;
import com.project.models.Company;

import javax.persistence.Query;
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
    public void updateAll(List<Company> objects) {
        dao.updateAll(objects);
    }

    public Company mapping(Query query) {
        Company company = new Company();
        company.setCompanyId((Integer) query.getParameterValue("COMPANY_ID"));
        company.setName(String.valueOf(query.getParameterValue("NAME")));
        company.setStreetName(String.valueOf(query.getParameterValue("STREET_NAME")));
        company.setStreetNumber((Integer) query.getParameterValue("STREET_NUMBER"));
        company.setCity(String.valueOf(query.getParameterValue("CITY")));
        company.setZipcode(String.valueOf(query.getParameterValue("ZIPCODE")));
        return company;
    }
}
