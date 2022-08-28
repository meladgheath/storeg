package org.softwareengine.utils.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseService {
    public static Connection connection;

    public static void openConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        connection = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.home")+"\\db.db");
        connection = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.home")+"/db.db");
    }

    public static void CloseConnection() throws SQLException {
        if (!connection.isClosed()) connection.close();
    }
}
