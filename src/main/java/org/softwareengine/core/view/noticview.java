package org.softwareengine.core.view;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.softwareengine.core.model.Paths;


public class noticview {

        public static StackPane pane ;
        public VBox root;
        public BorderPane Root ;

        public TextField store ;
        public TextField item  ;
        public TextField bank;
        public DatePicker date ;

//        public Spinner<Integer> num ;
        public TextField num ;
        public TextField refID ;

        public Button Vstore ;
        public Button Vitem  ;
        public Button Vbank;

        public Button saveButton ;
        public JFXButton attuchemnt ;
        public Button printButton ;

        public TableView tableView ;

        public Text storeTex  ;
        public Text itemTex   ;
        public Text bankTex   ;
        public Text numberTex ;
        public Text dateTex   ;


        public ContextMenu tableMenu ;

        public MenuItem printMenu    ;
        public MenuItem downloadMenu ;
        public MenuItem detailMenu   ;
        public MenuItem updateMenu   ;
        public MenuItem deleteMenu   ;

        public ImageView imageView ;

        public noticview() {

            root = new VBox();

            store   = new TextField();
            item    = new TextField();
            bank = new TextField();


            storeTex  = new Text();
            itemTex   = new Text();
            bankTex = new Text();
            numberTex = new Text();
            dateTex   = new Text();

            num = new TextField();
            refID = new TextField();

            num.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

            date = new DatePicker();

            imageView = new ImageView();



            saveButton  = new Button();
            attuchemnt  = new JFXButton();
            printButton = new Button();


            updateMenu   = new MenuItem();
            deleteMenu   = new MenuItem();
            printMenu    = new MenuItem();
            detailMenu   = new MenuItem();
            downloadMenu = new MenuItem();



            tableMenu = new ContextMenu();

            tableMenu.getItems().add(updateMenu);
            tableMenu.getItems().add(deleteMenu);
            tableMenu.getItems().add(new SeparatorMenuItem());
            tableMenu.getItems().add(printMenu);
            tableMenu.getItems().add(detailMenu);
            tableMenu.getItems().add(downloadMenu);

            tableMenu.setPrefHeight(60);
            tableMenu.setPrefWidth(60);

            Vstore = new Button("V");
            Vitem  = new Button("V");
            Vbank = new Button("V");

            root.setPrefWidth(200);

            tableView = new TableView();
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            tableView.setPrefHeight(800);

            tableView.setContextMenu(tableMenu);


            store.setDisable(true);
            item.setDisable (true);
            bank.setDisable(true);


            HBox top = new HBox( );


            top.getChildren().add(itemTex) ;
            top.getChildren().add(item);
            top.getChildren().add(Vitem);
            top.getChildren().add(bankTex) ;
            top.getChildren().add(bank);
            top.getChildren().add(Vbank);
            top.getChildren().add(numberTex);
            top.getChildren().add(num);
            top.getChildren().add(dateTex);
            top.getChildren().add(date);
//            top.getChildren().add(attuchemnt);
            top.getChildren().add(refID);
            top.getChildren().add(saveButton);
            top.getChildren().add(printButton);

            top.setSpacing(6.5);

            root.setPadding(new Insets(20));
            root.setSpacing(5);

            Separator line = new Separator();
            line.setOrientation(Orientation.HORIZONTAL);


            Root = new BorderPane();
            Root.setRight(new StackPane(imageView));
            Root.setCenter(tableView);

            root.getChildren().add(Root);
            root.getChildren().add(line);
            root.getChildren().add(top) ;




        }
        public StackPane getRoot() {
            pane = new StackPane();
            pane.getChildren().add(root);
//            pane.getChildren().add(Root);
            return pane ;
        }
    }