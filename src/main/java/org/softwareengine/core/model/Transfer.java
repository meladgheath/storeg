package org.softwareengine.core.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.softwareengine.utils.service.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transfer {

    private int id ;
    private int storeID ;
    private int itemID  ;
    private int amount  ;
    private int box     ;

    private int packages;

    private String item ;
    private String store ;
    private int num ;
    private String amount_totel ;

    private char state ;

    public Transfer() {

    }

    public Transfer(char state) {
        this.state = state;
    }


    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPackages() {
        return packages;
    }


    public String getAmount_totel() {
        return amount_totel;
    }

    public void setAmount_totel(String amount_totel) {
        this.amount_totel = amount_totel;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }

    public int getStoreID() {
        return storeID;
    }

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getItemID() {
        return itemID;
    }


    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void update () throws SQLException {
        String sql = "DELETE FROM amount WHERE num < 0  OR num = 0" ;
        DatabaseService.openConnection();

        Statement statement = DatabaseService.connection.createStatement();
        statement.executeUpdate(sql);

        DatabaseService.CloseConnection();
    }

    public Transfer getInfoWHEREitemid() throws SQLException {

        ObservableList<Transfer> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM amount WHERE id = "+this.id;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        resultSet.next() ;
        Transfer one = new Transfer() ;

        one.setItemID(resultSet.getInt("itemid"));

        DatabaseService.CloseConnection();

        return one ;
    }

    public ObservableList<Transfer> getInfo() throws SQLException {
        ObservableList<Transfer> list = FXCollections.observableArrayList();
//        String sql = "SELECT (SELECT name  FROM store where id = store ) as storename  ,(SELECT name FROM item WHERE id = item ) as itemname\n" +
//                ",* FROM transfer  WHERE store = "+this.storeID+" ORDER BY id";

        String sql = "SELECT itemid , (SELECT name FROM item WHERE id = itemid ) as item ," +
                " (SELECT name FROM store WHERE id = storeid) as store , sum(num) as num  FROM amount  WHERE storeid = "+this.storeID +" group by itemid ";

       /* if (state == 'S')
            sql = sql + " AND state = 'S'  group by itemid" ;
        else
            sql = sql + " AND state = 'D'  group by itemid" ;*/

        //update();
        System.out.println("this . itemID "+this.itemID);

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        while (resultSet.next()) {
            Transfer one = new Transfer() ;

            one.setId(++i);
            one.setItemID(resultSet.getInt("itemid"));
//            one.setStoreID(resultSet.getInt("storeid"));
//            one.setAmount(resultSet.getInt("amount"));
//            one.setItemID(resultSet.getInt("item"));
            one.setItem(resultSet.getString("item"));
            one.setStore(resultSet.getString("store"));
            one.setNum(resultSet.getInt("num"));

           /* main.model.item item = new main.model.item() ;
            item.setId(one.getItemID());


            int box      = 0 ;
            int packages = item.getInfoWHEREid().getPackages();

            amount = one.getAmount() ;


            outer:  while (amount > 0) {
                if (amount>packages) {
                    amount = amount - packages ;
                    box++;
                }else
                    break outer;
            }

            System.out.println(box);
            System.out.println(amount);

            one.setAmount(amount);
            one.setBox(box);
            one.setPackages(packages);
            one.setAmount_totel(amount+"/"+packages);

*/

            list.add(one);
        }

        DatabaseService.connection.close();
        return list ;
    }


    public boolean check () throws SQLException {

       String sql = "SELECT id FROM amount WHERE storeid = " + this.storeID + " AND itemid = " + this.itemID;

       DatabaseService.openConnection();
       Statement statement = DatabaseService.connection.createStatement();
       ResultSet resultSet = statement.executeQuery(sql);
       boolean logic = resultSet.next();

        resultSet.close();
        statement.close();
        DatabaseService.connection.close();

        return logic ;


    }

    public Transfer getThis () throws SQLException {

       String sql = "SELECT * FROM amount WHERE storeid = "+this.storeID+" AND itemid = "+this.itemID ;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        Transfer one = null ;
        while (resultSet.next()) {
            one = new Transfer() ;

            one.setId(resultSet.getInt("id"));
            one.setNum(resultSet.getInt("num"));
            one.setItemID(resultSet.getInt("itemid"));


        }
        return one ;


    }
    public void add() throws SQLException {


        String sql ;

        DatabaseService.CloseConnection();

        if (check()) {
            sql = "UPDATE amount SET num = (SELECT num FROM amount WHERE storeid = "+this.storeID+" AND itemid = "+this.itemID+" )+"+this.num+" WHERE storeid = "+this.storeID+" AND itemid = "+this.itemID +" " ;


        }else
            sql = "INSERT INTO amount (storeid,itemid,num) VALUES ("+this.storeID+","+this.itemID+","+this.num+")  " ;

        DatabaseService.openConnection();
//        PreparedStatement ps = database.connection.prepareStatement(sql);
//
//        ps.setInt(1,this.storeID);
//        ps.setInt(2,this.itemID );
//        ps.setInt(3,this.amount );



        Statement statement = DatabaseService.connection.createStatement();
        statement.executeUpdate(sql);

        DatabaseService.CloseConnection();
    }
    public void drop() throws SQLException{
        String sql ;
        sql = "UPDATE amount SET num = (SELECT num FROM amount WHERE storeid = "+this.storeID+" AND itemid = "+this.itemID+" ) - "+this.num+" " +
                " WHERE storeid = "+this.storeID+" AND itemid = "+this.itemID ;


        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        statement.executeUpdate(sql);

        DatabaseService.CloseConnection();
//        System.exit(0);

    }

}

