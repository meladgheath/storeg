package org.softwareengine.core.controller;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.softwareengine.core.model.Store;
import org.softwareengine.config.languages;
import org.softwareengine.core.view.StoreView;
import java.sql.SQLException;
import java.util.Locale;

public class StoreController {


    public StoreView view;

//    updateDialog dialog ;
    int storeID;

    public StoreController() {
        view = new StoreView();


        try {
            initiate();
            setupLanguage();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void setupLanguage() {
        languages lang = new languages();

        view.saveButton.setText(lang.getWord("save"));
        view.nameTex   .setText(lang.getWord("name"));
        ((TableColumn)view.tableView.getColumns().get(0)).setText(lang.getWord("id"));
        ((TableColumn)view.tableView.getColumns().get(1)).setText(lang.getWord("name")); //name column

        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar"))
            view.root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        else
            view.root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

    }

    private void initiate() throws SQLException {
        getTableColum();

        view.tableView.setOnKeyPressed(onTablePressed());
        view.saveButton.setOnAction(OnSaveButton());


    }

    private void getTableColum() throws SQLException {


        TableColumn<Integer, Store> id = new TableColumn<>("#");

        TableColumn<String, Store> name = new TableColumn<>("Name");




        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));


        id.setMaxWidth(50);
        id.setMinWidth(25);

        view.tableView.getColumns().add(id);
        view.tableView.getColumns().add(name);



        getTableDetail();

    }

    private void getTableDetail() throws SQLException {
        Store model = new Store();

        view.tableView.setItems(model.getInfo());

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

                Store model = new Store() ;

                int index = view.tableView.getSelectionModel().getSelectedIndex() ;


                String name ;
                try {
                    storeID = model.getInfoID().get(index).getId() ;
                    System.out.println("the ID = "+ storeID);
                    name = model.getInfo().get(index).getName() ;
                    System.out.println("the name = "+name);


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
                System.out.println(t.getId());
                System.out.println(t.getText()+" yes that is mother fucker . . . ");


                try {
                    Store model = new Store();
                    System.out.println(storeID);
                    model.setId(storeID);
                    model.setName(t.getText());
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
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Store model = new Store();
                model.setName(view.name.getText());

                try {
                    model.save();
                    System.out.println("data is saved !");
                    getTableDetail();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                view.name.clear();
            }
        };
    }// save Button
}