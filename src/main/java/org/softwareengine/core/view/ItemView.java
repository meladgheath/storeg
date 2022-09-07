package org.softwareengine.core.view;



import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox ;
import javafx.scene.layout.HBox ;
import javafx.scene.layout.BorderPane ;

import javafx.scene.text.Text;

    public class ItemView {

        public static StackPane pane ;
        public VBox root;

        public TextField code;
        public TextField name ;
        public TextField type ;


        public Button Vtype ;

        public Button saveButton ;
        public Button printButton ;


        public TableView tableView ;

        public Text nameTex ;
        public Text codeTex ;
        public Text typeTex ;

        public ContextMenu tableMenu ;
        public MenuItem update ;

        public ItemView() {

            root     = new VBox();
            code     = new TextField();
            name     = new TextField();

            nameTex = new Text();
            codeTex = new Text();
            typeTex = new Text();

            update = new MenuItem();

            tableMenu = new ContextMenu();
            tableMenu.getItems().add(update);

            saveButton  = new Button();
            printButton = new Button();

            type = new TextField();
            type.setDisable(true);
            Vtype = new Button("V");

            Separator line = new Separator();
            line.setOrientation(Orientation.VERTICAL);

            root.setPrefWidth(200);

            tableView = new TableView();
            tableView.setPrefHeight(700);
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            HBox down = new HBox();

            down.getChildren().add(nameTex) ;
            down.getChildren().add(name);
            down.getChildren().add(codeTex);
            down.getChildren().add(code);
            down.getChildren().add(typeTex);
            down.getChildren().add(type) ;
            down.getChildren().add(Vtype);
            down.getChildren().add(saveButton);
            down.getChildren().add(printButton);

            HBox right2 = new HBox();
            right2.getChildren().add(line);

            down.setSpacing(8);
            down.setPadding(new Insets(10,0,0,0));

            tableView.setContextMenu(tableMenu);

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


