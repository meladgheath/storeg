package org.softwareengine.core.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.softwareengine.core.view.Home;
import org.softwareengine.config.languages;
import org.softwareengine.core.model.Paths;

import java.io.FileNotFoundException;
import java.util.Locale;

    public class HomeController {

        public Stage primaryStage ;
        public Home view;
        public HomeController(Stage primaryStage) {
            this.primaryStage = primaryStage ;
            initiated();
            setupLanguages() ;

        }

        private void setupLanguages(){

        languages lang = new languages();

        view.itemButton        .setTooltip(new Tooltip(lang.getWord("item") ));
        view.bankButton        .setTooltip(new Tooltip(lang.getWord("bank") ));
        view.typesButton       .setTooltip(new Tooltip(lang.getWord("types")));
        view.amountButton      .setTooltip(new Tooltip(lang.getWord("amounts")));
        view.logoutButton      .setTooltip(new Tooltip(lang.getWord("logout")));
        view.settingButton  .setTooltip(new Tooltip(lang.getWord("setting")));
        }
        public void initiated() {

            //initiated language locale
            //english init
//            Locale lang = new Locale("en") ;
            //arabic init
            Locale lang = new Locale("ar") ;
            Locale.setDefault(lang);

            view = new Home();
                view.itemImageview        .setImage(new Image(getClass().getResourceAsStream(Paths.ITEMS       .getPath())));
                view.bankImageview        .setImage(new Image(getClass().getResourceAsStream(Paths.BANKS       .getPath())));
                view.typesImageview       .setImage(new Image(getClass().getResourceAsStream(Paths.PACKAGE     .getPath())));
                view.listImageview        .setImage(new Image(getClass().getResourceAsStream(Paths.LIST        .getPath())));
                view.logoutImageview      .setImage(new Image(getClass().getResourceAsStream(Paths.LOGOUT      .getPath())));
                view.amountsImageview     .setImage(new Image(getClass().getResourceAsStream(Paths.ADD         .getPath())));
                view.noticImageview       .setImage(new Image(getClass().getResourceAsStream(Paths.DISBURSEMENT.getPath())));
                view.settingImageview     .setImage(new Image(getClass().getResourceAsStream(Paths.SETTING     .getPath())));
            view.settingButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onSettingButton();
                }
            });
            view.itemButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    onItemButton();
                }
            });
            view.bankButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    banksController control = new banksController() ;
                    view.root.setCenter(control.view.getRoot());
                }
            });
            view.listButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onListButton();
                }
            });
            view.disbursementButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onDeliveryButton();
                }
            });
            view.transferButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onTransferButton();
                }
            });
            view.amountButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onAmountButton();}
            });
            view.moneyButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onLogoutButton();
                }
            });
            view.logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onTreasuryButton();
                }
            });
            view.typesButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onTypeButton();
                }
            });
            view.userButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onUserButton();
                }
            });
            view.transactionButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onTransactionButton();
                }
            });
            view.noticButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onNoticButton();
                }
            });
            view.logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    primaryStage.close();
                    try {
                        new loginController(primaryStage);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        }
        private void onItemButton () {
            ItemController control = new ItemController();
            view.root.setCenter(control.view.getRoot());
        }
        private void onListButton() {
            listController control = new listController();
            view.root.setCenter(control.view.getRoot());
        }
        private void onNoticButton() {
            noticController control = new noticController();
            view.root.setCenter(control.view.getRoot());
        }
        private void onDeliveryButton() {
            DeliverController control = new DeliverController();
            view.root.setCenter(control.view.getRoot());
        }
        private void onTransferButton() {
            TransferController control = new TransferController();
            view.root.setCenter(control.view.getRoot());
        }
        private void onSettingButton() {

            SettingController control = new SettingController();
            view.root.setCenter(control.view.getRoot());
        }
        private void onAmountButton() {
            AmountController control = new AmountController() ;
            view.root.setCenter(control.view.getRoot());
        }
        private void onLogoutButton() {

        }
        private void onTreasuryButton() {
        }
        private void onTypeButton() {
            typeController control = new typeController();
            view.root.setCenter(control.view.getRoot());
        }
        private void onUserButton() {
        }
        private void onDepthBookButton() {

        }
        private void onTransactionButton() {
            TransactionController control = new TransactionController();
            view.root.setCenter(control.view.getRoot());
        }

    }