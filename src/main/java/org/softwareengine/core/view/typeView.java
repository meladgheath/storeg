package org.softwareengine.core.view;


import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

import javafx.scene.text.Text;

public class typeView {

    public static StackPane pane ;
    public VBox root;


    public TextField name ;

    public Button saveButton ;


    public TableView tableView ;

    public Text nameTex ;



    public typeView() {

        root     = new VBox();

        name     = new TextField();
        nameTex = new Text();

        saveButton = new Button();

        Separator line = new Separator();
        line.setOrientation(Orientation.VERTICAL);

        root.setPrefWidth(200);

        tableView = new TableView();
        tableView.setPrefHeight(700);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        HBox down = new HBox();

        down.getChildren().add(nameTex) ;
        down.getChildren().add(name);

        down.getChildren().add(saveButton);

        HBox right2 = new HBox();
        right2.getChildren().add(line);

        down.setSpacing(8);
        down.setPadding(new Insets(10,0,0,0));

        BorderPane pane = new BorderPane();

        pane.setCenter(tableView);
        pane.setBottom(down);

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