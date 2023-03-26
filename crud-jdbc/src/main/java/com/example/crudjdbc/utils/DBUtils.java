package com.example.crudjdbc.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static Connection connection = null;

    private static String URL = "jdbc:mysql://localhost:3306/jdbc_crud";
    private static String user = "bestuser";
    private static String password = "bestuser";

    public static Connection getConnection(){
        try {
           connection = DriverManager.getConnection(URL,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

}
