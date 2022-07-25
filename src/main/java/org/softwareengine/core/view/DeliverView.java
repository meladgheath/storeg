package org.softwareengine.core.view;



import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class DeliverView {



    public static StackPane pane ;
    public VBox root;

    public TextField store ;
    public TextField item  ;
    public TextField drive ;
    public DatePicker date ;

    public Spinner<Integer> num ;


    public Button Vstore ;
    public Button Vitem  ;
    public Button VDrive ;

    public Button saveButton ;

    public TableView tableView ;

    public Text storeTex  ;
    public Text itemTex   ;
    public Text driverTex ;
    public Text numberTex ;




    public DeliverView() {

        root = new VBox();

        store   = new TextField();
        item    = new TextField();
        drive   = new TextField();


        storeTex  = new Text();
        itemTex   = new Text();
        driverTex = new Text();
        numberTex = new Text();

        num = new Spinner<>();

        num.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

        date = new DatePicker();


//        saveButton     = new Button("save")  ;
        saveButton = new Button();

        Vstore = new Button("V");
        Vitem  = new Button("V");
        VDrive = new Button("V");

        root.setPrefWidth(200);

        tableView = new TableView();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.setPrefHeight(800);

        store.setDisable(true);
        item.setDisable (true);
        drive.setDisable(true);


        HBox top = new HBox( );

            /*top.add(new Text("name  : "),0,1);
            top.add(name,1,1);

            top.add(saveButton,1,3);
            saveButton.setMinWidth(root.getPrefWidth());

            top.setHgap(12);
            top.setVgap(12);
            top.setAlignment(Pos.BASELINE_CENTER);

*/
        top.getChildren().add(storeTex) ;
        top.getChildren().add(store);
        top.getChildren().add(Vstore);
        top.getChildren().add(itemTex) ;
        top.getChildren().add(item);
        top.getChildren().add(Vitem);
        top.getChildren().add(driverTex) ;
        top.getChildren().add(drive);
        top.getChildren().add(VDrive);
        top.getChildren().add(numberTex);
        top.getChildren().add(num);
        top.getChildren().add(date);
        top.getChildren().add(saveButton);

        top.setSpacing(6.5);

        root.setPadding(new Insets(20));
        root.setSpacing(5);

        Separator line = new Separator();
        line.setOrientation(Orientation.HORIZONTAL);

//            root.getChildren().add(top);
//            root.getChildren().add(line);

        root.getChildren().add(tableView);
        root.getChildren().add(line);
        root.getChildren().add(top) ;

    }
    public StackPane getRoot() {
        pane = new StackPane();
        pane.getChildren().add(root);
        return pane ;
    }
}
