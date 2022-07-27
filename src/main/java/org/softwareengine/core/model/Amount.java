package org.softwareengine.core.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.softwareengine.utils.service.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Amount {
    private int id;
    private int itemID;
    private int storeID;
    private int num;
    private String item;
    private String store;
    private String stateName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void save() throws SQLException {
        String sql = "insert into amount ( itemid, storeid, num  ) values (?,?,?)" ;

        DatabaseService.openConnection();
        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);


        ps.setInt(1,this.itemID);
        ps.setInt(2,this.storeID  );

        ps.setInt(3,this.num);

        ps.executeUpdate();

        DatabaseService.CloseConnection();
    }

    public Amount getInfoNumber() throws SQLException {
        String sql = "SELECT sum(num) as number FROM amount WHERE itemid = "+this.itemID+" and storeid = "+this.storeID+" " ;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next() ;

        Amount one = new Amount() ;
        one.setNum(resultSet.getInt("number"));

        DatabaseService.connection.close();

        return one;
    }

    public ObservableList<Amount> getInfoItemInSpecStore() throws SQLException {
        ObservableList<Amount> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = "SELECT itemid ,(SELECT name FROM item WHERE id = itemid ) as items , \n" +
                "sum(num) as number  FROM amount WHERE storeid = "+this.storeID+"  GROUP by itemid " ;


        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println(" the access is "+this.storeID);
        int i = 0 ;
        while (resultSet.next()) {
            Amount one = new Amount() ;

            one.setId(++i);
            one.setItemID(resultSet.getInt("itemid"));
            one.setItem(resultSet.getString("items"));
            one.setNum(resultSet.getInt("number"));

            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list ;
    }

    //TODO This method has problem
    public ObservableList<Amount> getInfoTransactions() throws SQLException {
        ObservableList<Amount> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = "SELECT (SELECT name FROM item WHERE id = itemid) as item ,\n" +
                "(CASE state\n" +
                "WHEN 'S' THEN (SELECT name FROM store WHERE id = storeid  ) \n" +
                "WHEN 'D' THEN (SELECT name FROM driver WHERE id = storeid )\n" +
                "END) as store , num ,\n" +
                "     (CASE state \n" +
                "   WHEN 'S' THEN 'Store'\n" +
                "   WHEN 'D' THEN 'Driver'\n" +
                "   END) as state FROM amount " ;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        while (resultSet.next()) {
            Amount one = new Amount() ;

            one.setId(++i);
            one.setStore(resultSet.getString("store"));
            one.setItem(resultSet.getString("item"));
            one.setNum(resultSet.getInt("num"));
//                one.setStateName(resultSet.getString("state"));

            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list ;
    }

    public ObservableList<Amount> getInfo() throws SQLException {
        ObservableList<Amount> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = "SELECT  (SELECT name FROM item WHERE id = itemid) as item " +
                ", (SELECT name FROM store WHERE id = storeid) as store , sum(num) as num  FROM amount   GROUP by itemid" ;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        while (resultSet.next()) {
            Amount one = new Amount() ;

            one.setId(++i);
            one.setStore(resultSet.getString("store"));
            one.setItem(resultSet.getString("item"));
            one.setNum(resultSet.getInt("num"));

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

        sql = "DELETE FROM amount where num = 0" ;

        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);
//            ps.setString(1,this.name);
//            ps.setInt   (2,this.id  );

        ps.executeUpdate();


    }



    public ObservableList<org.softwareengine.core.model.Store> getInfoID() throws SQLException {
        ObservableList<org.softwareengine.core.model.Store> list = FXCollections.observableArrayList();
        String sql = "SELECT  id  FROM store order by id  ";

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        while (resultSet.next()) {
            org.softwareengine.core.model.Store one = new org.softwareengine.core.model.Store() ;

            one.setId(resultSet.getInt("id"));


            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list ;
    }

    public Amount getNameByID() throws SQLException {
        ObservableList<Store> list = FXCollections.observableArrayList();
        String sql = "SELECT  name  FROM store where id = "+this.id;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next() ;

//            setName(resultSet.getString("name"));

        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();

        return this ;
    }
}