package org.softwareengine.utils.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseService {
    public static Connection connection;

    public static void openConnection() throws SQLException {
        try {
//            Class.forName("org.sqlite.JDBC");
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        connection = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.home")+"\\db.db");
//        connection = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.home")+"/Sdb.db");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/storge","wahda","wahda");
        System.out.println("connecting");
//                jdbc:postgresql://localhost:5432/
    }

    public static void CloseConnection() throws SQLException {
        if (!connection.isClosed()) connection.close();
    }
}
