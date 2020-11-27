package com.project.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface EntityService<X> {

    List<X> findAll() throws SQLException;

    X find(int id);

    void save(X object);

    void saveAll(X... objects);

    void update(X object);

    void updateAll(List<X> objects) throws SQLException;

}
