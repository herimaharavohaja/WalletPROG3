package org.example.Repositor;

import org.example.Models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface InterfaceRepository <T> {
    Account findById(int toFind);

    List<T> findAll();

    List<T> saveAll(List<T> toSave);

    T save(T toSave);


    void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet);
}
