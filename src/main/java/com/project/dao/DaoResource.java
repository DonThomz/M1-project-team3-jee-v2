package com.project.dao;

import com.project.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DaoResource<T> {


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

    List<T> findAll() throws DaoException;

    T find(int id) throws DaoException;

    void save(T object) throws DaoException;

    void update(T object) throws DaoException;

    void updateAll(List<T> objects) throws DaoException;

}
