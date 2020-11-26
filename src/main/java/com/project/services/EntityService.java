package com.project.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface EntityService<X> {


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

    List<X> findAll() throws SQLException;

    X find(int id);

    void save(X object);

    void saveAll(X... objects);

    void update(X object);

    void updateAll(List<X> objects);

}
