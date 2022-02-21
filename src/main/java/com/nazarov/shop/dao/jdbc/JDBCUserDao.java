package com.nazarov.shop.dao.jdbc;

import com.nazarov.shop.dao.ConnectionFactory;
import com.nazarov.shop.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUserDao implements UserDao {

    private final ConnectionFactory CONNECTION_FACTORY;
    private static final String FIND_USER_PASSWORD_BY_EMAIL = "SELECT password FROM users WHERE email =?;";

    public JDBCUserDao(ConnectionFactory connectionFactory) {
        this.CONNECTION_FACTORY = connectionFactory;
    }

    @Override
    public boolean checkUser(String email, String password) {
        boolean isChecked = false;
        try (Connection connection = CONNECTION_FACTORY.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_PASSWORD_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            isChecked = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isChecked;
    }
}

