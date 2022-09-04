package org.softwareengine.utils.ui;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.softwareengine.config.languages;

public class updateItem {

        public JFXDialog dialog ;
        public TextField item;
        public Text itemTx ;
        public Button update ;

        public updateItem(StackPane pane , String Heading ) {

            languages lang = new languages();


            VBox body = new VBox();

            body.setAlignment(Pos.CENTER_LEFT);
            body.setSpacing(6.5);

            itemTx   = new Text("item   : ") ;

            item = new TextField();

            update= new Button(lang.getWord("update"));
            body.getChildren().add(itemTx);
            body.getChildren().add(item);
            body.getChildren().add(update);

            JFXDialogLayout layout = new JFXDialogLayout() ;

            dialog = new JFXDialog(pane , layout, JFXDialog.DialogTransition.CENTER,true) ;

            layout.setHeading(new Text(Heading));

            StackPane root = new StackPane( );
            root.getChildren().add(body);

            layout.setBody(root);

        }
        public void show () {
            dialog.show();
        }

    }


