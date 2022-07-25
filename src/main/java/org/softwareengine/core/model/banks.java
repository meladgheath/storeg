package org.softwareengine.core.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.softwareengine.utils.service.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class banks {


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
            String sql = "INSERT INTO banks (name) VALUES (?)" ;

            DatabaseService.openConnection();
            PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);

//            ps.setInt   (1,this.code    );
//            ps.setString(2,this.name    );
//            ps.setInt   (3,this.packages);
            ps.setString(1,this.name);

            ps.executeUpdate();

            DatabaseService.CloseConnection();
        }

        public ObservableList<org.softwareengine.core.model.Item> getInfo() throws SQLException {
            ObservableList<org.softwareengine.core.model.Item> list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM item ORDER BY id";

            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int i = 0 ;
            while (resultSet.next()) {
                org.softwareengine.core.model.Item one = new org.softwareengine.core.model.Item() ;

                one.setId(++i);
                one.setCode(resultSet.getInt("code"));
                one.setName(resultSet.getString("name"));
//                one.setPackages(resultSet.getInt("package"));
//                one.setValue(resultSet.getDouble("value"));
                list.add(one);
            }
            return list ;
        }


        public org.softwareengine.core.model.Item getInfoWHEREid() throws SQLException {
            ObservableList<org.softwareengine.core.model.Item> list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM item WHERE id = "+this.id;

            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int i = 0 ;
            resultSet.next() ;
            org.softwareengine.core.model.Item one = new org.softwareengine.core.model.Item() ;

            one.setValue(resultSet.getDouble("value"));
            one.setPackages(resultSet.getInt("package"));
            DatabaseService.CloseConnection();

            return one ;
        }



        public ObservableList<org.softwareengine.core.model.Item> getInfoID() throws SQLException {
            ObservableList<org.softwareengine.core.model.Item> list = FXCollections.observableArrayList();
            String sql = "SELECT id FROM item ORDER BY id";

            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                org.softwareengine.core.model.Item one = new org.softwareengine.core.model.Item() ;

                one.setId(resultSet.getInt("id"));

                list.add(one);
            }
            return list ;
        }


        public org.softwareengine.core.model.Item getInfoByID() throws SQLException {



            String sql;

            sql = "SELECT * FROM item where id =  "+this.id;


            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            org.softwareengine.core.model.Item one = null ;
            while (resultSet.next()) {
                one = new org.softwareengine.core.model.Item() ;

                one.setId(resultSet.getInt("id"));
                one.setCode(resultSet.getInt("code"));
                one.setName(resultSet.getString("name"));
                one.setPackages(resultSet.getInt("package"));


            }
            return one ;
        }
    }


