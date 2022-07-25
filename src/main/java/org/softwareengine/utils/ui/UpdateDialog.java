package org.softwareengine.utils.ui;


import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;


import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

    public class UpdateDialog {


        public JFXDialog dialog ;



        public TextField tf1;
        public TextField tf2;
        public TextField tf3;
        public TextField tf4;

        public Text  t1 ;
        public Text  t2 ;
        public Text  t3 ;
        public Text  t4 ;

        public UpdateDialog(StackPane pane , String Heading , int totle, String text[]) {



            VBox body = new VBox();

            body.setAlignment(Pos.CENTER_LEFT);
            body.setSpacing(6.5);




            if (totle >= 1) {
                t1 = new Text(text[0]) ;
                tf1 = new TextField();
                body.getChildren().add(t1) ;
                body.getChildren().add(tf1);
            }
            if (totle >= 2) {
                t2 = new Text(text[1]);
                tf2 = new TextField();
                body.getChildren().add(t2);
                body.getChildren().add(tf2);
            }

            if (totle >= 3) {
                t3 = new Text(text[2]);
                tf3 = new TextField() ;
                body.getChildren().add(t3);
                body.getChildren().add(tf3);
            }
            if (totle >= 3) {
                t4 = new Text(text[3]);
                tf4 = new TextField() ;
                body.getChildren().add(t4);
                body.getChildren().add(tf4);
            }


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
