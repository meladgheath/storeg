package org.softwareengine.utils.ui;


import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;

import org.softwareengine.config.languages;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

    public class UpdateDialog {

        public JFXDialog dialog ;

        public TextField item;
        public TextField bank;
        public TextField number;
        public DatePicker date ;

        public Text  itemTx ;
        public Text bankTx ;
        public Text numberTx ;
        public Text  DateTx ;

        public Button Vitem  ;
        public Button Vbank  ;

        public Button update ;

        public UpdateDialog(StackPane pane , String Heading ) {

            languages lang = new languages();


            VBox body = new VBox();

            body.setAlignment(Pos.CENTER_LEFT);
            body.setSpacing(6.5);

            itemTx   = new Text("item   : ") ;
            bankTx   = new Text("bank   : ") ;
            numberTx = new Text("number : ") ;
            DateTx   = new Text("Date   : ") ;

            item = new TextField();
            bank = new TextField();
            number = new TextField();
            date = new DatePicker();

            Vitem = new Button("V") ;
            Vbank = new Button("V") ;
            update= new Button(lang.getWord("update"));

            item.setDisable(true);
            bank.setDisable(true);

                body.getChildren().add(itemTx);
                body.getChildren().add(item);
                body.getChildren().add(Vitem);
                body.getChildren().add(bankTx);
                body.getChildren().add(bank);
                body.getChildren().add(Vbank);
                body.getChildren().add(numberTx);
                body.getChildren().add(number);
                body.getChildren().add(DateTx);
                body.getChildren().add(date);
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
