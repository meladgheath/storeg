package org.softwareengine.core.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class loginView {


        public TextField userName ;
        public TextField password ;

        public VBox root ;

        public final Scene scene ;
        public ImageView lock ;

        public AnchorPane pane ;
        public Label appVersion ;

        public loginView() {

            lock     = new ImageView()     ;
            userName = new TextField()     ;
            userName.setPromptText("UserName : ");
            password = new PasswordField() ;
            password.setPromptText("Password : ");
            root = new VBox();

             appVersion = new Label() ;

            pane = new AnchorPane(appVersion);

            AnchorPane.setLeftAnchor(appVersion,5.0);
            AnchorPane.setBottomAnchor(appVersion,1.0);

            root.getChildren().add(lock)    ;
            root.getChildren().add(userName);
            root.getChildren().add(pane);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(30));
            root.setSpacing(15);


            lock.setFitWidth(150);
            lock.setFitHeight(170);

            scene = new Scene(root,361,450);

        }

        public Scene getScene() {
            return scene ;
        }
    }