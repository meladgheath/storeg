package org.softwareengine.core.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SettingView {

    public static StackPane pane ;
    public VBox root;

    public Button backUp ;
    public Button importDB ;


    public ComboBox languages ;

    public Text   languagesTex ;

    public SettingView() {

//        backUp = new Button("BackUp") ;
        backUp = new Button();
//        importDB = new Button("Import Data");
        importDB = new Button();



        languages = new ComboBox();
        languagesTex = new Text();


        root = new VBox();

        HBox languagesRow = new HBox();

        root.getChildren().add(backUp);
        root.getChildren().add(importDB);

        //////////////////////////// languages row
        languagesRow.getChildren().add(languagesTex);
        languagesRow.getChildren().add(languages);

//        languagesRow.setPadding(new Insets(5));
        languagesRow.setSpacing(5);
        languagesRow.setAlignment(Pos.BASELINE_CENTER);

        root.getChildren().add(languagesRow) ;

        //////////////////////////////////////////////

        root.setAlignment(Pos.BASELINE_CENTER);
        root.setSpacing(12);
        root.setPadding(new Insets(25));

    }

    public StackPane getRoot() {
        pane = new StackPane();
        pane.getChildren().add(root);
        return pane ;
    }
}
