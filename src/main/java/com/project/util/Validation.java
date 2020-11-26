package com.project.util;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

/**
 * List of methods to validate field input and throw exceptions if a error is detected
 */
public class Validation {

    // Constraints
    private static final String regexEmail = "^(.+)@(.+)$";
    private static final int MIN_CHARACTERS_FIELD = 3;

    public static void validationTextField(String field, String fieldName) throws Exception {
        if (field == null || field.trim().isEmpty()) throw new Exception(fieldName + " must not be empty");
    }

    public static int validationNumberField(String field, String fieldName) throws Exception {
        if (field != null && !field.trim().isEmpty()) {
            try {
                int parseField = parseInt(field);
                if (parseField < 0) throw new Exception(fieldName + " must be a positive number");
                else return parseField;
            } catch (NumberFormatException e) {
                throw new Exception("Invalid number");
            }
        } else throw new NumberFormatException(fieldName + " is empty");
    }

    public static Float validationFloatField(String field, String fieldName) throws NumberFormatException, NullPointerException {
        if (field != null && !field.trim().isEmpty()) {
            try {
                float parseField = Float.parseFloat(field);
                if (parseField < 0) throw new NumberFormatException(fieldName + " is not a positive number");
                else return parseField;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("invalid number");
            }
        } else throw new NullPointerException(fieldName + " is empty");
    }

    public static Timestamp validationTimestamp(String timestamp, String fieldName) throws IllegalArgumentException, NullPointerException {
        if (timestamp != null && !timestamp.trim().isEmpty()) {
            try {
                return Timestamp.valueOf(timestamp.replace("T", " ") + ":00");
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("The field " + fieldName + " is note correct");
            }
        } else throw new NullPointerException("The field " + fieldName + " is empty");
    }

    public static void validationDate(String date, String dateName) throws NullPointerException {
        if (date == null || date.trim().isEmpty()) throw new NullPointerException(dateName + " is empty");
    }

    /**
     * Check if start date is before the end date
     *
     * @param startDate the internship start date
     * @param endDate   the internship end date
     * @throws IllegalArgumentException if end date is before start date
     * @throws NullPointerException     if dates are null or empty
     */
    public static void validationDates(Date startDate, Date endDate) throws IllegalArgumentException, NullPointerException {
        if (startDate != null && endDate != null) {
            if (!startDate.before(endDate)) throw new IllegalArgumentException("start date must be before end Date");
        }
    }

    public static void validationKeywords(String keywords) throws Exception {
    }

    /**
     * Check email field
     *
     * @param email from input form
     * @throws Exception throw an exception if the email is incorrect
     */
    public static void validationEmail(String email) throws Exception {
        if (email != null && !email.trim().isEmpty()) {
            if (!Pattern.matches(regexEmail, email)) throw new Exception("email invalid, please enter a correct email");
        } else throw new Exception("email empty, please try again");
    }

    /**
     * Check password field
     *
     * @param password from input form
     * @throws Exception throw an exception if the password is incorrect
     */
    public static void validationPassword(String password) throws Exception {
        if (password != null && !password.trim().isEmpty()) {
            if (password.trim().length() < MIN_CHARACTERS_FIELD)
                throw new Exception("password must be granter than " + MIN_CHARACTERS_FIELD + " characters");
        } else throw new Exception("password empty, please enter a password");
    }

}
