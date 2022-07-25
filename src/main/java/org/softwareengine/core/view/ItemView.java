package org.softwareengine.core.view;



import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

//        public TextField packages ;



        public Button saveButton ;


        public TableView tableView ;

        public Text nameTex ;
        public Text codeTex ;


        public ItemView() {

            root     = new VBox();

            code     = new TextField();

            name     = new TextField();

            nameTex = new Text();
            codeTex = new Text();

//            packages = new TextField();

            // saveButton  = new Button("save")  ;
            saveButton = new Button();



            Separator line = new Separator();
            line.setOrientation(Orientation.VERTICAL);



            root.setPrefWidth(200);

            tableView = new TableView();
            tableView.setPrefHeight(700);
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

//            GridPane right = new GridPane();
            HBox down = new HBox();


//            right.add(new Text("code :  "),0,0);
//            right.add(code,1,0);


            down.getChildren().add(nameTex) ;
            down.getChildren().add(name);

            down.getChildren().add(codeTex);
            down.getChildren().add(code);

//            right.add(new Text("pakcage  : "),0,2);
//            right.add(packages,1,2);


//            right.add(saveButton,1,3);
            down.getChildren().add(saveButton);

//            saveButton.setMinWidth(root.getPrefWidth());

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


