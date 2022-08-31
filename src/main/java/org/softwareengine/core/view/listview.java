package org.softwareengine.core.view;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class listview {

        public static StackPane pane ;
        public VBox root;
        public HBox top ;

        public TextField item  ;
        public TextField bank;

        public CheckBox checkBox;


        public Button Vitem  ;
        public Button Vbank;

        public Button saveButton ;

        public Button printButton ;

        public TableView tableView ;
        public Text itemTex   ;
        public Text bankTex   ;



        public ContextMenu tableMenu ;

        public MenuItem printMenu    ;
        public MenuItem downloadMenu ;
        public MenuItem detailMenu   ;
        public MenuItem updateMenu   ;


        public listview() {

            root = new VBox();


            item    = new TextField();
            bank = new TextField();



            itemTex   = new Text();
            bankTex = new Text();


            printButton = new Button();
            saveButton  = new Button();


            updateMenu   = new MenuItem();
            printMenu    = new MenuItem();
            detailMenu   = new MenuItem();
            downloadMenu = new MenuItem();


            tableMenu = new ContextMenu();

            Vitem  = new Button("V");
            Vbank = new Button("V");
            checkBox = new CheckBox();

            root.setPrefWidth(200);

            tableView = new TableView();
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            tableView.setPrefHeight(800);

            tableView.setContextMenu(tableMenu);

            item.setDisable (true);
            bank.setDisable(true);

             top = new HBox( );

            top.getChildren().add(itemTex) ;
            top.getChildren().add(item);
            top.getChildren().add(Vitem);
            top.getChildren().add(printButton);
            top.getChildren().add(checkBox);
            top.setSpacing(6.5);

            root.setPadding(new Insets(20));
            root.setSpacing(5);

            Separator line = new Separator();
            line.setOrientation(Orientation.HORIZONTAL);

            root.getChildren().add(top) ;
            root.getChildren().add(line);
            root.getChildren().add(tableView);
        }
        public StackPane getRoot() {
            pane = new StackPane();
            pane.getChildren().add(root);
            return pane ;
        }
    }