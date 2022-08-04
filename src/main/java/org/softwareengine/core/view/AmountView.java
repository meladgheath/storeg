package org.softwareengine.core.view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


    public class AmountView {



        public static StackPane pane ;
        public VBox root;

        public TextField store;
        public TextField item ;
        public TextField num  ;

        public Text storeTex ;
        public Text itemTex  ;
        public Text numTex   ;


        public Button Vstore ;
        public Button Vitem ;
        public Button saveButton  ;
        public Button printButton ;

        public TableView tableView ;



        public AmountView() {

            root = new VBox();

            store   = new TextField();
            item    = new TextField();
            num     = new TextField();


            storeTex = new Text();
            itemTex  = new Text();
            numTex   = new Text();


            saveButton  = new Button();
            printButton = new Button();

            Vstore = new Button("V");
            Vitem  = new Button("V");

            root.setPrefWidth(200);

            tableView = new TableView();
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            tableView.setPrefHeight(800);

            store.setDisable(true);
            item.setDisable (true);

              HBox top = new HBox( );

            /*top.add(new Text("name  : "),0,1);
            top.add(name,1,1);

            top.add(saveButton,1,3);
            saveButton.setMinWidth(root.getPrefWidth());

            top.setHgap(12);
            top.setVgap(12);
            top.setAlignment(Pos.BASELINE_CENTER);

*/
            top.getChildren().add(storeTex) ;
            top.getChildren().add(store);
            top.getChildren().add(Vstore);
            top.getChildren().add(itemTex) ;
            top.getChildren().add(item);
            top.getChildren().add(Vitem);
            top.getChildren().add(numTex);
            top.getChildren().add(num);
            top.getChildren().add(saveButton);
            top.getChildren().add(printButton);

            top.setSpacing(6.5);

            root.setPadding(new Insets(20));
            root.setSpacing(5);

            Separator line = new Separator();
            line.setOrientation(Orientation.HORIZONTAL);

            root.getChildren().add(tableView);
            root.getChildren().add(line);
            root.getChildren().add(top) ;

        }
        public StackPane getRoot() {
            pane = new StackPane();
            pane.getChildren().add(root);
            return pane ;
        }
    }