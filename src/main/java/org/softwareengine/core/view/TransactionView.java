package org.softwareengine.core.view;



import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TransactionView {

    public static StackPane pane ;
    public VBox root;

    public DatePicker date ;
    public CheckBox   checkBox ;

    public Button print ;

    public TableView tableView ;


    public TransactionView() {

        root = new VBox();

        date = new DatePicker();
        checkBox = new CheckBox("all data");
        print    = new Button("Print") ;

        root.setPrefWidth(200);
        //root.setPrefHeight(600);

        tableView = new TableView();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.setPrefHeight(700);


        GridPane top = new GridPane();



        top.add(new Text("name  : "),0,1);
        top.add(date,1,1);

//        top.add(checkBox,2,1);
//        top.add(print,3,1);

        top.setHgap(12);
        top.setVgap(12);
        top.setAlignment(Pos.BASELINE_CENTER);




        root.setPadding(new Insets(20));
        root.setSpacing(5);

        Separator line = new Separator();
        line.setOrientation(Orientation.HORIZONTAL);

        root.getChildren().add(top);
        root.getChildren().add(line);

        root.getChildren().add(tableView);


    }
    public StackPane getRoot() {
        pane = new StackPane();
        pane.getChildren().add(root);
        return pane ;
    }

}
