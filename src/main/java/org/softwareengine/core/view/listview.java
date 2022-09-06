package org.softwareengine.core.view;

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
        public HBox top;
        public HBox  reportWithBrnID;

        public TextField item  ;
        public TextField bank;


        public CheckBox checkBox;
        public CheckBox orderby ;


        public Button Vitem  ;
        public Button Vbank;

        public Button saveButton ;

        public Button printButton ;

        public TableView tableView ;
        public Text itemTex   ;
        public Text bankTex   ;

        public ComboBox<String> reportChanger ;


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


            reportChanger = new ComboBox<>();

            root.setPrefWidth(200);

            tableView = new TableView();
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            tableView.setPrefHeight(800);

            tableView.setContextMenu(tableMenu);

            item.setDisable (true);
            bank.setDisable(true);

            top = new HBox();

            getTop("item");

            root.setPadding(new Insets(20));
            root.setSpacing(5);

            Separator line = new Separator();
            line.setOrientation(Orientation.HORIZONTAL);

            root.getChildren().add(reportChanger);
            root.getChildren().add(top) ;
            root.getChildren().add(line);
            root.getChildren().add(tableView);
        }

        public void getTop(String name) {


         top.getChildren().removeAll(top.getChildren());

            switch (name){
                case "item" :
                    top.getChildren().add(itemTex) ;
                    top.getChildren().add(item);
                    top.getChildren().add(Vitem);
                    top.getChildren().add(printButton);
                    top.setSpacing(6.5);
                    break;
                case "brn" :
                    top.getChildren().add(bankTex) ;
                    top.getChildren().add(bank);
                    top.getChildren().add(Vbank);
                    top.getChildren().add(printButton);
                    top.setSpacing(6.5);
                    break;
                case "group" :
                    top.getChildren().add(printButton);
                    break;


            }
        }

    public void removeall() {
        top.getChildren().removeAll();
    }


        public StackPane getRoot() {
            pane = new StackPane();
            pane.getChildren().add(root);
            return pane ;
        }
    }