package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DaoResource<T> {


    protected Database database;
    protected EntityManager entityManager;

    static void close(Connection connection) throws SQLException {
        if (connection != null) connection.close();
    }

    static void close(ResultSet resultSet) throws SQLException {
        if (resultSet != null) resultSet.close();
    }

    static void close(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) preparedStatement.close();
    }

    static void closeAll(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        close(connection);
        close(resultSet);
        close(preparedStatement);
    }

    abstract List<T> findAll() throws DaoException;

    abstract T find(int id) throws DaoException;

    abstract void save(T object) throws DaoException;

    abstract void update(T object) throws DaoException;

    abstract void updateAll(List<T> objects) throws DaoException;

    void isOpen() {
        this.entityManager = this.entityManager.isOpen() ? this.entityManager : database.getConnection();
    }

}
