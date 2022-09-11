package org.softwareengine.core.model;

import org.softwareengine.utils.service.DatabaseService;

import javax.xml.stream.events.StartElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class user {

    private static int id ;
    private static String name ;
    private static String password ;
    private static  String lang ;


    public static String getLang() throws SQLException {
        String sql = "SELECT lang FROM setting" ;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        user.lang = resultSet.getString("lang") ;

        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();

        return lang;
    }

    public static void setLang(String lang) {
        user.lang = lang;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        user.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        user.name = name;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        user.password = password;
    }
    public static void getUser() throws SQLException {
        String sql = "SELECT * FROM  USER WHERE id = "+user.id;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            user.id = resultSet.getInt("id") ;
            user.name = resultSet.getString("name");
            user.password = resultSet.getString("pass");
        }else {
            user.id = 0;
            user.name = null;
            user.password = null;
        }


        resultSet.close();
        DatabaseService.CloseConnection();

    }
    public static void  updateLanguages() throws SQLException {
        String sql = "UPDATE setting SET lang = '"+user.lang +"'";

        DatabaseService.openConnection();

        Statement statement = DatabaseService.connection.createStatement();
        statement.executeUpdate(sql);

        DatabaseService.CloseConnection();

    }
}
