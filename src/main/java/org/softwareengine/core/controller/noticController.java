package org.softwareengine.core.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.JasperViewer;

import org.softwareengine.config.languages;
import org.softwareengine.core.model.*;
import org.softwareengine.core.view.noticview;
import org.softwareengine.utils.ui.FXDialog;

import org.softwareengine.utils.ui.report;

import java.io.*;
import java.sql.SQLException;
import java.util.Locale;

public class noticController {

        public noticview view;

        public FXDialog dialog ;

        public int itemID   ;
        public int storeID  ;
        public int bankID;

        ByteArrayOutputStream bos ;

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
            view.storeTex.setText(lang.getWord("store"));
            view.saveButton.setText(lang.getWord("save"));
            view.printButton.setText(lang.getWord("print"));

            view.printMenu.setText(lang.getWord("print"));
            view.detailMenu.setText(lang.getWord("detail"));
            view.downloadMenu.setText(lang.getWord("download"));

            ((TableColumn) view.tableView.getColumns().get(0)).setText(lang.getWord("id"));//id
            ((TableColumn) view.tableView.getColumns().get(1)).setText(lang.getWord("item"));//item

            ((TableColumn) view.tableView.getColumns().get(2)).setText(lang.getWord("bank"));//driver
            ((TableColumn) view.tableView.getColumns().get(3)).setText(lang.getWord("number"));//num
            ((TableColumn) view.tableView.getColumns().get(4)).setText(lang.getWord("date"));//date

            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar"))
                view.root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            else
                view.root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        }
        private void initiate() throws SQLException {
            getTableColum();

            view.attuchemnt.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(Paths.ATTUCH.getPath()))));
            view.num.setOnKeyReleased(onNumTextPressed());
            view.Vitem.setOnAction(onItem_V_Action());
            view.Vstore.setOnAction(onStore_V_Action());
            view.Vbank.setOnAction(onBank_V_Action());
            view.tableView.setOnKeyPressed(onTablePressed());
            view.tableView.setOnMouseClicked(ontableClick());
            view.saveButton.setOnAction(OnSaveButton());
            view.printButton.setOnAction(onPrintButton());
            view.printMenu.setOnAction(onPrintMenu());
            view.detailMenu.setOnAction(onDetailMenu());
            view.downloadMenu.setOnAction(onDownloadMenu());
            view.attuchemnt.setOnAction(onAttu());



        }

        private void getTableColum() throws SQLException {


            TableColumn<Integer, Transaction> id = new TableColumn<>();

            TableColumn<String, Transaction> item = new TableColumn<>();

//            TableColumn<String, Transaction> store = new TableColumn<>();

            TableColumn<String, Transaction> bank = new TableColumn<>();

            TableColumn<String, Transaction> num = new TableColumn<>();

            TableColumn<String, Transaction> date = new TableColumn<>();


            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            item.setCellValueFactory(new PropertyValueFactory<>("item"));
//            store.setCellValueFactory(new PropertyValueFactory<>("store"));
            num.setCellValueFactory(new PropertyValueFactory<>("number"));
            bank.setCellValueFactory(new PropertyValueFactory<>("bank"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));


            id.setMaxWidth(50);
            id.setMinWidth(25);

            view.tableView.getColumns().add(id);
            view.tableView.getColumns().add(item);
//            view.tableView.getColumns().add(store);
            view.tableView.getColumns().add(bank);
            view.tableView.getColumns().add(num);
            view.tableView.getColumns().add(date);

            getTableDetail();

        }

        private void getTableDetail() throws SQLException {

            Transaction model = new Transaction();

            view.tableView.setItems(model.getInfoTransactionss());

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

//                        size = amount.getInfoItemInSpecStore().size();
                        size = item.getInfo().size();


                        while (i < size)
//                        dialog.listView.getItems().add(item.getInfo().get(i++).getName());
                            dialog.listView.getItems().add(item.getInfo().get(i++).getName());


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

        private EventHandler<MouseEvent> ontableClick () {
            return new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if (event.isSecondaryButtonDown())
                        view.tableMenu.show(view.tableView,event.getScreenX(),event.getScreenY());

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

                        Item model = new Item();

                        itemID = model.getInfoID().get(index).getId() ;
                        view.item.setText(model.getInfo().get(index).getName());

                        getTableDetail();
                        break;
                    case "store" :
                        storeID = store.getInfo().get(index).getId() ;
                        view.store.setText(store.getInfo().get(index).getName());

                        view.item.clear();


                        break;
                    case "banks" :
                        bankID = bank.getInfoIDs().get(index).getId() ;
                        view.bank.setText(bank.getInfoIDs().get(index).getName());
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        private EventHandler<KeyEvent> onTablePressed () {
            return new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {


                    if (!(event.getCode() == KeyCode.DELETE))
                        return;

                    int index = view.tableView.getSelectionModel().getSelectedIndex();
                    Transaction model = new Transaction();
                    try {
                        model.setId(new Transaction().getInfoTransactionssID().get(index).getId());
                        model.delete();
                        getTableDetail();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
                    model.setItemID(itemID);
                    model.setStoreID(storeID);
                    model.setBankID(bankID);
                    model.setDate(view.date.getValue().toString());
                    model.setImg(bos);

                    int num = Integer.parseInt(view.num.getText());
                    model.setNumber(num);

                    try {


                            model.saves();
                            model.update();

                            view.num.clear();
                            view.item.clear();
                            view.bank.clear();

                            getTableDetail();

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
//                    JasperViewer.viewReport(re.getDistrubumentReport(),false);
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

        private EventHandler<ActionEvent> onPrintMenu() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    report re = new report();


                    Transaction transaction = new Transaction();
                    try {
                        System.out.println(transaction.getInfoTransactionsID().get(view.tableView.getSelectionModel().getSelectedIndex()).getNumber());
                        System.out.println(transaction.getInfoTransactionsID().get(view.tableView.getSelectionModel().getSelectedIndex()).getItem());
                        System.out.println(transaction.getInfoTransactionsID().get(view.tableView.getSelectionModel().getSelectedIndex()).getStore());
                        System.out.println(transaction.getInfoTransactionsID().get(view.tableView.getSelectionModel().getSelectedIndex()).getBank());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        transaction = transaction.getInfoTransactions().get(view.tableView.getSelectionModel().getSelectedIndex());
//                    JasperViewer.viewReport(re.getDistrubumentReport(),false);
                        JasperViewer.viewReport(re.getCoffee(transaction),false);
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

                        System.out.println("Error . . . .");
                        System.out.println(index);
                        System.out.println(model.getInfoTransactionssID().get(index).getId());
                        System.out.println(model.getId());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    FileChooser file = new FileChooser();
                    file.setInitialFileName("doc.png");
//                    file.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("*.png"));

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