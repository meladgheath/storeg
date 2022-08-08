package org.softwareengine.core.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.softwareengine.utils.service.DatabaseService;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {

    private int id;

    private int itemID;
    private int storeID;
    private int bankID;
    private int number;

    private String item;
    private String store;
    private String bank;

    private String date;

    private ByteArrayOutputStream img ;



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public ByteArrayOutputStream getImg() {
        return img;
    }

    public void setImg(ByteArrayOutputStream img) {
        this.img = img;
    }

    public void save() throws SQLException {
        String sql = "INSERT INTO transactions ( item , bank, store , number , date ,img ) VALUES (?,?,?,?,?,?)";

        DatabaseService.openConnection();
        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);


        ps.setInt(1, this.itemID);
        ps.setInt(2, this.bankID);
        ps.setInt(3, this.storeID);
        ps.setInt(4, this.number);
        ps.setString(5, this.date);
        ps.setBytes(6,this.img.toByteArray());

        ps.executeUpdate();

        DatabaseService.CloseConnection();
//        bos.close();
        System.out.println("what happing here melad ");
    }

    public void saves() throws SQLException {
        String sql = "INSERT INTO transactionss ( item , bank,  number , date ,img ) VALUES (?,?,?,?,?)";

        DatabaseService.openConnection();
        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);

        ps.setInt(1, this.itemID);
        ps.setInt(2, this.bankID);
        ps.setInt(3, this.number);
        ps.setString(4, this.date);
        ps.setBytes(5,this.img.toByteArray());

        ps.executeUpdate();

        DatabaseService.CloseConnection();
//        bos.close();
        System.out.println("what happing here melad ");
    }

public void getImagess(File file) throws SQLException, IOException {

        String sql = "SELECT img from transactions where id =  "+this.id;

    System.out.println("the id here is "+this.id);


        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        InputStream in = null;
        OutputStream out ;
        int i = 0;
        while (resultSet.next()){
            in = resultSet.getBinaryStream(1);

        }

        System.out.println(file.getPath());
        out = new FileOutputStream(file) ;
        int readnum = 0 ;

        while ((readnum = in.read()) > -1 )
            out.write(readnum);

        out.close();
        in.close();
        
        DatabaseService.CloseConnection();

}


    public ObservableList<Transaction> getInfoTransactionss() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = """
                SELECT (SELECT name FROM banks WHERE id = bank) as bank ,
                (SELECT name FROM item WHERE id = item ) as item ,
                 number , date
                 FROM transactionss order by date desc
                 """;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(++i);
            one.setItem(resultSet.getString("item"));
            one.setBank(resultSet.getString("bank"));
            one.setNumber(resultSet.getInt("number"));
            one.setDate(resultSet.getString("date"));

            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list;
    }



    public ObservableList<Transaction> getInfoTransactions() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = """
                SELECT (SELECT name FROM banks WHERE id = bank) as bank ,
                (SELECT name FROM item WHERE id = item ) as item ,
                (SELECT name FROM store WHERE id = store) as store , number , date
                 FROM transactions order by date desc
                 """;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(++i);
            one.setStore(resultSet.getString("store"));
            one.setItem(resultSet.getString("item"));
            one.setBank(resultSet.getString("bank"));
            one.setNumber(resultSet.getInt("number"));
            one.setDate(resultSet.getString("date"));

            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list;
    }

    public ObservableList<Transaction> getInfoTransactionsID() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = """
                SELECT id , (SELECT name FROM banks WHERE id = bank) as bank ,
                (SELECT name FROM item WHERE id = item ) as item ,
                (SELECT name FROM store WHERE id = store) as store , number , date
                 FROM transactions order by date desc
                 """;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(resultSet.getInt("id"));
            one.setStore(resultSet.getString("store"));
            one.setItem(resultSet.getString("item"));
            one.setBank(resultSet.getString("bank"));
            one.setNumber(resultSet.getInt("number"));
            one.setDate(resultSet.getString("date"));

            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list;
    }
    public ObservableList<Amount> getInfo() throws SQLException {
        ObservableList<Amount> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = "SELECT  (SELECT name FROM item WHERE id = itemid) as item " + ", (SELECT name FROM store WHERE id = storeid) as store , sum(num) as num  FROM amount   GROUP by itemid";

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0;
        while (resultSet.next()) {
            Amount one = new Amount();

            one.setId(++i);
            one.setStore(resultSet.getString("item"));
            one.setItem(resultSet.getString("store"));
            one.setNum(resultSet.getInt("num"));

            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list;
    }

    public void update() throws SQLException {

        String sql = "";


        DatabaseService.openConnection();

        sql = "delete FROM  amount  where num = 0 or num < 0 ";

        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);
//            ps.setString(1,this.name);
//            ps.setInt   (2,this.id  );

        ps.executeUpdate();


    }


    public ObservableList<Store> getInfoID() throws SQLException {
        ObservableList<Store> list = FXCollections.observableArrayList();
        String sql = "SELECT  id  FROM store order by id  ";

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0;
        while (resultSet.next()) {
            Store one = new Store();

            one.setId(resultSet.getInt("id"));


            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list;
    }
/*
    public main.model.amount getNameByID() throws SQLException {
        ObservableList<main.model.store> list = FXCollections.observableArrayList();
        String sql = "SELECT  name  FROM store where id = "+this.id;

        database.openConnection();
        Statement statement = database.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next() ;

//            setName(resultSet.getString("name"));

        resultSet.close();
        statement.close();
        database.CloseConnection();

        return this ;

    }*/

}