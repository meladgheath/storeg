package org.softwareengine.core.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.softwareengine.utils.service.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Store {

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


    public void save() throws SQLException {
        String sql = "insert into store ( name ) values (?)" ;

        DatabaseService.openConnection();
        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);


        ps.setString(1,this.name    );


        ps.executeUpdate();

        DatabaseService.CloseConnection();
    }


    public ObservableList<Store> getInfo() throws SQLException {
        ObservableList<Store> list = FXCollections.observableArrayList();
        String sql = "SELECT  *  FROM store order by id ";

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        while (resultSet.next()) {
            Store one = new Store() ;

            one.setId(++i);
            one.setName(resultSet.getString("name"));


            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list ;
    }

    public void update () throws SQLException {

        String sql = "" ;


        DatabaseService.openConnection();

        sql = "UPDATE store SET name = ? where id = ?" ;
        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);
        ps.setString(1,this.name);
        ps.setInt   (2,this.id  );
        ps.executeUpdate();


    }



    public ObservableList<Store> getInfoID() throws SQLException {
        ObservableList<Store> list = FXCollections.observableArrayList();
        String sql = "SELECT  id  FROM store order by id  ";

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        while (resultSet.next()) {
            Store one = new Store() ;

            one.setId(resultSet.getInt("id"));


            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list ;
    }

    public Store getNameByID() throws SQLException {
        ObservableList<Store> list = FXCollections.observableArrayList();
        String sql = "SELECT  name  FROM store where id = "+this.id;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next() ;

        setName(resultSet.getString("name"));

        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();

        return this ;

    }
}

