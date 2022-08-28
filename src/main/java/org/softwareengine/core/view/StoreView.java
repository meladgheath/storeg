package org.softwareengine.core.view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StoreView {



    public static StackPane pane ;
    public VBox root;


    public TextField name ;

    public Text nameTex ;


    public Button saveButton ;
    public Button printButton ;

    public TableView tableView ;


    public StoreView() {

        root = new VBox();

        name = new TextField();

        nameTex = new Text();

//        saveButton     = new Button("save")  ;

        saveButton  = new Button();
        printButton = new Button();


        root.setPrefWidth(200);

        tableView = new TableView();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        GridPane top = new GridPane();




        top.add(nameTex,0,1);
        top.add(name,1,1);






        top.add(saveButton,1,3);

        saveButton.setMinWidth(root.getPrefWidth());

        top.setHgap(12);
        top.setVgap(12);
        top.setAlignment(Pos.BASELINE_CENTER);




        root.setPadding(new Insets(20));
        root.setSpacing(5);

        Separator line = new Separator();
        line.setOrientation(Orientation.HORIZONTAL);

        root.getChildren().add(top);
        root.getChildren().add(line);

        root.getChildren().add(tableView);


    }
    public StackPane getRoot() {
        pane = new StackPane();
        pane.getChildren().add(root);
        return pane ;
    }
}

