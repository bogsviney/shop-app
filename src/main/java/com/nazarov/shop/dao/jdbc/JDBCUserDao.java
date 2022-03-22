package com.nazarov.shop.dao.jdbc;

import com.nazarov.shop.dao.ConnectionFactory;
import com.nazarov.shop.dao.UserDao;
import com.nazarov.shop.service.PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUserDao implements UserDao {

    private final ConnectionFactory connectionFactory;
    private final PasswordEncoder passwordEncoder;

    private static final String FIND_USER_PASSWORD_BY_EMAIL = "SELECT id FROM users WHERE email =? AND password =?;";
    private static final String GET_USER_ROLE_BY_EMAIL = "SELECT role FROM users WHERE email =?;";

    public JDBCUserDao(ConnectionFactory connectionFactory, PasswordEncoder passwordEncoder) {
        this.connectionFactory = connectionFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkUser(String email, String password) {
        boolean isChecked = false;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_PASSWORD_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwordEncoder.encryptPassword(password));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                isChecked = resultSet.next();
                System.out.println("JDBC USERDAO: ACCESS ENABLE : " + isChecked);
                return isChecked;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("JDBC USERDAO: TROUBLE WITH CHECKING USER", e);
        }
    }

    @Override
    public String getUserRole(String email) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ROLE_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                if (role == null) {
                    throw new RuntimeException("JDBC USERDAO: WE HAVE NO USER WITH EMAIL " + email);
                } else {
                    System.out.println("JDBC USERDAO: ROLE in DB -> " + role);
                    return role;
                }
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("JDBC USERDAO: TROUBLE WITH GETTING USER ROLE");
        }

    }

}

