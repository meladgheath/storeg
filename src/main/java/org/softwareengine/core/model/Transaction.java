package org.softwareengine.core.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.softwareengine.utils.service.DatabaseService;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transaction {

    private int id;
    private int seq ;
    private int itemID;
    private int storeID;
    private int bankID;
    private int number;

    private String item;
    private String store;
    private String bank;

    private String date;

    private String date_from ;
    private String date_to   ;
    private int userid ;

    private String refID ;


    private ByteArrayOutputStream img ;


    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

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
        String sql = "INSERT INTO transactions ( item , bank, store , number , date ,img, userid ) VALUES (?,?,?,?,?,?,?)";

        DatabaseService.openConnection();
        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);


        ps.setInt(1, this.itemID);
        ps.setInt(2, this.bankID);
        ps.setInt(3, this.storeID);
        ps.setInt(4, this.number);
        ps.setString(5, this.date);
        ps.setBytes(6,this.img.toByteArray());
        ps.setInt(7,user.getId());

        ps.executeUpdate();

        DatabaseService.CloseConnection();


    }

    public void update () throws SQLException {
        String sql = "UPDATE transactionss SET item = ? , bank = ? , number = ? , date = ?  WHERE id = "+this.id ;

        System.out.println(id + " the id is here ");
        DatabaseService.openConnection();
        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);
        ps.setInt(1, this.itemID);
        ps.setInt(2, this.bankID);
        ps.setInt(3, this.number);
        ps.setString(4, this.date);
        ps.executeUpdate();
        DatabaseService.CloseConnection();
        System.out.println("updated !!");

    }

    public void saves() throws SQLException {
        String sql = "INSERT INTO wahdabank.transactions ( item , bank,  number , date ,userid,\"refID\" ) VALUES (?,?,?,?,?,?)";

        DatabaseService.openConnection();
        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
//        LocalDate dd = LocalDate.parse(date,formatter);

        ps.setInt(1, this.itemID);
        ps.setInt(2, this.bankID);
        ps.setInt(3, this.number);
        ps.setString(4, this.date.toString());
//        ps.setBytes(5,(this.img != null ) ? this.img.toByteArray() : null);
        ps.setInt(5,user.getId());
        ps.setString(6,this.refID);

        ps.executeUpdate();

        DatabaseService.CloseConnection();
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

    public void getImagesss(File file) throws SQLException, IOException {

        String sql = "SELECT img from transactionss where id =  "+this.id;

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

    public InputStream getImage() throws SQLException, IOException {

        String sql = "SELECT img from transactionss where id =  "+this.id;


        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        InputStream in = null;
        OutputStream out ;
        int i = 0;
        while (resultSet.next()){
            in = resultSet.getBinaryStream(1);

        }

//        System.out.println(file.getPath());
//        out = new FileOutputStream(file) ;
//        int readnum = 0 ;
//
//        while ((readnum = in.read()) > -1 )
//            out.write(readnum);
//
//        out.close();
//        in.close();

        DatabaseService.CloseConnection();

        return in ;

    }

    public InputStream getBlob() throws SQLException, IOException {

        String sql = "SELECT img from transactionss where id =  "+this.id;

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

        DatabaseService.CloseConnection();
            return in ;

    }

    public ObservableList<Transaction> getInfoTransactionssORDERBYwhereDATE(String first , String second) throws SQLException {

        ObservableList<Transaction> list = FXCollections.observableArrayList();
        String sql = "SELECT (SELECT name FROM banks WHERE id = bank) as bank ,\n" +
                "                (SELECT name FROM item WHERE id = item ) as item ,\n" +
                "    sum(number) as number , date " +
                "    FROM transactionss " +
                "    WHERE date BETWEEN DATE(?) AND DATE(?) " +
                "    GROUP by bank , item , date" ;

        DatabaseService.openConnection();
        PreparedStatement statement = DatabaseService.connection.prepareStatement(sql);
        statement.setString(1,first);
        statement.setString(2,second);

        ResultSet resultSet = statement.executeQuery();

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

    public ObservableList<Transaction> getInfoTransactionssORDERBY() throws SQLException {

        ObservableList<Transaction> list = FXCollections.observableArrayList();
        String sql = "SELECT (SELECT name FROM wahdabank.banks WHERE id = bank) as bank , " +
                "                (SELECT name FROM wahdabank.item WHERE id = item ) as item , " +
                "                 sum(number) as number  " +
                "                 FROM wahdabank.transactions " +
                " GROUP by bank , item " ;

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
//            one.setDate(resultSet.getString("date"));

            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list;
    }
    public ObservableList<Transaction> getInfoTransactionss() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = """
                SELECT (SELECT name FROM banks WHERE id = bank) as bank ,
                (SELECT name FROM item WHERE id = item ) as item ,
                 number , date
                 FROM transactionss
                 """;
//        order by date desc

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

    public ObservableList<Transaction> getInfoORDERBYdate() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = """
                SELECT id , (SELECT name FROM wahdabank.banks WHERE id = bank) as bank ,
                (SELECT name FROM wahdabank.item WHERE id = item ) as item ,
                 number , date, "refID"
                 FROM wahdabank.transactions ORDER BY date
                 """;
//        order by date desc

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setSeq(++i);
            one.setId(resultSet.getInt("id"));
            one.setItem(resultSet.getString("item"));
            one.setBank(resultSet.getString("bank"));
            one.setNumber(resultSet.getInt("number"));
            one.setDate(resultSet.getString("date"));
            one.setRefID(resultSet.getString("refID"));
            list.add(one);
        }
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();
        return list;
    }

    public ObservableList<Transaction> getInfoWHEREitemID() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = "SELECT (SELECT name FROM wahdabank.banks WHERE id = bank) as bank , (SELECT name FROM wahdabank.item WHERE id = item ) as item ," +
                "number , date FROM wahdabank.transactions where item = "+itemID ;

//        order by date desc

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(++i);
            one.setSeq(i);
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

    public ObservableList<Transaction> getInfoWHEREbankIDWithDate() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = "SELECT (SELECT name FROM wahdabank.banks WHERE id = bank) as bank , (SELECT name FROM wahdabank.item WHERE id = item ) as item ," +
                "number , date FROM wahdabank.transactions where bank = "+bankID +" and date = ? " ;

        DatabaseService.openConnection();

        PreparedStatement statement = DatabaseService.connection.prepareStatement(sql);


        statement.setString(1,this.date_from);

        ResultSet resultSet = statement.executeQuery();

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(++i);
            one.setSeq(i);
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
    public ObservableList<Transaction> getInfoWHEREitemIDWithDate() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = "SELECT (SELECT name FROM wahdabank.banks WHERE id = bank) as bank , (SELECT name FROM wahdabank.item WHERE id = item ) as item ," +
                "number , date FROM wahdabank.transactions where item = "+itemID +" and date = ? " ;
                //" and date BETWEEN ? AND ?";

//        order by date desc

        DatabaseService.openConnection();
//        PreparedStatement statement = DatabaseService.connection.createStatement();
        PreparedStatement statement = DatabaseService.connection.prepareStatement(sql);

        System.out.println(date_from+" here is the date from ");
        statement.setString(1,this.date_from);
//        statement.setString(2,date_to);

        ResultSet resultSet = statement.executeQuery();

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(++i);
            one.setSeq(i);
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

    public ObservableList<Transaction> getInfoWHEREitemIDWithTwoDate() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();

        String sql = "SELECT (SELECT name FROM wahdabank.banks WHERE id = bank) as bank , (SELECT name FROM wahdabank.item WHERE id = item ) as item ," +
                "number , date FROM wahdabank.transactions where item = "+itemID +" and date BETWEEN ? AND ?";

        DatabaseService.openConnection();

        PreparedStatement statement = DatabaseService.connection.prepareStatement(sql);

        System.out.println(date_from+" here is the date from ");
        statement.setString(1,this.date_from);
        statement.setString(2,this.date_to);

        ResultSet resultSet = statement.executeQuery();

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(++i);
            one.setSeq(i);
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

    public ObservableList<Transaction> getInfoWHEREbankIDWithTwoDate() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();

        String sql = "SELECT (SELECT name FROM wahdabank.banks WHERE id = bank) as bank , (SELECT name FROM wahdabank.item WHERE id = item ) as item ," +
                "number , date FROM wahdabank.transactions where bank = "+bankID +" and date BETWEEN ? AND ?";


        DatabaseService.openConnection();

        PreparedStatement statement = DatabaseService.connection.prepareStatement(sql);


        statement.setString(1,this.date_from);
        statement.setString(2,this.date_to);

        ResultSet resultSet = statement.executeQuery();

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(++i);
            one.setSeq(i);
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


    public ObservableList<Transaction> getInfoWhereBankLastDate() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
//        String sql = "select  * from transactionss where bank = ? and date =  ?";
        String sql = "SELECT (SELECT name FROM wahdabank.banks WHERE id = bank) as bank , (SELECT name FROM wahdabank.item WHERE id = item ) as item , " +
                " number , date FROM wahdabank.TRANSACTIONs WHERE bank = ? AND (date BETWEEN ? AND (SELECT max(date) FROM wahdabank.transactions)) order by date" ;
//        order by date desc

        DatabaseService.openConnection();

        PreparedStatement statement = DatabaseService.connection.prepareStatement(sql);

        statement.setInt(1,this.bankID);
        statement.setString(2,this.date);
//        statement.setString(2,this.date_to);

        ResultSet resultSet = statement.executeQuery();

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(++i);
            one.setSeq(i);
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

    public ObservableList<Transaction> getInfoWHEREitemIDWithLastDate() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = "SELECT (SELECT name FROM wahdabank.banks WHERE id = bank) as bank , (SELECT name FROM wahdabank.item WHERE id = item ) as item ," +
                "number , date FROM wahdabank.TRANSACTIONs WHERE item = ? AND (date BETWEEN ? AND (SELECT max(date) FROM wahdabank.transactions)) order by date";

//        order by date desc

        DatabaseService.openConnection();

        PreparedStatement statement = DatabaseService.connection.prepareStatement(sql);

        statement.setInt(1,this.itemID);
        statement.setString(2,this.date);
//        statement.setString(2,this.date_to);

        ResultSet resultSet = statement.executeQuery();

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(++i);
            one.setSeq(i);
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

    public ObservableList<Transaction> getInfoWHERErefID() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();

        String sql = "SELECT (SELECT name FROM wahdabank.banks WHERE id = bank) as bank , (SELECT name FROM wahdabank.item WHERE id = item ) as item ,\n" +
                " number , date FROM wahdabank.transactions where \"refID\"  = '"+refID.trim()+"'" ;

//        order by date desc

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

    public ObservableList<Transaction> getInfoWHEREbankID() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();

        String sql = "SELECT (SELECT name FROM wahdabank.banks WHERE id = bank) as bank , (SELECT name FROM wahdabank.item WHERE id = item ) as item ," +
                "number , date FROM wahdabank.transactions where bank = "+bankID ;

//        order by date desc

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
//            one.setStore(resultSet.getString("store"));
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

    public ObservableList<Transaction> getInfoTransactionssID() throws SQLException {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = """
                SELECT id , (SELECT name FROM banks WHERE id = bank) as bank ,
                (SELECT name FROM item WHERE id = item ) as item ,
                 number , date
                 FROM transactionss 
                 """;
//        order by date desc
        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0;
        while (resultSet.next()) {
            Transaction one = new Transaction();

            one.setId(resultSet.getInt("id"));
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

    public void delete() throws SQLException {
        String sql = "DELETE FROM transactionss WHERE id = "+this.id;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement() ;
        statement.executeUpdate(sql);

        DatabaseService.CloseConnection();
    }
    public ObservableList<Amount> getInfo() throws SQLException {
        ObservableList<Amount> list = FXCollections.observableArrayList();
//            String sql = "SELECT  (SELECT name FROM item where id = itemid) as item , (SELECT name FROM store where id = storeid) as store , num FROM amount";
        String sql = "SELECT  (SELECT name FROM item WHERE id = itemid) as item " + ", (SELECT name FROM store WHERE id = storeid) as store , sum(num) as num " +
                " FROM amount   GROUP by itemid";

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

}