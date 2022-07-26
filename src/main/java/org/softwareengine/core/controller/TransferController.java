package org.softwareengine.core.controller;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.softwareengine.config.languages;
import org.softwareengine.core.model.type;
import org.softwareengine.core.model.Item;
import org.softwareengine.core.model.Store;
import org.softwareengine.core.model.Transfer;
import org.softwareengine.utils.ui.FXDialog;
import org.controlsfx.control.Notifications;
import org.softwareengine.core.view.TransferView;

import java.sql.SQLException;
import java.util.Locale;

public class TransferController {

    public TransferView view;
    FXDialog dialog ;


    int storeFrom  = 0 ;
    int storeTo    = 0 ;

    public int storeTO = 0 ;

    int itemID = 0 ;

    String fromName ;
    String toName   ;



    public TransferController() {
        view = new TransferView();


        try {
            initiate();
            setupLanguages();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void setupLanguages() {
        languages lang = new languages();

        view.toTex  .setText(lang.getWord("to"));
        view.fromTex.setText(lang.getWord("from"));

        ((TableColumn)view.tableView_from.getColumns().get(0)).setText(lang.getWord("id"));//id
        ((TableColumn)view.tableView_from.getColumns().get(1)).setText(lang.getWord("item"));//item
        ((TableColumn)view.tableView_from.getColumns().get(2)).setText(lang.getWord("box"));//box
        ((TableColumn)view.tableView_from.getColumns().get(3)).setText(lang.getWord("amounts"));//amount


        ((TableColumn)view.tableview_to.getColumns().get(0)).setText(lang.getWord("id"));
        ((TableColumn)view.tableview_to.getColumns().get(1)).setText(lang.getWord("item"));
        ((TableColumn)view.tableview_to.getColumns().get(2)).setText(lang.getWord("box"));
        ((TableColumn)view.tableview_to.getColumns().get(3)).setText(lang.getWord("amounts"));

        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar"))
            view.root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        else
            view.root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

    }

    private void initiate() throws SQLException {
        getTableColumFrom();
        getTableColumTo();
        view.fromSearch.setOnAction(onStoreSearch(1));
        view.toSearch  .setOnAction(onStoreSearch(2));

        view.fromTo.setOnAction(fromTo());
        view.toFrom.setOnAction(toFrom());

        view.fromToAll.setOnAction(fromToAll());
        view.toFromAll.setOnAction(toFromAll());

//        view.saveButton.setOnAction(OnSaveButton());

    }

    private EventHandler<ActionEvent> onStoreSearch (int choic){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                dialog = new FXDialog(view.pane, "store list . . . ",false);


                Store store = new Store();


                int i = 0;
                int size = 0;
                try {
                    size = store.getInfo().size();
                    System.out.println("the size is " + size);

                    while (i < size)
                        dialog.listView.getItems().add(store.getInfo().get(i++).getName());



                    dialog.show();
                    dialog.listView.setOnKeyPressed(OnListPressed(choic));

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }};
    }


    private EventHandler<ActionEvent> onDriveSearch (){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                dialog = new FXDialog(view.pane, "Store list . . . ",false);


                Store store = new Store();


                int i = 0;
                int size = 0;
                try {

                    size = store.getInfo().size();

                    System.out.println("the size is " + size);

                    while (i < size)
                        dialog.listView.getItems().add(store.getInfo().get(i++).getName());



                    dialog.show();
                    dialog.listView.setOnKeyPressed(OnListPressed(1));

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }};
    }

    private EventHandler<ActionEvent> fromTo() {

        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int index = view.tableView_from.getSelectionModel().getSelectedIndex() ;

                Transfer from = new Transfer('S') ;
                Transfer to   = new Transfer('D') ;



                from.setStoreID(storeFrom);
                to  .setStoreID(storeTO);






                try {

//                    int amount = (view.amount.getText().length() != 0 ) ? Integer.parseInt(view.amount.getText()) : from.getInfo().get(index).getAmount();

//                    System.out.println(amount+"  this is the amount mother fucker . . . . ");


                    System.out.println(index+ "   what is our index man . . . ");

                    itemID = from.getInfo().get(index).getItemID();

                    //itemID = from.getInfoWHEREitemid().getItemID() ;




                    from.setItemID(itemID);
                    to.setItemID(itemID);

                    int num = Integer.parseInt(view.amount.getText()) ;

                    from.setNum(num);
                    to  .setNum(num);

//                    from.setAmount(amount);
//                    to.setAmount(amount);

                    from.drop();
                    to.add();

                    getTableDetailFrom();
                    getTableDetailTo  ();

                    view.amount.clear();

                    from.update();
                    to.update();

                }catch (IndexOutOfBoundsException e){
                    Notifications.create().title("Error").text("point and click on the item first . . . ").hideAfter(Duration.seconds(5)).darkStyle().showError();
                }
                catch (NumberFormatException e) {
                    Notifications.create().title("Error").text("you must enter/input the amount of item . . . ").hideAfter(Duration.seconds(5)).darkStyle().showError();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    private EventHandler<ActionEvent> fromToAll() {

        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int index = view.tableView_from.getSelectionModel().getSelectedIndex() ;

                Transfer from = new Transfer('S') ;

                Transfer to   = new Transfer('D') ;



                from.setStoreID(storeFrom);
                to  .setStoreID(storeTO);



                try {

                    System.out.println(index+" here is the index man . . . ");
                    itemID = from.getInfo().get(index).getItemID();

                    from.setItemID(itemID);
                    to.setItemID(itemID);

                    int num = from.getThis().getNum();
                    System.out.println(from.getThis().getNum());

                    from.setNum(num );
                    to.setNum(num);


                    from.drop();
                    to   .add();

                    getTableDetailFrom();
                    getTableDetailTo  ();

                    view.amount.clear();

                    to.update();
                    from.update();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };
    }
    //todo
    private EventHandler<ActionEvent> toFrom() {

        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int index = view.tableview_to.getSelectionModel().getSelectedIndex() ;

                Transfer from = new Transfer('S') ;
                Transfer to   = new Transfer('D') ;


                from.setStoreID(storeFrom);
                to  .setStoreID(storeTO);



                try {

//                    int amount = (view.amount.getText().length() != 0 ) ? Integer.parseInt(view.amount.getText()) : to.getInfo().get(index).getAmount();
                    itemID = to.getInfo().get(index).getItemID();


                    from.setItemID(itemID);
                    to.setItemID(itemID);



//                    from.setAmount(amount);
//                    to.setAmount(amount);

                    int num = Integer.parseInt(view.amount.getText());

                  from.setNum(num);
                  to  .setNum(num);


                    to  .drop();
                    from.add();

                    getTableDetailFrom();
                    getTableDetailTo  ();

                    view.amount.clear();

                    to.update();
                    from.update();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    private EventHandler<ActionEvent> toFromAll() {

        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int index = view.tableview_to.getSelectionModel().getSelectedIndex() ;

                Transfer from = new Transfer('S') ;
                Transfer to   = new Transfer('D') ;


                from.setStoreID(storeFrom);
                to  .setStoreID(storeTO);



                try {
                    itemID = to.getInfo().get(index).getItemID();

                    from.setItemID(itemID);
                    to.setItemID(itemID);

                    int num = to.getThis().getNum();



                    from.setNum(num);
                    to.setNum(num);

                    to.drop();
                    from.add();

                    getTableDetailFrom();
                    getTableDetailTo  ();

                    view.amount.clear();
                    to.update();
                    from.update();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    private EventHandler<KeyEvent> OnListPressed(int where ) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(event.getCode() == KeyCode.ENTER))
                    return;

                int index;


                index = dialog.listView.getSelectionModel().getSelectedIndex();



                Store store   = new Store();
                type driver = new type();


                System.out.println(dialog.listView.getSelectionModel().getSelectedIndex());

                try {
                    System.out.println("<<<< " + store.getInfo().get(index).getName() + " >>>>");
                    System.out.println("<><><>" + store.getInfoID().get(index).getId() + "<><><>");

                    if (where == 1 ) {
                        if (storeFrom != store.getInfoID().get(index).getId())
                            storeFrom = store.getInfoID().get(index).getId() ;
                        fromName = store.getInfo().get(index).getName();
                        view.from.setText(fromName);


                        getTableDetailFrom();
                    }else if (where ==2 )
                    {

                        if (storeTO != store.getInfoID().get(index).getId())
                            storeTO = store.getInfoID().get(index).getId() ;
                        toName = store.getInfo().get(index).getName();
                        view.to.setText(toName);


                        /*if (storeTo != store.getInfoID().get(index).getId())
                            storeTo = store.getInfoID().get(index).getId();

                        toName = store.getInfo().get(index).getName();
                        view.to.setText(toName);
                        storeTO = store.getInfo().get(index).getId() ;
                        view.to.setText(driver.getInfo().get(index).getName());*/

                        getTableDetailTo();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }


                dialog.dialog.close();



            }
        };
    }
    private void getTableColumFrom() throws SQLException {


        TableColumn<Integer, Transfer> id = new TableColumn<>("#");
        TableColumn<String, Transfer> item = new TableColumn<>("item");
        TableColumn<String, Transfer> amount = new TableColumn<>("amount");
        TableColumn<String, Transfer> Box = new TableColumn<>("Box");
//        TableColumn<String, model.transfer> packges = new TableColumn<>("totel");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        item.setCellValueFactory(new PropertyValueFactory<>("item"));
        amount.setCellValueFactory(new PropertyValueFactory<>("num"));
        Box.setCellValueFactory(new PropertyValueFactory<>("box"));
//        packges.setCellValueFactory(new PropertyValueFactory<>("packages"));


        id.setMaxWidth(50);
        id.setMinWidth(25);


        view.tableView_from.getColumns().add(id);
        view.tableView_from.getColumns().add(item);
        view.tableView_from.getColumns().add(Box);
        view.tableView_from.getColumns().add(amount);
//        view.tableView_from.getColumns().add(packges);

    }
    private void getTableColumTo() {
        TableColumn<Integer, Transfer> id = new TableColumn<>("#");
        TableColumn<String, Transfer> item = new TableColumn<>("item");
        TableColumn<String, Transfer> amount = new TableColumn<>("amount");
        TableColumn<String, Transfer> Box = new TableColumn<>("Box");
//        TableColumn<String, model.transfer> packges = new TableColumn<>("totel");




        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        item.setCellValueFactory(new PropertyValueFactory<>("item"));
        amount.setCellValueFactory(new PropertyValueFactory<>("num"));
        Box.setCellValueFactory(new PropertyValueFactory<>("box"));
//        packges.setCellValueFactory(new PropertyValueFactory<>("packages"));


        id.setMaxWidth(50);
        id.setMinWidth(25);

        view.tableview_to.getColumns().add(id);
        view.tableview_to.getColumns().add(item);
        view.tableview_to.getColumns().add(Box);
        view.tableview_to.getColumns().add(amount);
//        view.tableview_to.getColumns().add(packges);


    }

    private void getTableDetailFrom() throws SQLException {
        Transfer from = new Transfer('S');
//        from.setStoreID(storeFrom);

        from.setStoreID(storeFrom);
        view.tableView_from.setItems(from.getInfo());
//        view.tableView.setItems(model.getInfo());

    }

    private void getTableDetailTo() throws SQLException {

        Transfer to = new Transfer('D');
        to  .setStoreID(storeTO);

        view.tableview_to  .setItems(to.getInfo());
//        view.tableView.setItems(model.getInfo());
    }


    private EventHandler<ActionEvent> OnSaveButton() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Item model = new Item();
            }
        };
    }// save Button


}

