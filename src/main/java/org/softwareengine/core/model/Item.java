package org.softwareengine.core.model;



//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.softwareengine.utils.service.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    public class Item {

        private int id ;
        private int code ;
        private String name ;
        private double value ;
        private int packages ;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPackages() {
            return packages;
        }

        public void setPackages(int packages) {
            this.packages = packages;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public void save() throws SQLException {
            String sql = "INSERT INTO item (name , code) VALUES (?,?)" ;

            DatabaseService.openConnection();
            PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);

//            ps.setInt   (1,this.code    );
//            ps.setString(2,this.name    );
//            ps.setInt   (3,this.packages);
            ps.setString(1,this.name);
            ps.setInt(2,this.code);

            ps.executeUpdate();

            DatabaseService.CloseConnection();
        }

        public ObservableList<Item> getInfo() throws SQLException {
            ObservableList<Item> list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM item ORDER BY id";

            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int i = 0 ;
            while (resultSet.next()) {
                Item one = new Item() ;

                one.setId(++i);
                one.setCode(resultSet.getInt("code"));
                one.setName(resultSet.getString("name"));
//                one.setPackages(resultSet.getInt("package"));
//                one.setValue(resultSet.getDouble("value"));
                list.add(one);
            }
            return list ;
        }


        public Item getInfoWHEREid() throws SQLException {
            ObservableList<Item> list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM item WHERE id = "+this.id;

            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int i = 0 ;
            resultSet.next() ;
            Item one = new Item() ;

            one.setValue(resultSet.getDouble("value"));
            one.setPackages(resultSet.getInt("package"));
            DatabaseService.CloseConnection();

            return one ;
        }

        public void update (String cases) throws SQLException {

            String sql = "" ;



            DatabaseService.openConnection();


            PreparedStatement ps ;
            switch (cases) {
                case "name" :
                    sql = "UPDATE item SET name = ? where id = ?" ;
                    ps = DatabaseService.connection.prepareStatement(sql);
                    ps.setString(1,this.name);
                    ps.setInt   (2,this.id  );
                    ps.executeUpdate();
                    break;

                case "code" :
                    sql = "UPDATE item SET code = ? where id = ?" ;
                    ps = DatabaseService.connection.prepareStatement(sql);
                    ps.setInt(1,this.code);
                    ps.setInt(2,this.id  );
                    ps.executeUpdate();
                    break;
                case "package" :
                    sql = "UPDATE item SET package = ? where id = ?" ;
                    ps = DatabaseService.connection.prepareStatement(sql);
                    ps.setInt(1,this.packages);
                    ps.setInt(2,this.id  );
                    ps.executeUpdate();
                    break;
                case "value":
                    sql = "UPDATE item SET value = ? where id = ?" ;
                    ps = DatabaseService.connection.prepareStatement(sql);
                    ps.setDouble(1,this.value);
                    ps.setInt(2 ,this.id  );
                    ps.executeUpdate();
                    break;

            }




            System.out.println(sql);



            DatabaseService.CloseConnection();

        }

        public ObservableList<Item> getInfoID() throws SQLException {
            ObservableList<Item> list = FXCollections.observableArrayList();
            String sql = "SELECT id FROM item ORDER BY id";

            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                Item one = new Item() ;

                one.setId(resultSet.getInt("id"));

                list.add(one);
            }
            return list ;
        }

        public ObservableList<Item> getInfoByCode() throws SQLException {
            ObservableList<Item> list = FXCollections.observableArrayList();


            String sql;
            System.out.println("code . . . . . "+this.code);
            if (this.code == 0)
                sql = "SELECT id , code ,  name , value  FROM item ORDER BY id";
            else

                sql  = "SELECT id , code , name , value FROM item WHERE code::text like '%"+this.code+"%' ORDER BY id";

            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                Item one = new Item() ;

                one.setId(resultSet.getInt("id"));
                one.setCode(resultSet.getInt("code"));
                one.setName(resultSet.getString("name"));
                one.setValue(resultSet.getDouble("value"));

                list.add(one);
            }
            return list ;
        }


        public Item getInfoWHERECode() throws SQLException {



            String sql = "SELECT id , code ,  name , value  FROM item WHERE code = "+this.code+" ORDER BY id";
            System.out.println(sql);

            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);



            Item one = new Item() ;

            resultSet.next() ;

            one.setId(resultSet.getInt("id"));
            one.setCode(resultSet.getInt("code"));
            one.setName(resultSet.getString("name"));
            one.setValue(resultSet.getDouble("value"));

            return one ;
        }


        public Item getInfoByID() throws SQLException {



            String sql;

            sql = "SELECT * FROM item where id =  "+this.id;


            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Item one = null ;
            while (resultSet.next()) {
                one = new Item() ;

                one.setId(resultSet.getInt("id"));
                one.setCode(resultSet.getInt("code"));
                one.setName(resultSet.getString("name"));
                one.setPackages(resultSet.getInt("package"));


            }
            return one ;
        }


        public void renewalItem() throws SQLException {

            String sql = "UPDATE item SET value = "+this.value+" WHERE id = "+this.id ;

            DatabaseService.openConnection();
            Statement statement = DatabaseService.connection.createStatement();

            statement.executeUpdate(sql);


            DatabaseService.CloseConnection();

        }
    }