package com.project.dao;

import com.project.database.Database;
import com.project.models.Company;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CompanyDao implements DaoResource<Company> {

    private final EntityManager entityManager;

    public CompanyDao(Database database) {
        this.entityManager = database.getConnection();
    }

    @Override
    public List<Company> findAll() {
        return entityManager.createQuery("SELECT c FROM Company c", Company.class).getResultList();
    }

    @Override
    public Company find(int id) {
        return entityManager.find(Company.class, id);
    }

    @Override
    public void save(Company object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveAll(Company... objects) {
        entityManager.getTransaction().begin();
        for (Company object : objects) {
            entityManager.persist(object);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Company object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateAll(List<Company> objects) {
        entityManager.getTransaction().begin();
        objects.forEach(object -> {
            entityManager.merge(object);
        });
        entityManager.getTransaction().commit();
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
