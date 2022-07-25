package org.softwareengine.core.view;


import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.text.Text;

public class Driver {


    public static StackPane pane ;
    public VBox root;

    public TextField name;
    public TextField phoneNo ;
    public TextField salary ;
    public DatePicker date  ;

    public Text nameTex ;
    public Text phoneTex ;



    public Button saveButton       ;
//    public Button browsImageButton ;

    public TableView tableView ;


    public Driver() {




        root     = new VBox();

        name    = new TextField();
        phoneNo = new TextField();

        nameTex = new Text();
        phoneTex = new Text();


//        saveButton  = new Button(resourceBundle.getString("save"))  ;
        saveButton = new Button();
//        browsImageButton = new Button("browseImage. . .") ;

        Separator line = new Separator();
        line.setOrientation(Orientation.VERTICAL);

        root.setPrefWidth(200);

        tableView = new TableView();
        tableView.setPrefHeight(700);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        GridPane right = new GridPane();

//        right.add(new Text(resourceBundle.getString("name")+"  :  "),0,0);
        right.add(nameTex,0,0);
        right.add(name,1,0);


//        right.add(new Text(resourceBundle.getString("phone")+" : "),0,1);
        right.add(phoneTex,0,1);
        right.add(phoneNo,1,1);

        /*right.add(new Text("salary  : "),0,2);
        right.add(salary,1,2);

        right.add(new Text("date  : "),0,3);
        right.add(date,1,3);
*/
//        right.add(browsImageButton,1,4);
        right.add(saveButton,1,5);


        saveButton.setMinWidth(root.getPrefWidth());
//        browsImageButton.setMinWidth(root.getPrefWidth()*2);

        HBox right2 = new HBox();
        right2.getChildren().add(line);
        right2.getChildren().add(right);


        right.setHgap(12);
        right.setVgap(12);
        right.setAlignment(Pos.BASELINE_CENTER);
        right.setPadding(new Insets(12));



        BorderPane pane = new BorderPane();
        pane.setRight(right2);
        pane.setCenter(tableView);



        root.setPadding(new Insets(20));
        root.setSpacing(5);


        root.getChildren().add(pane);


    }
    public StackPane getRoot() {
        pane = new StackPane();
        pane.getChildren().add(root);
        return pane ;
    }
}