
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

import java.awt.*;


public class Home {


            public Scene scene ;
            public  BorderPane root ;


            public JFXButton settingButton     ;
            public JFXButton itemButton        ;
            public JFXButton bankButton;
            public JFXButton storeButton;
            public JFXButton disbursementButton;
            public JFXButton transferButton    ;
            public JFXButton amountButton;
            public JFXButton moneyButton       ;
            public JFXButton treasuryButton    ;
            public JFXButton typesButton;
            public JFXButton userButton        ;
            public JFXButton transactionButton ;
            public JFXButton customerTypeButton;
            public JFXButton permissionsButton ;
            public JFXButton depthBookButton   ;


            public ImageView itemImageview          ;
            public ImageView bankImageview;
            public ImageView storeImageview;
            public ImageView disbursementImageview;
            public ImageView settingImageview       ;
            public ImageView transferImageview      ;
            public ImageView amountsImageview;
            public ImageView moneyImageview         ;
            public ImageView treasuryImageview      ;
            public ImageView typesImageview;
            public ImageView userImageview          ;
            public ImageView transactionsImageview  ;
            public ImageView customerTypeImageview  ;
            public ImageView permissionsImageview   ;
            public ImageView depthBookImageview     ;


            public Home() {


                root = new BorderPane() ;
                root.setPrefWidth(90);
                root.setPrefHeight(35);

                //*********************Right**********************************


                settingButton     = new JFXButton() ;
                itemButton        = new JFXButton() ;
                bankButton = new JFXButton() ;
                storeButton = new JFXButton() ;
                disbursementButton = new JFXButton() ;
                transferButton    = new JFXButton() ;
                amountButton = new JFXButton() ;
                moneyButton       = new JFXButton() ;
                treasuryButton    = new JFXButton() ;
                typesButton = new JFXButton() ;
                userButton        = new JFXButton() ;
                transactionButton = new JFXButton() ;
                customerTypeButton= new JFXButton() ;
                permissionsButton = new JFXButton() ;
                depthBookButton   = new JFXButton() ;

                settingImageview      = new ImageView() ;
                itemImageview         = new ImageView() ;
                bankImageview = new ImageView() ;
                storeImageview = new ImageView() ;
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
                depthBookImageview    = new ImageView() ;

                
                setToolTips();

                settingButton.setMinWidth(root.getPrefWidth());
                settingButton.setMinHeight(root.getPrefHeight());

                itemButton.setMinWidth(root.getPrefWidth());
                itemButton.setMinHeight(root.getPrefHeight());

                bankButton.setMinWidth(root.getPrefWidth());
                bankButton.setMinHeight(root.getPrefHeight());

                storeButton.setMinWidth(root.getPrefWidth());
                storeButton.setMinHeight(root.getPrefHeight());

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

                customerTypeButton.setMinWidth(root.getPrefWidth());
                customerTypeButton.setMinHeight(root.getPrefHeight());

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
                right.getChildren().add(storeButton        );
                right.getChildren().add(amountButton       );
                right.getChildren().add(disbursementButton );
                /*
                right.getChildren().add(transferButton   );
                right.getChildren().add(transactionButton);*/
                right.getChildren().add(settingButton    );


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
                // settingButton .setGraphic(settingImageview);
                // settingButton .setTooltip(new Tooltip("setting"));
                // String setting = resourceBundle.getString("setting") ;
                // settingButton.setTooltip(new Tooltip(setting)) ;

                


                itemButton    .setGraphic(itemImageview );
//                itemButton.setTooltip(new Tooltip(resourceBundle.getString("item")));


                bankButton.setGraphic(bankImageview);
//                storeButton   .setTooltip(new Tooltip(resourceBundle.getString("store")));
                

                storeButton.setGraphic(storeImageview);
                // purchaseButton.setTooltip(new Tooltip("purchase"));

                disbursementButton.setGraphic(disbursementImageview);
//                deliveryButton.setTooltip(new Tooltip(resourceBundle.getString("delivery")));

                transferButton.setGraphic(transferImageview);
//                transferButton.setTooltip(new Tooltip(resourceBundle.getString("transfer")));

                amountButton.setGraphic(amountsImageview);
//                amountButton.setTooltip(new Tooltip(resourceBundle.getString("amounts")));

                /*moneyButton   .setGraphic(moneyImageview);
                moneyButton   .setTooltip(new Tooltip("money"));*/

                // treasuryButton.setGraphic(treasuryImageview);
                // treasuryButton.setTooltip(new Tooltip("treasury"));

                typesButton.setGraphic(typesImageview);
//                driverButton.setTooltip(new Tooltip(resourceBundle.getString("driver")));

                // userButton      .setGraphic(userImageview);
                // userButton      .setTooltip(new Tooltip("user"));

                transactionButton.setGraphic(transactionsImageview);
//                transactionButton.setTooltip(new Tooltip("transaction list"));

                // customerTypeButton.setGraphic(customerTypeImageview);
                // customerTypeButton.setTooltip(new Tooltip("customer type"));

                // permissionsButton.setGraphic(permissionsImageview);
                // permissionsButton.setTooltip(new Tooltip("permissions"));

                // depthBookButton.setGraphic(depthBookImageview);
                // depthBookButton.setTooltip(new Tooltip("depth Book"));

                settingButton.setGraphic(settingImageview);
//                settingButton.setTooltip(new Tooltip(resourceBundle.getString("setting")));

            }
        }