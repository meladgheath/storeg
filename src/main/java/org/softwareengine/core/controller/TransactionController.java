package org.softwareengine.core.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.softwareengine.core.model.Amount;
import org.softwareengine.core.view.TransactionView;

import java.sql.SQLException;

public class TransactionController {


    public TransactionView view;

//    updateDialog dialog ;
    int storeID;
    String date = "current_date";

    public TransactionController() {
        view = new TransactionView();


        try {
            initiate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void initiate() throws SQLException {
        getTableColum();
        view.date.setOnAction(onDatePicker());
        view.checkBox.setOnAction(onCheckBox());
        view.print.setOnAction(onPrintButton());

    }

    private  EventHandler<ActionEvent> onPrintButton () {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                /*if ((!view.checkBox.isSelected())&&(view.date.getEditor().getText().equals("")))
                    return;

                main.model.transactiontransactions transactions = new model.transactions();

                if (view.checkBox.isSelected())
                    transactions.setDate("current_date");
                else if (!view.date.getEditor().getText().equals(""))
                    transactions.setDate(view.date.getEditor().getText());
                try {
                    transactions.getReport().show(false);
                } catch (DRException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                    *//*JasperReport jasperReport = null ;
                    JRDataSource dataSource   = null ;
                    JasperPrint print = null ;
                    File file = new File("Blank_A4.jrxml");
                    try {
                      jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath()) ;
                    } catch (JRException e) {
                        e.printStackTrace();
                    }
                    dataSource = new JREmptyDataSource();

                    System.out.println(file.getAbsolutePath());
                    System.out.println(file.getPath());

                    try {
                         print = JasperFillManager.fillReport(jasperReport,null,dataSource);
                        JasperViewer.viewReport(print,false);


                        System.out.println("here the third . . . ");
                    } catch (JRException e) {
                        e.printStackTrace();
                    }
                    System.out.println("here the second . . .");*/
            }
        };
    }

    private EventHandler<ActionEvent> onDatePicker () {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                    date = view.date.getValue().toString() ; //yyyy - MM - dd  example -> 2020-07-23
                date = view.date.getEditor().getText() ; // dd/MM/yyyy   example -> 23/07/2020
                System.out.println("'"+date+"'");
                try {
                    getTableDetail() ;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
    }

    private EventHandler<ActionEvent> onCheckBox() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    getTableDetail();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
    }

    private void getTableColum() throws SQLException {



        TableColumn<Integer, Amount> id = new TableColumn<>("#");
        TableColumn<String, Amount> store = new TableColumn<>("Hold");
        TableColumn<String, Amount> item = new TableColumn<>("Item");
        TableColumn<Integer, Amount> num = new TableColumn<>("Number");
        TableColumn<String, Amount> state = new TableColumn<>("State");
//        TableColumn<Date, main.model.amount> date = new TableColumn<>("date");
//        TableColumn<String, main.model.amount> store = new TableColumn<>("store");
//        TableColumn<Integer, model.transactions> bild = new TableColumn<>("bild");
//        TableColumn<String, model.transactions> tresury = new TableColumn<>("tresury");




        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        store.setCellValueFactory(new PropertyValueFactory<>("store"));
        item.setCellValueFactory(new PropertyValueFactory<>("item"));
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        state.setCellValueFactory(new PropertyValueFactory<>("stateName"));
//        date.setCellValueFactory(new PropertyValueFactory<>("date"));
//        store.setCellValueFactory(new PropertyValueFactory<>("store"));
//        bild.setCellValueFactory(new PropertyValueFactory<>("bildID"));
//        tresury.setCellValueFactory(new PropertyValueFactory<>("tresuryName"));

        id.setMaxWidth(50);
        id.setMinWidth(25);

        view.tableView.getColumns().add(id);
        view.tableView.getColumns().add(store);
        view.tableView.getColumns().add(item);
        view.tableView.getColumns().add(num) ;
        view.tableView.getColumns().add(state) ;
//        view.tableView.getColumns().add(date);
//        view.tableView.getColumns().add(store);
//        view.tableView.getColumns().add(bild);
//        view.tableView.getColumns().add(tresury);

        getTableDetail();
  }

    private void getTableDetail() throws SQLException {

        Amount model = new Amount();
        //view.tableView.setItems(model.getInfoTransactions());

      /*  model.transactions model = new model.transactions();
        model.setDate(date);
        if (view.checkBox.isSelected())
            view.tableView.setItems(model.getInfo());
        else
            view.tableView.setItems(model.getInfoWHEREdate());*/
    }
}

