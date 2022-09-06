package org.softwareengine.core.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class loginView {


        public TextField userName ;
        public TextField password ;



        public final Scene scene ;
        public ImageView lock ;

        public loginView() {

            lock     = new ImageView()     ;
            userName = new TextField()     ;
            password = new PasswordField() ;




            VBox root = new VBox();

            root.getChildren().add(lock)    ;
            root.getChildren().add(userName);
            root.getChildren().add(password);

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



