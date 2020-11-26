package com.project.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author victo
 */
public class ValidationTest {

    public ValidationTest() {
    }


    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of validationTextField method, of class Validation.
     */
    @Test
    public void testValidationTextField() {
        System.out.println("validationTextField");
        String field = "commentary of intern";
        String fieldName = "commentaryIntern";
        //String field = ""; --> To fail test
        Validation.validationTextField(field, fieldName);
    }

    /**
     * Test of validationNumberField method, of class Validation.
     */
    @Test
    public void testValidationNumberField() {
        System.out.println("validationNumberField");
        String field = "1788";
        String fieldName = "companyStreetNumber";
        //String field = "azertyuiop"; --> To fail test
        Validation.validationNumberField(field, fieldName);
    }

    /**
     * Test of validationFloatField method, of class Validation.
     */
    @Test
    public void testValidationFloatField() {
        System.out.println("validationFloatField");
        String field = "17.3";
        String fieldName = "noteTech";
        //String field = "notAnumber"; --> To fail test
        Validation.validationFloatField(field, fieldName);
    }

    /**
     * Test of validationTimestamp method, of class Validation.
     */
    @Test
    public void testValidationTimestamp() {
        System.out.println("validationTimestamp");
        String timestamp = "2020-11-26 13:44";
        String fieldName = "midInternshipMeetingDate";
        //String timestamp = "2020-11-26 13:44:00"; --> To fail tests
        Validation.validationTimestamp(timestamp, fieldName);
    }

    /**
     * Test of validationMidInternship method, of class Validation.
     */
    @Test
    public void validationMidInternship() {
        System.out.println("validationMidInternship");
        Date startDate = Date.valueOf("2019-04-26");
        Date endDate = Date.valueOf("2019-09-24");
        Timestamp midInternship = new Timestamp(Date.valueOf("2019-09-10").getTime());
        //Timestamp midInternship = new Timestamp(Date.valueOf("2019-09-30").getTime()); --> To fail test
        Validation.validationMidInternship(startDate, endDate, midInternship);
    }

    /**
     * Test of validationDate method, of class Validation.
     */
    @Test
    public void testValidationDate() {
        System.out.println("validationDate");
        String date = "17-10-2020";
        String dateName = "startDate";
        //String date = ""; --> To fail test
        Validation.validationDate(date, dateName);
    }

    /**
     * Test of validationDates method, of class Validation.
     */
    @Test
    public void testValidationDates() {
        System.out.println("validationDates");
        Date startDate = Date.valueOf("2019-04-26");
        Date endDate = Date.valueOf("2019-09-24");
        //Date endDate = Date.valueOf("2018-09-24"); --> to fail test
        Validation.validationDates(startDate, endDate);
    }

    /**
     * Test of validationKeywords method, of class Validation.
     */
    @Test
    public void testValidationKeywords() throws Exception {
        System.out.println("validationKeywords");
        String keywords = "keyword1, keyword2";
        String fieldName = "missionKeywords";
        //String keywords = ""; --> To fail test
        Validation.validationKeywords(keywords, fieldName);
    }

    /**
     * Test of validationEmail method, of class Validation.
     */
    @Test
    public void testValidationEmail() throws Exception {
        System.out.println("validationEmail");
        String email = "john.smith@efrei.fr";
        //String email = "azertyuiop"; --> To fail test
        Validation.validationEmail(email);
    }

    /**
     * Test of validationPassword method, of class Validation.
     */
    @Test
    public void testValidationPassword() throws Exception {
        System.out.println("validationPassword");
        String password = "Coucoucmoi";
        //String password = "h"; --> To fail test
        Validation.validationPassword(password);
    }

}