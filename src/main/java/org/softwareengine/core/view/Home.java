
package org.softwareengine.core.view;


import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;

import javafx.geometry.Orientation;
import javafx.scene.Scene;


import javafx.scene.control.Separator;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;


import java.awt.Dimension ;
import java.awt.Toolkit ;

public class Home {

            public Scene scene ;
            public  BorderPane root ;

            public JFXButton settingButton     ;
            public JFXButton itemButton        ;
            public JFXButton bankButton;
            public JFXButton listButton;
            public JFXButton disbursementButton;
            public JFXButton transferButton    ;
            public JFXButton amountButton;
            public JFXButton moneyButton       ;
            public JFXButton treasuryButton    ;
            public JFXButton typesButton;
            public JFXButton userButton        ;
            public JFXButton transactionButton ;
            public JFXButton noticButton;
            public JFXButton permissionsButton ;
            public JFXButton depthBookButton   ;


            public ImageView itemImageview          ;
            public ImageView bankImageview          ;
            public ImageView listImageview;
            public ImageView disbursementImageview  ;
            public ImageView settingImageview       ;
            public ImageView transferImageview      ;
            public ImageView amountsImageview       ;
            public ImageView moneyImageview         ;
            public ImageView treasuryImageview      ;
            public ImageView typesImageview         ;
            public ImageView userImageview          ;
            public ImageView transactionsImageview  ;
            public ImageView customerTypeImageview  ;
            public ImageView permissionsImageview   ;
            public ImageView noticImageview         ;


            public Home() {


                root = new BorderPane() ;
                root.setPrefWidth(90);
                root.setPrefHeight(35);

                //*********************Right**********************************


                settingButton     = new JFXButton() ;
                itemButton        = new JFXButton() ;
                bankButton = new JFXButton() ;
                listButton = new JFXButton() ;
                disbursementButton = new JFXButton() ;
                transferButton    = new JFXButton() ;
                amountButton = new JFXButton() ;
                moneyButton       = new JFXButton() ;
                treasuryButton    = new JFXButton() ;
                typesButton = new JFXButton() ;
                userButton        = new JFXButton() ;
                transactionButton = new JFXButton() ;
                noticButton = new JFXButton() ;
                permissionsButton = new JFXButton() ;
                depthBookButton   = new JFXButton() ;

                settingImageview      = new ImageView() ;
                itemImageview         = new ImageView() ;
                bankImageview = new ImageView() ;
                listImageview = new ImageView() ;
                disbursementImageview = new ImageView() ;
                transferImageview     = new ImageView() ;
                amountsImageview = new ImageView() ;
                moneyImageview        = new ImageView() ;
                treasuryImageview     = new ImageView() ;
                typesImageview = new ImageView() ;
                userImageview         = new ImageView() ;
                transactionsImageview = new ImageView() ;
                customerTypeImageview = new ImageView() ;
                permissionsImageview  = new ImageView() ;
                noticImageview = new ImageView() ;

                
                setToolTips();

                settingButton.setMinWidth(root.getPrefWidth());
                settingButton.setMinHeight(root.getPrefHeight());

                itemButton.setMinWidth(root.getPrefWidth());
                itemButton.setMinHeight(root.getPrefHeight());

                bankButton.setMinWidth(root.getPrefWidth());
                bankButton.setMinHeight(root.getPrefHeight());

                listButton.setMinWidth(root.getPrefWidth());
                listButton.setMinHeight(root.getPrefHeight());

                disbursementButton.setMinWidth(root.getPrefWidth());
                disbursementButton.setMinHeight(root.getPrefHeight());


                transferButton.setMinWidth(root.getPrefWidth());
                transferButton.setMinHeight(root.getPrefHeight());

                amountButton.setMinWidth(root.getPrefWidth()  );
                amountButton.setMinHeight(root.getPrefHeight());

                moneyButton.setMinWidth(root.getPrefWidth()  );
                moneyButton.setMinHeight(root.getPrefHeight());

                treasuryButton.setMinWidth(root.getPrefWidth()  );
                treasuryButton.setMinHeight(root.getPrefHeight());

                typesButton.setMinWidth(root.getPrefWidth()  );
                typesButton.setMinHeight(root.getPrefHeight());

                userButton .setMinWidth(root.getPrefWidth()  );
                userButton .setMinHeight(root.getPrefHeight());

                transactionButton.setMinWidth(root.getPrefWidth());
                transactionButton.setMinHeight(root.getPrefHeight());

                noticButton.setMinWidth(root.getPrefWidth());
                noticButton.setMinHeight(root.getPrefHeight());

                depthBookButton.setMinWidth(root.getPrefWidth());
                depthBookButton.setMinHeight(root.getPrefHeight());

                permissionsButton.setMinWidth(root.getPrefWidth());
                permissionsButton.setMinHeight(root.getPrefHeight());

                VBox right = new VBox() ;
                right.setSpacing(15);
                right.setPadding(new Insets(20,20,20,20));

                right.getChildren().add(typesButton        );
                right.getChildren().add(itemButton         );
                right.getChildren().add(bankButton         );
//                right.getChildren().add(amountButton       );
                right.getChildren().add(noticButton        );
                right.getChildren().add(listButton         );
                right.getChildren().add(settingButton      );

                ////////////////////////////////////////////////
                HBox allRight = new HBox() ;
                Separator LineMid = new Separator() ;
                LineMid.setOrientation(Orientation.VERTICAL);

                LineMid.setStyle("-fx-background-color: #9ACD32;");

                allRight.getChildren().add(LineMid) ;
                allRight.getChildren().add(right) ;
                allRight.setStyle("-fx-background-color: rgb(0,200,0)");

                ///////////////////////////////////////////

                ////////////////////////////////////////////////////////
                root.setRight(allRight);
                Dimension screensize =Toolkit.getDefaultToolkit().getScreenSize() ;
                double width = screensize.getWidth() - (screensize.getWidth()/4) ;
                double height = screensize.getHeight() - (screensize.getHeight()/4);

                System.out.println(width+"   "+screensize.getWidth());
                System.out.println(height+"  "+screensize.getHeight());
                scene = new Scene(root , width, height) ;
            }

            public Scene getScene() {
                return scene ;
            }

            public void setToolTips() {

                itemButton        .setGraphic(itemImageview );
                bankButton        .setGraphic(bankImageview);
                listButton.setGraphic(listImageview);
                disbursementButton.setGraphic(disbursementImageview);
                transferButton    .setGraphic(transferImageview);
                amountButton      .setGraphic(amountsImageview);
                typesButton       .setGraphic(typesImageview);
                transactionButton .setGraphic(transactionsImageview);
                settingButton     .setGraphic(settingImageview);
                noticButton       .setGraphic(noticImageview);

            }
        }