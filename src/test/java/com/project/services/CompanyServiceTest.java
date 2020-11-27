package com.project.services;

import com.project.dao.CompanyDao;
import com.project.models.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CompanyServiceTest {
    private static Integer FakeCompanyID = 0;

    @Mock
    CompanyDao companyDao;
    
    @BeforeEach
    void setUp() {
        companyDao = Mockito.mock(CompanyDao.class);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        Company fakeCompany1 = createFakeCompany("Capgemini","Paris","Kléber","75016",76);
        Company fakeCompany2 = createFakeCompany("Google","Paris","Lucie","75015",13);
        List<Company> fakeCompanies = Arrays.asList(fakeCompany1,fakeCompany2);

        // fake behavior
        when(companyDao.findAll()).thenReturn(fakeCompanies);

        CompanyService service = new CompanyService(companyDao);
        List<Company> result = service.findAll();
        // assertions
        assertEquals(fakeCompanies, result);
    }

    @Test
    void find() {
    }

    @Test
    void save() {
    }

    @Test
    void saveAll() {
    }

    @Test
    void update() {
    }

    @Test
    void updateAll() {
    }
    
    private Company createFakeCompany (String name, String city, String streetName, String zipcode, int streetNumber){
        Company company = new Company();
        company.setCompanyId(FakeCompanyID++);
        company.setCity(city);
        company.setName(name);
        company.setStreetName(streetName);
        company.setStreetNumber(streetNumber);
        company.setZipcode(zipcode);
        company.setInternshipsByCompanyId(new ArrayList<>());
        company.setInternshipSupervisorsByCompanyId(new ArrayList<>());
        return company;
    }
}