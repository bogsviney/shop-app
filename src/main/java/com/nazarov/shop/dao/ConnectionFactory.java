package com.nazarov.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String url;
    private final String userName;
    private final String passWord;

    public ConnectionFactory(String url, String userName, String passWord) {
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, userName, passWord);

        } catch (SQLException e) {
            throw new RuntimeException("CANNOT GET CONNECTION", e);
        }
    }
}
