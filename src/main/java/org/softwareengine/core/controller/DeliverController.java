package org.softwareengine.core.controller;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.JasperViewer;
import org.softwareengine.core.model.*;
import org.softwareengine.config.languages;
import org.softwareengine.core.view.DeliverView;
import org.softwareengine.utils.ui.FXDialog;
import org.controlsfx.control.Notifications;
import org.softwareengine.utils.ui.report;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Locale;

public class DeliverController {


    public DeliverView view;



    public FXDialog dialog ;

    public int itemID   ;
    public int storeID  ;
    public int bankID;

    public DeliverController() {
        view = new DeliverView();


        try {
            initiate();
            setupLanguages() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void setupLanguages() {
        languages lang = new languages();

        view.itemTex.setText(lang.getWord("item"));
        view.numberTex.setText(lang.getWord("number"));
        view.bankTex.setText(lang.getWord("bank"));
        view.storeTex.setText(lang.getWord("store"));
        view.saveButton.setText(lang.getWord("save"));
        view.printButton.setText(lang.getWord("print"));

        ((TableColumn) view.tableView.getColumns().get(0)).setText(lang.getWord("id"));//id
        ((TableColumn) view.tableView.getColumns().get(1)).setText(lang.getWord("item"));//item
        ((TableColumn) view.tableView.getColumns().get(2)).setText(lang.getWord("store"));//store
        ((TableColumn) view.tableView.getColumns().get(3)).setText(lang.getWord("bank"));//driver
        ((TableColumn) view.tableView.getColumns().get(4)).setText(lang.getWord("number"));//num
        ((TableColumn) view.tableView.getColumns().get(5)).setText(lang.getWord("date"));//date

        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar"))
            view.root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        else
            view.root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

    }
    private void initiate() throws SQLException {
        getTableColum();

        view.num.setOnKeyReleased(onNumTextPressed());
        view.Vitem.setOnAction(onItem_V_Action());
        view.Vstore.setOnAction(onStore_V_Action());
        view.Vbank.setOnAction(onBank_V_Action());
        view.tableView.setOnKeyPressed(onTablePressed());
        view.saveButton.setOnAction(OnSaveButton());
        view.printButton.setOnAction(onPrintButton());

    }

    private void getTableColum() throws SQLException {


        TableColumn<Integer, Transaction> id = new TableColumn<>();

        TableColumn<String, Transaction> item = new TableColumn<>();

        TableColumn<String, Transaction> store = new TableColumn<>();

        TableColumn<String, Transaction> bank = new TableColumn<>();

        TableColumn<String, Transaction> num = new TableColumn<>();

        TableColumn<String, Transaction> date = new TableColumn<>();


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        item.setCellValueFactory(new PropertyValueFactory<>("item"));
        store.setCellValueFactory(new PropertyValueFactory<>("store"));
        num.setCellValueFactory(new PropertyValueFactory<>("number"));
        bank.setCellValueFactory(new PropertyValueFactory<>("bank"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));


        id.setMaxWidth(50);
        id.setMinWidth(25);

        view.tableView.getColumns().add(id);
        view.tableView.getColumns().add(item);
        view.tableView.getColumns().add(store);
        view.tableView.getColumns().add(bank);
        view.tableView.getColumns().add(num);
        view.tableView.getColumns().add(date);

        getTableDetail();

    }

    private void getTableDetail() throws SQLException {

        Transaction model = new Transaction();

        view.tableView.setItems(model.getInfoTransactions());

    }

    private EventHandler<KeyEvent> onNumTextPressed () {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

//                String text = view.num.getText();

//                if (!text.matches("[0-9]+"))
//                    view.num.("");

            }
        };
    }

    private EventHandler<ActionEvent> onItem_V_Action () {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                dialog = new FXDialog(view.pane, "Item List . . . ",false);





                Item item  = new Item();
                Amount amount = new Amount();


                int i = 0;
                int size = 0;
                try {

                    amount.setStoreID(storeID);

                    size = amount.getInfoItemInSpecStore().size();


                    while (i < size)
//                        dialog.listView.getItems().add(item.getInfo().get(i++).getName());
                        dialog.listView.getItems().add(amount.getInfoItemInSpecStore().get(i++).getItem());


                    dialog.show();
                    dialog.listView.setOnKeyPressed(OnListPressed("item"));
                    dialog.listView.setOnMouseClicked(OnMouseClick("item"));

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };


    }

    private EventHandler<ActionEvent> onBank_V_Action() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                dialog = new FXDialog(view.pane, "Banks List . . . ",false);


                banks bank  = new banks();


                int i = 0;
                int size = 0;
                try {
                    size = bank.getInfo().size();
                    System.out.println("the size is " + size);

                    while (i < size)
                        dialog.listView.getItems().add(bank.getInfo().get(i++).getName());



                    dialog.show();
                    dialog.listView.setOnKeyPressed(OnListPressed("banks"));
                    dialog.listView.setOnMouseClicked(OnMouseClick("banks"));

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };


    }

    private EventHandler<ActionEvent> onStore_V_Action () {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                dialog = new FXDialog(view.pane, "Store List . . . ",false);


                Store store  = new Store();


                int i = 0;
                int size = 0;
                try {
                    size = store.getInfo().size();
                    System.out.println("the size is " + size);

                    while (i < size)
                        dialog.listView.getItems().add(store.getInfo().get(i++).getName());



                    dialog.show();
                    dialog.listView.setOnKeyPressed(OnListPressed("store"));
                    dialog.listView.setOnMouseClicked(OnMouseClick("store"));

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };


    }


    private EventHandler<KeyEvent> OnListPressed(String thing){
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (!(event.getCode() == KeyCode.ENTER))
                    return;
                ListEvent(thing);



                dialog.dialog.close();

            }
        } ;
    }

    private EventHandler<MouseEvent> OnMouseClick (String thing) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() < 2 )
                    return;
                ListEvent(thing);
                dialog.dialog.close();
            }
        } ;
    }



    private void ListEvent(String thing) {


        int index;


        index = dialog.listView.getSelectionModel().getSelectedIndex();



        Item item   = new Item();
        Store store  = new Store();
//        type driver = new type();
        banks bank = new banks();


        System.out.println(dialog.listView.getSelectionModel().getSelectedIndex());

        try {


            switch (thing) {
                case "item" :
                    Amount amount = new Amount();

                    amount.setStoreID(storeID);

                    int initvalue = 0 , max = amount.getInfoItemInSpecStore().get(index).getNum() , min = 1 ;

                    initvalue = max ;

//                    itemID = item.getInfo().get(index).getId();
                    itemID = amount.getInfoItemInSpecStore().get(index).getItemID() ;
                    view.item.setText(amount.getInfoItemInSpecStore().get(index).getItem());


                    SpinnerValueFactory<Integer> num =   new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initvalue);

//                    view.num.setEditable(true);


                    view.num.setValueFactory(num);
                    view.num.getValueFactory().wrapAroundProperty().set(true);

//                    view.num.getValueFactory().wrapAroundProperty().set(true);
//                    view.num.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

//                    view.num.setText(amount.getInfoItemInSpecStore().get(index).getNum()+"");
//                    view.item.setText(item.getInfo().get(index).getName());

                    getTableDetail();
                    break;
                case "store" :
                    storeID = store.getInfo().get(index).getId() ;
                    view.store.setText(store.getInfo().get(index).getName());

//                    view.num.clear();
                    view.item.clear();


                    break;
                case "banks" :
                    bankID = bank.getInfo().get(index).getId() ;
                    view.bank.setText(bank.getInfo().get(index).getName());
                    break;
            }


//                        getTableDetailTo();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private EventHandler<KeyEvent> onTablePressed () {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {


                if (!(event.getCode() == KeyCode.ENTER))
                    return;

                String text [] = {
                        "store Name : "
                } ;
//                dialog = new updateDialog(view.pane ,"update item . . . ",1,text) ;

                Amount model = new Amount() ;

                int index = view.tableView.getSelectionModel().getSelectedIndex() ;


                String name ;
                try {
                    storeID = model.getInfoID().get(index).getId() ;
                    System.out.println("the ID = "+ storeID);
//                    name = model.getInfo().get(index).getName() ;
//                    System.out.println("the name = "+name);


//                    dialog.tf1.setText(name);//t1 for item name
//
//                    dialog.tf1.setId("name");


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


//                dialog.show();

//                dialog.tf1.setOnKeyPressed(onUpdate());


            }} ;
    }


    private EventHandler<KeyEvent> onUpdate() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (!(event.getCode()== KeyCode.ENTER))
                    return;

                TextField t = (TextField) event.getSource() ;


                try {
                    Amount model = new Amount();
                    System.out.println(storeID);
                    model.setId(storeID);
//                    model.setName(t.getText());
                    model.update();
                    System.out.println("data is updated . . . ");
//                    dialog.dialog.close();
                    getTableDetail();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        };

    }

    private boolean get() {
        Amount amount = new Amount() ;
        return false ;
    }

    private EventHandler<ActionEvent> OnSaveButton() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Amount amount = new Amount();

                amount.setItemID(itemID);
                amount.setStoreID(storeID);

                int amountNumber = 0 ;
                try {
                    amountNumber = amount.getInfoNumber().getNum() ;
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                Transaction model = new Transaction();
//                model.setName(view.store.getText());
                model.setItemID(itemID);
                model.setStoreID(storeID);
                model.setBankID(bankID);
                model.setDate(view.date.getValue().toString());

                int num = view.num.getValue() ;
                model.setNumber(num);

                try {
                    if (num <= amountNumber) {
                        Transfer transfer = new Transfer();
                        transfer.setItemID(itemID);
                        transfer.setStoreID(storeID);
                        transfer.setNum(num);
                        transfer.drop();

                        model.save();
                        model.update();

                        view.store.clear();
                        view.num.getEditor().clear();
                        view.num.setValueFactory(null);
                        view.item.clear();
                        view.bank.clear();

                    }else
                        Notifications.create().title("error").text("the number is not avalible . . . ").darkStyle().hideAfter(Duration.seconds(5)).showWarning();

                    getTableDetail();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };
    }// save Button


    private EventHandler<ActionEvent> onPrintButton() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                report re = new report();

                try {
                    JasperViewer.viewReport(re.getDistrubumentReport(),false);
                } catch (JRException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("Done here man . . .");
            }
        };
    }

}