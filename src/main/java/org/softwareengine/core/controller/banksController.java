package org.softwareengine.core.controller;

import com.google.zxing.WriterException;
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
import org.softwareengine.config.languages;
import org.softwareengine.core.model.Item;
import org.softwareengine.core.model.banks;
import org.softwareengine.core.view.bankview;
import org.softwareengine.utils.ui.UpdateDialog;
import org.softwareengine.utils.ui.report;
import org.softwareengine.utils.ui.updateItem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class banksController {

        public bankview view;
        UpdateDialog dialog ;
        updateItem updateDialog;

        int itemID = 0 ;



        public banksController() {
            view = new bankview();


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
            view.referenceTex.setText(lang.getWord("reference"));
            view.update.setText(lang.getWord("update"));
            view.saveButton.setText(lang.getWord("save"));
            view.printButton.setText(lang.getWord("print"));


            ((TableColumn) view.tableView.getColumns().get(0)).setText(lang.getWord("id"));//id
            ((TableColumn) view.tableView.getColumns().get(1)).setText(lang.getWord("name"));//name
            ((TableColumn) view.tableView.getColumns().get(2)).setText(lang.getWord("reference"));//reference number

            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar"))
                view.root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            else
                view.root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        }

        private void initiate() throws SQLException {
            getTableColum();

            view.tableView.setOnKeyPressed(onTablePressed());
            view.tableView.setOnMouseClicked(ontableClick());
            view.saveButton.setOnAction(OnSaveButton());
            view.printButton.setOnAction(onPrintButton());
            view.update.setOnAction(onUpdateMenu());
        }

    private EventHandler<ActionEvent> onUpdateMenu() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                updateDialog = new updateItem(view.pane,"update") ;

                updateDialog.update.setOnAction(onUpdateAction());
                updateDialog.show();


            }
        };
    }

    private EventHandler<ActionEvent> onUpdateAction(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                banks model = new banks();
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

    private EventHandler<MouseEvent> ontableClick () {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {


                if (event.isSecondaryButtonDown())
                    view.tableMenu.show(view.tableView,event.getScreenX(),event.getScreenY());

            }
        } ;
    }

        private void getTableColum() throws SQLException {




            TableColumn<Integer, banks> id = new TableColumn<>();
            TableColumn<String, banks> name = new TableColumn<>();
            TableColumn<String, banks> reference = new TableColumn<>();



            id.setCellValueFactory(new PropertyValueFactory<>("id"));

            name    .setCellValueFactory(new PropertyValueFactory<>("name"));
            reference.setCellValueFactory(new PropertyValueFactory<>("referenceNumber"));

            id.setMaxWidth(50);
            id.setMinWidth(25);

            view.tableView.getColumns().add(id);
            view.tableView.getColumns().add(name);
            view.tableView.getColumns().add(reference);


            getTableDetail();

        }

        private void getTableDetail() throws SQLException {
            banks model = new banks();

            view.tableView.setItems(model.getInfo());

        }


        private EventHandler<KeyEvent> onTablePressed () {
            return new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() != KeyCode.DELETE)
                        return;

                    banks model = new banks();
                    int index = view.tableView.getSelectionModel().getSelectedIndex() ;
                    try {
                        model.setId(model.getInfoID().get(index).getId());
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
                report re = new report();

                try {
                    JasperViewer.viewReport(re.getBankBranchs(view.tableView.getItems()),false);
                } catch (JRException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Done here man . . .");
            }
        };
    }
    private EventHandler<ActionEvent> OnSaveButton() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    banks model = new banks();
                    model.setName(view.name.getText());
                    model.setReferenceNumber(view.referenceNumber.getText());
                    try {
                        model.save();
                        System.out.println("data is saved !");
                        getTableDetail();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    view.name.clear();
                    view.referenceNumber.clear();
               }
            };
        }// save Button
    }