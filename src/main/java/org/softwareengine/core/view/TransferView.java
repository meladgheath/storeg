package org.softwareengine.core.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class TransferView {

    public static StackPane pane ;
    public BorderPane root;

    public TextField item;
    public TextField from ;
    public TextField to   ;
    public TextField amount ;

    public Button itemSearch ;
    public Button fromSearch ;
    public Button toSearch   ;


    public Button fromTo ;
    public Button toFrom ;

    public Button fromToAll ;
    public Button toFromAll ;

    public TableView tableView_from ;
    public TableView tableview_to ;


    public Text fromTex ;
    public Text toTex   ;

    public TransferView() {

        root     = new  BorderPane();

        item = new TextField();
        amount = new TextField();


        from = new TextField();
        to   = new TextField();

        fromTex = new Text() ;
        toTex   = new Text() ;

        fromSearch = new Button("V");
        toSearch  = new Button("V");

        Separator line = new Separator();

        itemSearch = new Button("V");

        fromTo    = new Button("    <     ") ;
        toFrom    = new Button("    >     ") ;

        fromToAll = new Button("   <<    ") ;
        toFromAll = new Button("   >>    ") ;

        tableView_from = new TableView();
        tableView_from.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView_from.setPrefWidth(300);

        tableview_to   = new TableView();
        tableview_to  .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableview_to  .setPrefWidth(300);

        GridPane primary = new GridPane() ;
        primary.add(new Text("item : "),0,0 );
        primary.add(item,1,0);
        primary.add(itemSearch,2,0);

//        primary.add(new Text("amount : "),0,1);
//        primary.add(amount,1,1);



        primary.setPadding(new Insets(12));
        primary.setAlignment(Pos.BASELINE_CENTER);
        primary.setHgap(8);
        primary.setVgap(8);

        ///////////////////////////////////////////////
//        HBox secandry = new HBox();

        /////////////////////////Right///////////////////////////////
        GridPane right = new GridPane();
        right.add(fromTex,0,0);
        right.add(from  , 1, 0);
        right.add(fromSearch,2,0);

        right.add(tableView_from,1,2);
        right.setVgap(8);
        right.setHgap(2);

        right.setPadding(new Insets(20));
        /////////////////////////////////////////////////////////////

        ////////////////////////mid/////////////////////////////////////
        VBox mide = new VBox();
        mide.getChildren().add(amount);
        mide.getChildren().add(fromTo);
        mide.getChildren().add(toFrom);
        mide.getChildren().add(fromToAll);
        mide.getChildren().add(toFromAll);

        mide.setPadding(new Insets(100,5,0,5));
        mide.setSpacing(15);

        mide.setAlignment(Pos.BASELINE_CENTER);

        amount.setMaxWidth(100);

        /////////////////////////Left////////////////////////////////
        GridPane left = new GridPane();
        left.add(toTex,0,0);
        left.add(to,1,0);
        left.add(toSearch,2,0);
        left.add(tableview_to,1,2);

        left.setVgap(8);
        left.setHgap(2);
        left.setPadding(new Insets(20));
        ////////////////////////////////////////////////////////////

    /*    secandry.getChildren().add(right);
        secandry.getChildren().add(mide);
        secandry.getChildren().add(left);

        secandry.setSpacing(20);
        secandry.setAlignment(Pos.BASELINE_CENTER);
        secandry.setPadding(new Insets(8));
*/
        root.setCenter(mide);
        root.setRight(right);
        root.setLeft(left);
    }
    public StackPane getRoot() {
        pane = new StackPane();
        pane.getChildren().add(root);
        return pane ;
    }
}