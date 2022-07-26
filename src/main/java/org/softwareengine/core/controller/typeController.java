package org.softwareengine.core.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import org.softwareengine.core.model.type;
import org.softwareengine.config.languages;
import org.softwareengine.core.view.typeView;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;


public class typeController {


    public typeView view;
    File image ;


    public typeController() {
        view = new typeView();


        try {
            initiate();
            setupLanguages() ;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setupLanguages() {

        languages lang = new languages();

        view.nameTex.setText(lang.getWord("name"));

        view.saveButton.setText(lang.getWord("save"));

        ((TableColumn)view.tableView.getColumns().get(0)).setText(lang.getWord("id"));
        ((TableColumn)view.tableView.getColumns().get(1)).setText(lang.getWord("name"));

        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar"))
            view.root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        else
            view.root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);



    }

    private void initiate() throws SQLException, IOException {
        getTableColum();



        view.saveButton.setOnAction(OnSaveButton());
        view.tableView.setOnKeyPressed(onTableKeypressed());

    }

    private EventHandler<KeyEvent> onTableKeypressed() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                type model = new type();
                int index = view.tableView.getSelectionModel().getSelectedIndex();


//                DialogDetail dialog = null;

                /*try {
                    model.setId(model.getInfoID().get(index).getId());
//                    model = model.getInfoWHEREid();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }*/


            }};
    }


    private void getTableColum() throws SQLException, IOException {


        TableColumn<Integer, type> id = new TableColumn<>("#");
        TableColumn<String , type> name = new TableColumn<>("Name");


//      TableColumn<Double, main.model.driver> salary = new TableColumn<>("Salary") ;
//      TableColumn<Double, main.model.driver> date = new TableColumn<>("Date") ;


        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        name    .setCellValueFactory(new PropertyValueFactory<>("name"));

//        salary  .setCellValueFactory(new PropertyValueFactory<>("salary"));
//        date    .setCellValueFactory(new PropertyValueFactory<>("date"));

        id.setMaxWidth(50);
        id.setMinWidth(25);

        view.tableView.getColumns().add(id);
        view.tableView.getColumns().add(name);

//        view.tableView.getColumns().add(salary);
//        view.tableView.getColumns().add(date);

        getTableDetail();
    }

    private void getTableDetail() throws SQLException, IOException {
        type model = new type();

        view.tableView.setItems(model.getInfo());


    }



    private EventHandler<ActionEvent> OnSaveButton() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                type model = new type();



                model.setName(view.name.getText());

//                model.setSalary(Double.parseDouble(view.salary.getText()));
//                model.setDate(view.date.getEditor().getText());
//                model.setImage(image);


                try {
                    model.save();
                    System.out.println("data is saved !");
                    getTableDetail();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                view.name.clear();

//                view.salary.clear();
//                view.date.getEditor().clear();
//                view.date.setValue(null);
//                model.deleteImage();



            }
        };
    }// save Button


    private EventHandler<ActionEvent> onBrowseImageButton() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fileChooser = new FileChooser();
//                fileChooser.showOpenDialog(null) ;
                image = fileChooser.showOpenDialog(null) ;
                type model = new type();
//                model.setImage(file);

//                view.browsImageButton.setText(image.getAbsolutePath());

            }
        };
    }// browse image  Button

}
