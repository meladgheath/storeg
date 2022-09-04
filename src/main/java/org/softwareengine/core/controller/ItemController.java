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

//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.softwareengine.core.model.Item;
import org.softwareengine.config.languages;

import org.softwareengine.core.model.Transaction;
import org.softwareengine.core.model.type;
import org.softwareengine.core.view.ItemView;

import org.softwareengine.utils.ui.FXDialog;
import org.softwareengine.utils.ui.UpdateDialog;
import org.softwareengine.utils.ui.report;
import org.softwareengine.utils.ui.updateItem;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Locale;


public class ItemController {

        public ItemView view;



    public FXDialog dialog ;

        int itemID = 0 ;
        int typeId = 0 ;


        public ItemController() {
            view = new ItemView();


            try {
                initiate();
                setupLanguages();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        private void setupLanguages() {
            languages lang = new languages();

            view.nameTex.setText(lang.getWord("name"));
            view.codeTex.setText(lang.getWord("code"));
            view.typeTex.setText(lang.getWord("types"));
            view.update.setText(lang.getWord("update"));

            view.Vtype.setOnAction(onType_V_Action());
            view.saveButton.setText(lang.getWord("save"));
            view.printButton.setText(lang.getWord("print"));


            ((TableColumn) view.tableView.getColumns().get(0)).setText(lang.getWord("id"));//id
            ((TableColumn) view.tableView.getColumns().get(1)).setText(lang.getWord("name"));//name
            ((TableColumn) view.tableView.getColumns().get(2)).setText(lang.getWord("code"));//code
            ((TableColumn) view.tableView.getColumns().get(3)).setText(lang.getWord("types"));//type


            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar"))
                view.root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            else
                view.root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        }

        private void initiate() throws SQLException {
            getTableColum();

            view.tableView.setOnKeyPressed(onTablePressed());
            view.tableView.setOnMouseClicked(ontableClick());
            view.printButton.setOnAction(onPrintButton());
            view.saveButton.setOnAction(OnSaveButton());
            view.update.setOnAction(onUpdateMenu());


        }

        private void getTableColum() throws SQLException {




            TableColumn<Integer, ItemController> id = new TableColumn<>();
            TableColumn<String, Item> name = new TableColumn<>();
            TableColumn<String, Item> code = new TableColumn<>();
            TableColumn<String, Item> type = new TableColumn<>();




            id.setCellValueFactory(new PropertyValueFactory<>("id"));

            name    .setCellValueFactory(new PropertyValueFactory<>("name"));
            code    .setCellValueFactory(new PropertyValueFactory<>("code"));

            type    .setCellValueFactory(new PropertyValueFactory<>("typeName"));


            id.setMaxWidth(50);
            id.setMinWidth(25);

            view.tableView.getColumns().add(id);
            view.tableView.getColumns().add(name);
            view.tableView.getColumns().add(code);
            view.tableView.getColumns().add(type);



            getTableDetail();

        }

        private void getTableDetail() throws SQLException {
            Item model = new Item();

            view.tableView.setItems(model.getInfo());

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

        private EventHandler<KeyEvent> onTablePressed () {
            return new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    if (event.getCode() != KeyCode.DELETE)
                        return;
                    Item model = new Item();
                    int index = view.tableView.getSelectionModel().getSelectedIndex() ;
                    try {
                        model.setId(new Item().getInfoIDs().get(index).getId());
                        model.delete();
                        getTableDetail();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }} ;
        }

        updateItem updateDialog ;
    private EventHandler<ActionEvent> onUpdateMenu() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                String update = {}
//                UpdateDialog dialog = new UpdateDialog();
//                dialog.show();

//                updateDialog = new UpdateDialog(view.pane,"update :") ;
                updateDialog = new updateItem(view.pane,"update") ;


//                updateDialog.Vitem.setOnAction(onItem_V_Action("itemUpdate"));
//                updateDialog.Vbank.setOnAction(onBank_V_Action("bankUpdate"));

                updateDialog.update.setOnAction(onUpdateAction());

                updateDialog.show();


            }
        };
    }

    private EventHandler<ActionEvent> onUpdateAction(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                Transaction model = new Transaction();
                Item model = new Item();
                int index = view.tableView.getSelectionModel().getSelectedIndex();


                try {
                    int id = model.getInfoID().get(index).getId();
                    model.setId(id);
                    model.setName(updateDialog.item.getText());

                    model.update();

                    updateDialog.dialog.close();
                    getTableDetail();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private EventHandler<KeyEvent> onUpdate() {
            return new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    if (!(event.getCode()== KeyCode.ENTER))
                        return;

                    TextField t = (TextField) event.getSource() ;
                    System.out.println(t.getId());

                    Item model = new Item();
                    System.out.println(itemID);
                    model.setId(itemID);

                    switch (t.getId()) {
                        case "name" :
                            model.setName(t.getText());

                            break;
                        case "code" :
                            model.setCode(Integer.parseInt(t.getText()));
                            break;
                        case "package" :
                            model.setPackages(Integer.parseInt(t.getText()));
                            break;
                        case "value" :
                            model.setValue(Double.parseDouble(t.getText()));
                            break;

                    }


                    try {
//                        model.update(t.getId());
                        dialog.dialog.close();
                        getTableDetail();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            };

        }

        private EventHandler<ActionEvent> onPrintButton() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                   /* JasperReport report = null ;
                    JasperPrint jp = null ;

                    try {
                         report = JasperCompileManager.compileReport("Cherry.jrxml");
                         jp = JasperFillManager.fillReport(report,null,DatabaseService.connection);

                    } catch (JRException e) {
                        e.printStackTrace();
                    }*/
                    report re = new report();

                    try {
                        JasperViewer.viewReport(re.getItemReport(),false);
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

        private EventHandler<ActionEvent> OnSaveButton() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Item model = new Item();




                    model.setCode(Integer.parseInt(view.code.getText()));
                    model.setName(view.name.getText());
                    model.setType(typeId);

                    try {
                        model.save();
                        System.out.println("data is saved !");
                        getTableDetail();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    view.name.clear();
                    view.code.clear();
                    view.type.clear();


                }
            };
        }// save Button



    private EventHandler<ActionEvent> onType_V_Action () {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                dialog = new FXDialog(view.pane, "types List . . . ",false);


                type model  = new type();


                int i = 0;
                int size = 0;
                try {
                    size = model.getInfo().size();
                    System.out.println("the size is " + size);

                    while (i < size)
                        dialog.listView.getItems().add(model.getInfo().get(i++).getName());



                    dialog.show();
                    dialog.listView.setOnKeyPressed(OnListPressed());
                    dialog.listView.setOnMouseClicked(OnMouseClick());

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };


    }


    private EventHandler<KeyEvent> OnListPressed(){
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (!(event.getCode() == KeyCode.ENTER))
                    return;
                ListEvent();



                dialog.dialog.close();

            }
        } ;
    }


    private EventHandler<MouseEvent> OnMouseClick () {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() < 2 )
                    return;
                ListEvent();
                dialog.dialog.close();
            }
        } ;
    }

    private void ListEvent() {


        int index;


        index = dialog.listView.getSelectionModel().getSelectedIndex();
        System.out.println(index +"this is the index . . . . ");

        type types = new type();

            try {
                typeId   = types.getInfo().get(index).getId() ;

                view.type.setText(types.getInfo().get(index).getName());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
}