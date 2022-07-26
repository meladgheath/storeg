package org.softwareengine.core.model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.softwareengine.utils.service.DatabaseService;

import java.io.*;
import java.sql.*;


public class type {


    private int id ;
    private String name ;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void save() throws SQLException, FileNotFoundException {
//        String sql = "" ;
        String sql = "INSERT  INTO  type ( name  ) values (?)" ;
        DatabaseService.openConnection();
        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);


        ps.setString(1,this.name    );


        ps.executeUpdate();

        DatabaseService.CloseConnection();
    }

    public ObservableList<type> getInfo() throws SQLException {
        ObservableList<type> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM type ORDER BY id";

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        while (resultSet.next()) {
            type one = new type() ;

            one.setId(++i);
            one.setName(resultSet.getString("name"));

            list.add(one);
        }
        return list ;
    }



}