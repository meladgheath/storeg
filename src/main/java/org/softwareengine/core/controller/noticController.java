package org.softwareengine.core.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;

import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.FileChooser;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.JasperViewer;


import org.softwareengine.config.languages;
import org.softwareengine.core.model.*;
import org.softwareengine.core.view.noticview;
import org.softwareengine.utils.ui.FXDialog;

import org.softwareengine.utils.ui.FXDialogTest;
import org.softwareengine.utils.ui.UpdateDialog;
import org.softwareengine.utils.ui.report;

import java.io.*;

import java.sql.SQLException;
import java.util.Locale;
import java.util.regex.Pattern;


public class noticController {

        public noticview view;

        public FXDialogTest dialogx ;
        public FXDialog dialog ;

        public int itemID   ;
        public int storeID  ;
        public int bankID;

        String itemName ;
        String ref ;

        ByteArrayOutputStream bos ;

        ObservableList<banks> list ;

        public noticController() {
            view = new noticview();


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
//            view.storeTex.setText(lang.getWord("store"));
            view.saveButton.setText(lang.getWord("save"));
            view.printButton.setText(lang.getWord("print"));
            view.dateTex.setText(lang.getWord("date"));
            view.printMenu.setText(lang.getWord("print"));
            view.detailMenu.setText(lang.getWord("detail"));
            view.downloadMenu.setText(lang.getWord("download"));
            view.updateMenu.setText(lang.getWord("update"));
            view.deleteMenu.setText(lang.getWord("delete"));

            ((TableColumn) view.tableView.getColumns().get(0)).setText(lang.getWord("id"));//id
            ((TableColumn) view.tableView.getColumns().get(1)).setText(lang.getWord("item"));//item

            ((TableColumn) view.tableView.getColumns().get(2)).setText(lang.getWord("bank"));//driver
            ((TableColumn) view.tableView.getColumns().get(3)).setText(lang.getWord("number"));//num
            ((TableColumn) view.tableView.getColumns().get(4)).setText(lang.getWord("date"));//date
            ((TableColumn) view.tableView.getColumns().get(5)).setText(lang.getWord("refID"));

            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar"))
                view.root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            else
                view.root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        }
        private void initiate() throws SQLException {
            getTableColum();

            view.attuchemnt.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(Paths.ATTUCH.getPath()))));
            view.num.setOnKeyReleased(onNumTextPressed());
            view.Vitem.setOnAction(onItem_V_Action("item"));
            view.Vstore.setOnAction(onStore_V_Action());
            view.Vbank.setOnAction(onBank_V_Action("banks"));

            view.tableView.setOnKeyPressed(onTablePressed());
            view.tableView.setOnMouseClicked(ontableClick());

            view.saveButton.setOnAction(OnSaveButton());
            view.printButton.setOnAction(onPrintButton());
            view.printMenu.setOnAction(onPrintMenu());
            view.detailMenu.setOnAction(onDetailMenu());
            view.downloadMenu.setOnAction(onDownloadMenu());
            view.updateMenu.setOnAction(onUpdateMenu());
            view.deleteMenu.setOnAction(onDeleteMenu());
            view.attuchemnt.setOnAction(onAttu());


            view.tableView.getItems().addListener(onTableviewChangeReverse());

        }

        private ListChangeListener onTableviewChangeReverse() {
            return new ListChangeListener() {
                @Override
                public void onChanged(Change c) {

              ObservableList<Transaction> transactions = view.tableView.getItems();

              int size = transactions.size();
              int i = 0 ;
              while (i < size)
                  transactions.get(i).setSeq(++i);
              view.tableView.setItems(transactions);
                }
            };
        }

        private void getTableColum() throws SQLException {


            TableColumn<Integer, Transaction> id = new TableColumn<>();
            TableColumn<String, Transaction> item = new TableColumn<>();
            TableColumn<String, Transaction> bank = new TableColumn<>();
            TableColumn<String, Transaction> num = new TableColumn<>();
            TableColumn<String, Transaction> date = new TableColumn<>();
            TableColumn<String, Transaction> refID = new TableColumn<>();


            id.setCellValueFactory(new PropertyValueFactory<>("seq"));
            item.setCellValueFactory(new PropertyValueFactory<>("item"));
            num.setCellValueFactory(new PropertyValueFactory<>("number"));
            bank.setCellValueFactory(new PropertyValueFactory<>("bank"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            refID.setCellValueFactory(new PropertyValueFactory<>("refID"));


            id.setMaxWidth(75);
            id.setMinWidth(50);

            view.tableView.getColumns().add(id);
            view.tableView.getColumns().add(item);
            view.tableView.getColumns().add(bank);
            view.tableView.getColumns().add(num);
            view.tableView.getColumns().add(date);
            view.tableView.getColumns().add(refID);

            getTableDetail();

        }

        private void getTableDetail() throws SQLException {
            Transaction model = new Transaction();
            view.tableView.setItems(model.getInfoORDERBYdate());
        }

        private EventHandler<KeyEvent> onNumTextPressed () {
            return new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {


                }
            };
        }

        private EventHandler<ActionEvent> onItem_V_Action (String name) {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {


                    System.out.println(event.getEventType());
                    dialog = new FXDialog(view.pane, "Item List . . . ",false);

                    Item item  = new Item();
                    Amount amount = new Amount();


                    int i = 0;
                    int size = 0;
                    try {

                        amount.setStoreID(storeID);

                        size = item.getInfo().size();

                        while (i < size)
                            dialog.listView.getItems().add(item.getInfo().get(i++).getName());

                        dialog.show();
                        dialog.listView.setOnKeyPressed(OnListPressed(name));
                        dialog.listView.setOnMouseClicked(OnMouseClick(name));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            };


        }

        private EventHandler<ActionEvent> onBank_V_Action(String name ) {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {



                    dialogx = new FXDialogTest(view.pane, "Banks List . . . ",true,true);


                    banks bank  = new banks();


                    int i = 0;
                    int size = 0;
                    try {


                        size = bank.getInfo().size();
                        System.out.println("the size is " + size);

                        dialogx.banksListView.setItems(bank.getInfoIDs());
                        dialogx.banksListView.setCellFactory(param -> new ListCell<>(){
                            @Override
                            protected void updateItem(banks item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item == null) setText(null);
                                else setText(item.getName());
                            }
                        });



//                        dialog.listView.setItems(bank.getInfo());
//                        while (i < size)
//                            dialog.listView.getItems().add(bank.getInfo().get(i++).getName());

                        dialogx.show();
                        dialogx.banksListView.setOnKeyPressed(OnListPressed(name));
                        dialogx.banksListView.setOnMouseClicked(OnMouseClick(name));
                        dialogx.textField.setOnAction(DialogTextField(name));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            };
        }

        private EventHandler<ActionEvent> DialogTextField(String name ) {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    banks model = new banks();
                    dialogx.banksListView.getItems().removeAll(dialogx.banksListView.getItems());
                    String str = dialogx.textField.getText();
                    if (Pattern.matches("[0-9]+",dialogx.textField.getText())) {
                        try {
                            System.out.println("here");
                            int id = Integer.parseInt(dialogx.textField.getText()) ;
                            model.setReferenceNumber(dialogx.textField.getText());
                            list = model.getInfoWHEREref();
                            ref = model.getInfoWHEREref().get(0).getReferenceNumber();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }else
                        if (Pattern.matches("[\\w|\\W]+",dialogx.textField.getText()))
                        {
                            System.out.println("or here ");
                            try {
                                model.setName(dialogx.textField.getText());
                                list = model.getWHERElike();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }


                    int size = list.size();

                    int i = 0 ;
                    while (i < size)
                    dialogx.banksListView.getItems().add(list.get(i++));

                    dialogx.banksListView.setOnKeyPressed(OnListPressed(name));
                    dialogx.banksListView.setOnMouseClicked(OnMouseClick(name));
                }
            } ;
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

        private EventHandler<MouseEvent> ontableClick () {
            return new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if (event.isSecondaryButtonDown())
                        view.tableMenu.show(view.tableView,event.getScreenX(),event.getScreenY());

                    if (!(view.tableView.getSelectionModel().getSelectedIndex() == -1) )
                        image();
                }
            } ;
        }

        private void ListEvent(String thing) {

            int index;

            index = dialog.listView.getSelectionModel().getSelectedIndex();
            Item item   = new Item();
            Store store  = new Store();
            banks bank = new banks();


            System.out.println(dialog.listView.getSelectionModel().getSelectedIndex());

            try {

                switch (thing) {
                    case "item" :

                        Item model = new Item();
                        itemID = model.getInfoID().get(index).getId() ;
                        view.item.setText(model.getInfoID().get(index).getName());

                        getTableDetail();
                        break;
                    case "store" :

                        bank.setReferenceNumber(ref);
//                        bankID = bank.getInfoWHEREref().get(index).getId();
                        bankID = list.get(index).getId();
//                        view.bank.setText(bank.getInfoWHEREref().get(index).getName());
                        view.bank.setText(list.get(index).getName());

                        break;
                    case "onUpdate" :
                        int inde = dialogx.banksListView.getSelectionModel().getSelectedIndex() ;
//                        bank.setReferenceNumber(ref);
//                        bankID = list.get(index).getId();

                        bankID = dialogx.banksListView.getItems().get(inde).getId();
                        updateDialog.bank.setText(dialogx.banksListView.getItems().get(inde).getName());
                        dialogx.dialog.close();

                        System.out.println("the bank id is -> "+bankID);
                        System.out.println("the refer"+dialogx.banksListView.getItems().get(inde).getReferenceNumber());
                        break;
                    case "banks" :
//                        bankID = bank.getInfoIDs().get(index).getId() ;
                        index = dialogx.banksListView.getSelectionModel().getSelectedIndex() ;
                        bankID = dialogx.banksListView.getItems().get(index).getId();
//                        view.bank.setText(bank.getInfoIDs().get(index).getName());
                        view.bank.setText(dialogx.banksListView.getItems().get(index).getName());
                        dialogx.dialog.close();
                        break;
                    case "itemUpdate":
                        Item update = new Item();


                        itemID = update.getInfoID().get(index).getId() ;
//                        itemName = update.getInfoID().get(index).getName();
                        itemName = dialog.listView.getItems().get(index);
                        updateDialog.item.setText(itemName);

                        break;
                    case "bankUpdate" :
                        bankID = bank.getInfoIDs().get(index).getId() ;
                        updateDialog.bank.setText(bank.getInfoIDs().get(index).getName());
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        public void image()  {

            int index = view.tableView.getSelectionModel().getSelectedIndex();

                Transaction model = (Transaction) view.tableView.getItems().get(index);
                model.setId(model.getId());

            InputStream inputStream = null;
            try {
                inputStream = model.getImage();

                view.imageView.setImage(new Image(inputStream));
//                view.imageView.setFitWidth(180);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }catch (NullPointerException e) {
                view.imageView.setImage(null);
//                view.imageView.setFitWidth(0);
            }

        }
        private EventHandler<KeyEvent> onTablePressed () {
            return new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    if (!(view.tableView.getSelectionModel().getSelectedIndex() == -1) )
                        image();


                    if (!(event.getCode() == KeyCode.DELETE))
                        return;

                    int index = view.tableView.getSelectionModel().getSelectedIndex();
                    delelteRecord(index);
                }} ;
        }

        private void delelteRecord(int index) {
            Transaction model = new Transaction();
            try {
                 Transaction trans = (Transaction) view.tableView.getItems().get(index) ;

                model.setId(trans.getId());
                model.delete();

                view.tableView.getItems().remove(trans);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
                        model.setId(storeID);
                        model.update();
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
/*
                    int amountNumber = 0 ;
                    try {
                        amountNumber = amount.getInfoNumber().getNum() ;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    */

                    Transaction model = new Transaction();


                    model.setItemID(itemID);
                    model.setItem(view.item.getText());
                    model.setStoreID(storeID);
                    model.setBankID(bankID);

                    model.setDate(view.date.getValue().toString());
                    model.setBank(view.bank.getText());

                    model.setSeq(view.tableView.getItems().size()+1) ;

//                    model.setImg((bos == null) ? null : bos);
                    model.setRefID(view.refID.getText());

                    int num = Integer.parseInt(view.num.getText());
                    model.setNumber(num);


                    try {
                            model.saves();

                            view.num.clear();
                            view.item.clear();
                            view.bank.clear();
                            view.date.getEditor().clear();
                            view.date.setValue(null);
//                            getTableDetail();
                        view.tableView.getItems().add(model);

                        } catch (SQLException ex) {
                        ex.printStackTrace();
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
                        JasperViewer.viewReport(re.getDistrubumentReport(view.tableView.getItems(),"disbursementReport.jrxml"),false);

                    } catch (JRException e) {
                        e.printStackTrace();
                    }
                }
            };
        }

        private EventHandler<ActionEvent> onPrintMenu() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    report re = new report();


                    Transaction transaction = new Transaction();

                    try {
                        transaction = transaction.getInfoTransactions().get(view.tableView.getSelectionModel().getSelectedIndex());
                        JasperViewer.viewReport(re.getCoffee(transaction),false);
                    } catch (JRException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        }



        private EventHandler<ActionEvent> onDetailMenu() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            };
        }

        private EventHandler<ActionEvent> onDownloadMenu() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {


                    int index = view.tableView.getSelectionModel().getSelectedIndex();
                    Transaction model = new Transaction();
                    try {
                        model.setId(model.getInfoTransactionssID().get(index).getId());
                        System.out.println(index);
                        System.out.println(model.getInfoTransactionssID().get(index).getId());
                        System.out.println(model.getId());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    FileChooser file = new FileChooser();
                    file.setInitialFileName("doc.png");


                    try {

                        model.getImagesss(file.showSaveDialog(null));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            };
        }
    UpdateDialog updateDialog;
    private EventHandler<ActionEvent> onUpdateMenu() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                 updateDialog = new UpdateDialog(view.pane,"update :") ;

                updateDialog.Vitem.setOnAction(onItem_V_Action("itemUpdate"));
                updateDialog.Vbank.setOnAction(onBank_V_Action("onUpdate"));

                updateDialog.update.setOnAction(onUpdateAction());
//                dialog.textField.setOnAction(DialogTextField("onUpdate"));

                updateDialog.show();


            }
        };
    }

    private EventHandler<ActionEvent> onDeleteMenu() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int index = view.tableView.getSelectionModel().getSelectedIndex() ;
                delelteRecord(index);

            }
        };
    }
    private EventHandler<ActionEvent> onUpdateAction(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Transaction model = new Transaction();
                int index = view.tableView.getSelectionModel().getSelectedIndex();


                Transaction transaction = (Transaction) view.tableView.getItems().get(index);

                view.tableView.getItems().remove(transaction);

                transaction.setBankID(bankID);
                transaction.setItemID(itemID);
                transaction.setDate(updateDialog.date.getValue().toString());
                transaction.setNumber(Integer.parseInt(updateDialog.number.getText()));


                transaction.setItem(updateDialog.item.getText());
                transaction.setBank(updateDialog.bank.getText());

                try {
                    transaction.update();
                    updateDialog.dialog.close();
                    view.tableView.getItems().add(transaction);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
        private EventHandler<ActionEvent> onAttu() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {



                    view.saveButton.setDisable(true);
                    view.printButton.setDisable(true);

                    FileChooser files = new FileChooser();

                    File file = files.showOpenDialog(null);
                    FileInputStream in = null;

                    try {
                        in = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    bos = new ByteArrayOutputStream();

                    byte[] buf = new byte[1024] ;

                    try {
                        int i = 0 ;
                        while ((i = in.read(buf)) != -1 ) {
                            bos.write(buf, 0, i);
                        }
                        in.close();
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    view.saveButton.setDisable(false);
                    view.printButton.setDisable(false);
                }
            };
        }
    }