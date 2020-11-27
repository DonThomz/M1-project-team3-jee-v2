package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.Company;

import javax.persistence.Query;
import java.util.List;

public class CompanyDao extends DaoResource<Company> {

    public CompanyDao(Database database) {
        this.database = database;
        this.entityManager = this.database.getConnection();
    }

    @Override
    public List<Company> findAll() throws DaoException {
        isOpen();
        try {
            return entityManager.createQuery("SELECT c FROM Company c", Company.class).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public Company find(int id) throws DaoException {
        isOpen();
        try {
            return entityManager.find(Company.class, id);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Company object) throws DaoException {
        isOpen();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    public void saveAll(Company... objects) throws DaoException {
        isOpen();
        try {
            entityManager.getTransaction().begin();
            for (Company object : objects) {
                entityManager.persist(object);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Company object) throws DaoException {
        isOpen();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateAll(List<Company> objects) throws DaoException {
        isOpen();
        try {
            entityManager.getTransaction().begin();
            objects.forEach(entityManager::merge);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            entityManager.close();
        }
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
