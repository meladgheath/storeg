package org.softwareengine.core.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.JasperViewer;
import org.softwareengine.config.languages;
import org.softwareengine.core.model.Item;
import org.softwareengine.core.model.banks;
import org.softwareengine.core.view.bankview;
import org.softwareengine.utils.ui.UpdateDialog;
import org.softwareengine.utils.ui.report;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Locale;

public class banksController {

        public bankview view;
        UpdateDialog dialog ;

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
            view.saveButton.setText(lang.getWord("save"));
            view.printButton.setText(lang.getWord("print"));


            ((TableColumn) view.tableView.getColumns().get(0)).setText(lang.getWord("id"));//id
            ((TableColumn) view.tableView.getColumns().get(1)).setText(lang.getWord("name"));//name
            ((TableColumn) view.tableView.getColumns().get(2)).setText(lang.getWord("reference"));//reference number

//            ((TableColumn) view.tableView.getColumns().get(3)).setText(lang.getWord(""));
//            ((TableColumn) view.tableView.getColumns().get(4)).setText(lang.getWord("value"));

            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar"))
                view.root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            else
                view.root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        }

        private void initiate() throws SQLException {
            getTableColum();



            view.tableView.setOnKeyPressed(onTablePressed());
            view.saveButton.setOnAction(OnSaveButton());
            view.printButton.setOnAction(onPrintButton());



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
/*


                    String text [] = {
                            "name","code","package","value"
                    } ;
                    dialog = new UpdateDialog(view.pane ,"update item . . . ",3,text) ;

                    Item model = new Item() ;

                    int index = view.tableView.getSelectionModel().getSelectedIndex() ;

                    String itemName = "" ;
                    String code = "" ;
                    String packages = "" ;
                    String value = "" ;
                    try {
                        itemID   = model.getInfoID().get(index).getId() ;
                        System.out.println("the ID = "+itemID);
                        itemName = model.getInfo().get(index).getName() ;
                        System.out.println("the name = "+itemName);
                        code = model.getInfo().get(index).getCode()+"" ;
                        packages = model.getInfo().get(index).getPackages()+"";
                        value = model.getInfo().get(index).getValue()+"";

                        dialog.tf1.setText(itemName);//t1 for item name
                        dialog.tf2.setText(code); // t2 for code item
                        dialog.tf3.setText(packages); // t3 for package
                        dialog.tf4.setText(value);// t4 for value or price

                        dialog.tf1.setId("name");
                        dialog.tf2.setId("code");
                        dialog.tf3.setId("package");
                        dialog.tf4.setId("value");


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                    dialog.show();

                    dialog.tf1.setOnKeyPressed(onUpdate());
                    dialog.tf2.setOnKeyPressed(onUpdate());
                    dialog.tf3.setOnKeyPressed(onUpdate());
                    dialog.tf4.setOnKeyPressed(onUpdate());
*/
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
                        model.update(t.getId());
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
                    JasperViewer.viewReport(re.getBankBranchs(),false);
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