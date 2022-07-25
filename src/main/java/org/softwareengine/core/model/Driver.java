package org.softwareengine.core.model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.softwareengine.utils.service.DatabaseService;

import java.io.*;
import java.sql.*;


public class Driver {


    private int id ;
    private String name ;
    private String phoneNo ;
    private double salary  ;
    private String date    ;
    private double money ;
    private File image ;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

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


    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void save() throws SQLException, FileNotFoundException {
        String sql = "INSERT  INTO  driver ( name , phoneno  ) values (?,?)" ;

        DatabaseService.openConnection();
        PreparedStatement ps = DatabaseService.connection.prepareStatement(sql);


        ps.setString(1,this.name    );
        ps.setString(2,this.phoneNo);


        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
        LocalDate d = LocalDate.parse(date,formatter);

        ps.setObject(4,d);

        FileInputStream inputStream = new FileInputStream(this.image);

        ps.setBinaryStream(5,inputStream,image.length());
*/


        ps.executeUpdate();

        DatabaseService.CloseConnection();
    }

    public ObservableList<Driver> getInfo() throws SQLException {
        ObservableList<Driver> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM driver ORDER BY id";

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        while (resultSet.next()) {
            Driver one = new Driver() ;

            one.setId(++i);
            one.setName(resultSet.getString("name"));
            one.setPhoneNo(resultSet.getString("phoneNo"));
//            one.setDate(resultSet.getString("date"));
//            one.setSalary(resultSet.getDouble("salary"));
            System.out.println(one.getPhoneNo());
            list.add(one);
        }
        return list ;
    }


    public  boolean check () throws SQLException {

        String sql = "SELECT id FROM driver WHERE id = "+this.id ;
        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery(sql);
        boolean result ;
        if (resultSet.next())
            result = true ;
        else
            result = false ;
        resultSet.close();
        statement.close();
        DatabaseService.CloseConnection();

        return result  ;

    }

    public Driver getInfoWHEREid() throws SQLException, IOException {
        ObservableList<Driver> list = FXCollections.observableArrayList();
        String sql = "SELECT * , current_date - date as daywork FROM employment WHERE id = "+this.id;

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0 ;
        resultSet.next() ;
        Driver one = new Driver() ;


        one.setName(resultSet.getString("name"));
        one.setPhoneNo(resultSet.getString("phoneno"));
        one.setDate(resultSet.getString("date"));
        one.setSalary(resultSet.getDouble("salary"));


        byte[] image = resultSet.getBytes("image") ;

        FileOutputStream out = new FileOutputStream("melad.jpg");
        ByteArrayInputStream b = new ByteArrayInputStream(image);

        int j ;
        while ((j = b.read())!=-1){
            out.write(j);
        }

        this.image = new File("melad.jpg") ;

        one.setImage(this.image);

        double salary = one.getSalary() ;
        int day = resultSet.getInt("daywork") ;
        System.out.println(salary);
        salary = (salary/30) ;
        System.out.println(salary);
        salary = salary * day ;
        System.out.println(salary);

        one.setMoney(salary);
        DatabaseService.CloseConnection();

        return one ;
    }


    public ObservableList<Driver> getInfoID() throws SQLException {
        ObservableList<Driver> list = FXCollections.observableArrayList();
        String sql = "SELECT id FROM employment ";

        DatabaseService.openConnection();
        Statement statement = DatabaseService.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);


        while (resultSet.next()) {
            Driver one = new Driver() ;

            one.setId(resultSet.getInt("id"));

            list.add(one);
        }
        return list ;
    }
    public void deleteImage (){
        System.out.println(this.image.delete());
        System.out.println("final man ");
    }
}