package org.softwareengine.utils.ui;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.io.FileInputStream;
import java.io.InputStream;

public class ImageDialog {

        public JFXDialog dialog ;
//        public ListView<String> listView ;
//        public TextField textField ;
    public ImageView imageView ;
    public Image     image ;



        public ImageDialog(StackPane pane , String Heading, InputStream file) {

//            textField = new TextField();
//            textField.setPromptText("Search . . .");
            image = new Image(file);

            imageView = new ImageView(image);


//            listView = new ListView<>() ;

            VBox body = new VBox();

            body.setAlignment(Pos.CENTER);
            body.setSpacing(6.5);




//            body.getChildren().add(listView);
            body.getChildren().add(imageView);

            JFXDialogLayout layout = new JFXDialogLayout() ;

            dialog = new JFXDialog(pane , layout, JFXDialog.DialogTransition.CENTER,true) ;

            layout.setHeading(new Text(Heading));

            layout.setBody(body);
//            dialog.setMaxWidth(400);
//            dialog.setMaxHeight(400);

        }
        public void show () {
            dialog.show();
        }

    }