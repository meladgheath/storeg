package org.softwareengine.utils.ui;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.softwareengine.core.model.banks;

public class FXDialogTest {


        public JFXDialog dialog ;
        public ListView<String> listView ;
        public ListView<banks> banksListView ;
        public TextField textField ;
        public int valueID ;


        public FXDialogTest(StackPane pane , String Heading , boolean withTextField, boolean here) {

            textField = new TextField();
            textField.setPromptText("Search . . .");

            listView = new ListView<>() ;
            banksListView = new ListView<>();

            VBox body = new VBox();

            body.setAlignment(Pos.CENTER);
            body.setSpacing(6.5);

            if (withTextField)
                body.getChildren().add(textField);

            if (here)
                body.getChildren().add(banksListView);
            else
                body.getChildren().add(listView);

            JFXDialogLayout layout = new JFXDialogLayout() ;

            dialog = new JFXDialog(pane , layout, JFXDialog.DialogTransition.CENTER,true) ;

            layout.setHeading(new Text(Heading));

            layout.setBody(body);

        }
        public void show () {
            dialog.show();
        }

    }


