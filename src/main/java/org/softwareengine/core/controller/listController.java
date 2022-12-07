package org.softwareengine.core.controller;

//import com.google.zxing.WriterException;
import com.google.zxing.WriterException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.JasperViewer;
import org.softwareengine.config.languages;
import org.softwareengine.core.model.*;
import org.softwareengine.core.view.listview;
import org.softwareengine.utils.ui.FXDialog;
import org.softwareengine.utils.ui.report;

import java.io.*;
import java.sql.SQLException;
import java.util.Locale;

public class listController {

        public listview view;

        public FXDialog dialog ;

        public int itemID   ;
        public int storeID  ;
        public int bankID;

        String itemName ;

        ByteArrayOutputStream bos ;

        public listController() {
            view = new listview();

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

            view.bankTex.setText(lang.getWord("bank"));
            view.printButton.setText(lang.getWord("print"));

            view.printMenu.setText(lang.getWord("print"));
            view.detailMenu.setText(lang.getWord("detail"));
            view.downloadMenu.setText(lang.getWord("download"));
            view.updateMenu.setText(lang.getWord("update"));

            view.dateToTex.setText(lang.getWord("to"));
            view.dateFromTex.setText(lang.getWord("from"));

            view.reportChanger.getItems().add(lang.getWord("reportBank"));
            view.reportChanger.getItems().add(lang.getWord("reportItem"));
            view.reportChanger.getItems().add(lang.getWord("reportGroupby"));

//            reportChanger.getItems().add("report with BrnID");
//            reportChanger.getItems().add("report with itemID");

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

            view.Vitem.setOnAction(onItem_V_Action("item"));
            view.Vbank.setOnAction(onBank_V_Action("banks"));
            view.tableView.setOnMouseClicked(ontableClick());

            view.dateFrom.setOnAction(onDates());
            view.dateTo.setOnAction(onDates());
            view.printButton.setOnAction(onPrintButton());


            view.reportChanger.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int ch = view.reportChanger.getSelectionModel().getSelectedIndex() ;

                    switch (ch) {

                        case 0 : // with bank id

                            view.getTop("brn");
                        break;

                        case 1:// with item id

                            view.getTop("item");
                            break;
                        case 2 : // with group by
                            view.getTop("group");
                            try {
                                getTableDetail("orderby");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                    }
                }
            });


        }

        private EventHandler<ActionEvent> onDates () {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Transaction model = new Transaction();

                    try {

                        if (event.getSource().equals(view.dateTo))
                            if (view.dateFrom.getEditor().getText().length()==0)
                            {
                                model.setDate(view.dateTo.getValue().toString());
                                model.setItemID(itemID);
                                view.tableView.setItems(model.getInfoWHEREitemIDWithLastDate());
                                return;
                            }
                        if (event.getSource().equals(view.dateFrom))
                            if (view.dateTo.getEditor().getText().length()==0){
                                model.setDate_from(view.dateFrom.getValue().toString());
                                model.setItemID(itemID);
                                view.tableView.setItems(model.getInfoWHEREitemIDWithDate());
                                return;
                            }


                        if (event.getSource().equals(view.dateFrom) || event.getSource().equals(view.dateTo))
                        if (view.dateFrom.getEditor().getText().length()>0 && view.dateTo.getEditor().getText().length()>0)
                        {
                            model.setDate_to(view.dateTo.getValue().toString());
                            model.setDate_from(view.dateFrom.getValue().toString());
                            model.setItemID(itemID);
                            view.tableView.setItems(model.getInfoWHEREitemIDWithTwoDate());
                            return;
                        }

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        }

        private EventHandler<ActionEvent> onOrderBy() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (view.orderby.isSelected()) {
                        System.out.println("here is selected . . . ");
                        try {
                            getTableDetail("orderby");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            };
        }

        private EventHandler<ActionEvent> onCheck() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (view.checkBox.isSelected()) {
                        System.out.println(view.top.getChildren().remove(view.item));
                        System.out.println(view.top.getChildren().remove(view.itemTex));
                        System.out.println(view.top.getChildren().remove(view.Vitem));

                        view.top.getChildren().add(0, view.bankTex);
                        view.top.getChildren().add(1, view.bank);
                        view.top.getChildren().add(2, view.Vbank);

                        view.item.clear();
                        itemID=0 ;
                    }else
                    {
                        System.out.println(view.top.getChildren().remove(view.bank));
                        System.out.println(view.top.getChildren().remove(view.bankTex));
                        System.out.println(view.top.getChildren().remove(view.Vbank));

                        view.top.getChildren().add(0,view.itemTex);
                        view.top.getChildren().add(1,view.item);
                        view.top.getChildren().add(2,view.Vitem);

                        view.bank.clear();
                        bankID = 0 ;
                    }
                }
            };
        }


        private void getTableColum() throws SQLException {


            TableColumn<Integer, Transaction> id = new TableColumn<>();

            TableColumn<String, Transaction> item = new TableColumn<>();

            TableColumn<String, Transaction> bank = new TableColumn<>();

            TableColumn<String, Transaction> num = new TableColumn<>();

            TableColumn<String, Transaction> date = new TableColumn<>();


            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            item.setCellValueFactory(new PropertyValueFactory<>("item"));
            num.setCellValueFactory(new PropertyValueFactory<>("number"));
            bank.setCellValueFactory(new PropertyValueFactory<>("bank"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));


            id.setMaxWidth(50);
            id.setMinWidth(25);

            view.tableView.getColumns().add(id);
            view.tableView.getColumns().add(item);
            view.tableView.getColumns().add(bank);
            view.tableView.getColumns().add(num);
            view.tableView.getColumns().add(date);



        }

        private void getTableDetail(String name ) throws SQLException {

            Transaction model = new Transaction();

            switch (name) {
                case "item" :

//                    boolean isDate = false ;
//                    if (view.dateFrom.getEditor().getText().length()>0)
//                        model.setDate_from(view.dateFrom.getEditor().getText());
//                    if (view.dateTo.getEditor().getText().length()>0)
//                        model.setDate_to(view.dateTo.getEditor().getText());
//
//                    if (view.dateFrom.getEditor().getText().length()>0 && view.dateTo.getEditor().getText().length()>0)
//                        isDate = true ;

                    model.setItemID(itemID);
//                    if (isDate)
//                        view.tableView.setItems(model.getInfoWHEREitemIDWithDate());
//                    else
                    view.tableView.setItems(model.getInfoWHEREitemID());




                    break ;
                case "bank" :

                    model.setBankID(bankID);
                    view.tableView.setItems(model.getInfoWHEREbankID());
                    break;
                case "orderby" :
                    view.tableView.setItems(model.getInfoTransactionssORDERBY());
                    break;
            }


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
                        dialog.listView.setOnKeyPressed(OnListPressed(name));
                        dialog.listView.setOnMouseClicked(OnMouseClick(name));

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
            banks bank = new banks();


            System.out.println(dialog.listView.getSelectionModel().getSelectedIndex());

            try {


                switch (thing) {
                    case "item" :

                        Item model = new Item();

                        itemID = model.getInfoID().get(index).getId() ;
                        view.item.setText(model.getInfoID().get(index).getName());

                        getTableDetail("item");
                        break;

                    case "banks" :
                        bankID = bank.getInfoIDs().get(index).getId() ;
                        view.bank.setText(bank.getInfoIDs().get(index).getName());
                        getTableDetail("bank");
                        break;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

         private boolean get() {
            Amount amount = new Amount() ;
            return false ;
        }

        private EventHandler<ActionEvent> onPrintButton() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    report re = new report();

                    try {

                        if (view.top.getChildren().size()==1)
                        JasperViewer.viewReport(re.getDistrubumentReport(view.tableView.getItems(),"disbursementReport2.jrxml"),false);
                        else
                        JasperViewer.viewReport(re.getDistrubumentReport(view.tableView.getItems(),"disbursementReport.jrxml"),false);

                    } catch (JRException e) {
                        Alert message = new Alert(Alert.AlertType.ERROR);
                        message.setContentText(e.getMessage());
                        message.show();
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
                        System.out.println(transaction.getInfoTransactionsID().get(view.tableView.getSelectionModel().getSelectedIndex()).getNumber());
                        System.out.println(transaction.getInfoTransactionsID().get(view.tableView.getSelectionModel().getSelectedIndex()).getItem());
                        System.out.println(transaction.getInfoTransactionsID().get(view.tableView.getSelectionModel().getSelectedIndex()).getStore());
                        System.out.println(transaction.getInfoTransactionsID().get(view.tableView.getSelectionModel().getSelectedIndex()).getBank());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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

    }