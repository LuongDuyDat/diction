package com.example.diction.Entry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    private Connection connection;

    public JDBCConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            String DbURL = "jdbc:sqlite:D:/Diction/database/dict.db";
            connection = DriverManager.getConnection(DbURL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;

    }
}
