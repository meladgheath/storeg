package org.softwareengine.core.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.JasperViewer;
import org.softwareengine.core.model.Amount;
import org.softwareengine.core.view.AmountView;
import org.softwareengine.utils.ui.FXDialog;
import org.softwareengine.core.model.Item;
import org.softwareengine.config.languages;
import org.softwareengine.core.model.Store;
import org.softwareengine.utils.ui.report;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Locale;

public class AmountController {
    public AmountView view;


    public FXDialog dialog;

    public int itemID;
    public int storeID;

    public AmountController() {
        view = new AmountView();


        try {
            initiate();
            setupLangauges();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void setupLangauges() {
        languages lang = new languages();

        view.storeTex.setText(lang.getWord("store"));
        view.itemTex.setText(lang.getWord("item"));
        view.numTex.setText(lang.getWord("number"));
        view.saveButton.setText(lang.getWord("save"));
        view.printButton.setText(lang.getWord("print"));

        ((TableColumn) view.tableView.getColumns().get(0)).setText(lang.getWord("id"));//id
        ((TableColumn) view.tableView.getColumns().get(1)).setText(lang.getWord("item"));//item
        ((TableColumn) view.tableView.getColumns().get(2)).setText(lang.getWord("store"));//store
        ((TableColumn) view.tableView.getColumns().get(3)).setText(lang.getWord("number"));//number

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
        view.tableView.setOnKeyPressed(onTablePressed());
        view.saveButton.setOnAction(OnSaveButton());
        view.printButton.setOnAction(onPrintButton());

    }

    private void getTableColum() throws SQLException {


        TableColumn<Integer, Amount> id = new TableColumn<>("#");

        TableColumn<String, Amount> item = new TableColumn<>("item");

        TableColumn<String, Amount> store = new TableColumn<>("store");

        TableColumn<String, Amount> num = new TableColumn<>("number");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        item.setCellValueFactory(new PropertyValueFactory<>("item"));
        store.setCellValueFactory(new PropertyValueFactory<>("store"));
        num.setCellValueFactory(new PropertyValueFactory<>("num"));


        id.setMaxWidth(50);
        id.setMinWidth(25);

        view.tableView.getColumns().add(id);
        view.tableView.getColumns().add(item);
        view.tableView.getColumns().add(store);
        view.tableView.getColumns().add(num);

        getTableDetail();

    }

    private void getTableDetail() throws SQLException {

        Amount model = new Amount();

        view.tableView.setItems(model.getInfo());

    }

    private EventHandler<KeyEvent> onNumTextPressed() {
        return event -> {
            String text = view.num.getText();
            if (!text.matches("[0-9]+")) view.num.setText("");
        };
    }

    private EventHandler<ActionEvent> onItem_V_Action() {
        return event -> {
            dialog = new FXDialog(view.pane, "Item List . . . ", false);
            Item item = new Item();

            int i = 0;
            int size = 0;
            try {
                size = item.getInfoIDs().size();
                System.out.println("the size is " + size);

                while (i < size) dialog.listView.getItems().add(item.getInfoIDs().get(i++).getName());

                dialog.show();
                dialog.listView.setOnKeyPressed(OnListPressed("item"));
                dialog.listView.setOnMouseClicked(OnMouseClick("item"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> onStore_V_Action() {
        return event -> {
            dialog = new FXDialog(view.pane, "Store List . . . ", false);

            Store store = new Store();
            int i = 0;
            int size = 0;
            try {
                size = store.getInfo().size();
                System.out.println("the size is " + size);

                while (i < size) dialog.listView.getItems().add(store.getInfo().get(i++).getName());

                dialog.show();
                dialog.listView.setOnKeyPressed(OnListPressed("store"));
                dialog.listView.setOnMouseClicked(OnMouseClick("store"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<KeyEvent> OnListPressed(String thing) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (!(event.getCode() == KeyCode.ENTER)) return;
                ListEvent(thing);

                dialog.dialog.close();

            }
        };
    }

    private EventHandler<MouseEvent> OnMouseClick(String thing) {
        return event -> {
            if (event.getClickCount() < 2) return;
            ListEvent(thing);
            dialog.dialog.close();
        };
    }


    private void ListEvent(String thing) {


        int index;


        index = dialog.listView.getSelectionModel().getSelectedIndex();


        Item item = new Item();
        Store store = new Store();


        System.out.println(dialog.listView.getSelectionModel().getSelectedIndex());

        try {
//            System.out.println("<<<< " + item.getInfo().get(index).get() + " >>>>");
//            System.out.println("<><><>" + item.getInfo().get(index).getId() + "<><><>");


//                        if (storeFrom != store.getInfoID().get(index).getId())
//                            storeFrom = store.getInfoID().get(index).getId();

                    /*    fromName = store.getInfo().get(index).getName();
                        view.from.setText(fromName);
*/
//                        getTableDetailFrom();


//                            storeTo = store.getInfoID().get(index).getId();

//                        toName = store.getInfo().get(index).getName();
//                        view.to.setText(toName);

            switch (thing) {
                case "item" -> {
                    //todo
                    itemID = item.getInfoIDs().get(index).getId();
                    System.out.println("this is the item id "+itemID);
                    view.item.setText(item.getInfo().get(index).getName());
                    getTableDetail();
                }
                case "store" -> {
                    storeID = store.getInfo().get(index).getId();
                    view.store.setText(store.getInfo().get(index).getName());
                }
            }


//                        getTableDetailTo();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private EventHandler<KeyEvent> onTablePressed() {
        return event -> {
            if (!(event.getCode() == KeyCode.ENTER)) return;

            String text[] = {"store Name : "};
//                dialog = new updateDialog(view.pane ,"update item . . . ",1,text) ;
            Amount model = new Amount();
            int index = view.tableView.getSelectionModel().getSelectedIndex();

            String name;
            try {
                storeID = model.getInfoID().get(index).getId();
                System.out.println("the ID = " + storeID);
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


        };
    }


    private EventHandler<KeyEvent> onUpdate() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (!(event.getCode() == KeyCode.ENTER)) return;

                TextField t = (TextField) event.getSource();
                System.out.println(t.getId());
                System.out.println(t.getText() + " yes that is mother fucker . . . ");


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

    private EventHandler<ActionEvent> OnSaveButton() {
        return event -> {

            Amount model = new Amount();
//                model.setName(view.store.getText());
            model.setItemID(itemID);
            model.setStoreID(storeID);
            model.setNum(Integer.parseInt(view.num.getText()));

            try {
                model.save();
                System.out.println("data is saved !");
                getTableDetail();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            view.store.clear();
            view.num.clear();
            view.item.clear();
        };
    }// save Button


    private EventHandler<ActionEvent> onPrintButton() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                report re = new report();

                try {
                    JasperViewer.viewReport(re.getAmountReport(),false);
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