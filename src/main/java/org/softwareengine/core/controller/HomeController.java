package org.softwareengine.core.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import org.softwareengine.core.view.Home;
import org.softwareengine.config.languages;
import org.softwareengine.core.model.Paths;

import java.util.Locale;

    public class HomeController {

        public Home view;

        public HomeController() {
            initiated();
            setupLanguages() ;

        }

        private void setupLanguages(){

        languages lang = new languages();

        view.itemButton        .setTooltip(new Tooltip(lang.getWord("item") ));
        view.bankButton        .setTooltip(new Tooltip(lang.getWord("bank") ));
        view.typesButton       .setTooltip(new Tooltip(lang.getWord("types")));
//        view.storeButton       .setTooltip(new Tooltip(lang.getWord("store")));
        view.amountButton      .setTooltip(new Tooltip(lang.getWord("amounts")));
//        view.disbursementButton.setTooltip(new Tooltip(lang.getWord("disbursement")));
        view.settingButton  .setTooltip(new Tooltip(lang.getWord("setting")));
        }

        public void initiated() {

            

            Locale lang = new Locale("en") ;
            Locale.setDefault(lang);

            view = new Home();

                view.itemImageview        .setImage(new Image(getClass().getResourceAsStream(Paths.ITEMS       .getPath())));
                view.bankImageview        .setImage(new Image(getClass().getResourceAsStream(Paths.BANKS       .getPath())));
                view.typesImageview       .setImage(new Image(getClass().getResourceAsStream(Paths.PACKAGE     .getPath())));
//                view.storeImageview       .setImage(new Image(getClass().getResourceAsStream(Paths.STORE       .getPath())));
                view.amountsImageview     .setImage(new Image(getClass().getResourceAsStream(Paths.ADD        .getPath())));
//                view.disbursementImageview.setImage(new Image(getClass().getResourceAsStream(Paths.DISBURSEMENT.getPath())));
                view.noticImageview       .setImage(new Image(getClass().getResourceAsStream(Paths.DISBURSEMENT.getPath())));

                view.settingImageview     .setImage(new Image(getClass().getResourceAsStream(Paths.SETTING    .getPath())));


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
            view.storeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onStoreButton();
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
                    onMoneyButton();
                }
            });
            view.treasuryButton.setOnAction(new EventHandler<ActionEvent>() {
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
            view.depthBookButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onDepthBookButton();
                }
            });
            view.permissionsButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
//                    permissions control = new permissions();
//                    view.root.setCenter(control.view.getDown());
                }
            });

        }



        private void onItemButton () {
            ItemController control = new ItemController();
            view.root.setCenter(control.view.getRoot());

            System.out.println("width  = "+view.getScene().getWidth());
            System.out.println("height = "+view.getScene().getHeight());

        }
        private void onStoreButton () {
            StoreController control = new StoreController();
            view.root.setCenter(control.view.getRoot());
        }
        private void onNoticButton() {
            noticController control = new noticController();
            view.root.setCenter(control.view.getRoot());
        }
        private void onPurchaseButton() {
//            Purchase control = new Purchase();
//            view.root.setCenter(control.view.getRoot());
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
        private void onMoneyButton() {
//            payANDcatch control = new payANDcatch() ;
//            view.root.setCenter(control.view.getRoot());
        }
        private void onTreasuryButton() {
//            treasury control = new treasury() ;
//            view.root.setCenter(control.view.getRoot());
        }
        private void onTypeButton() {
            typeController control = new typeController();
            view.root.setCenter(control.view.getRoot());
//            employment control = new employment() ;
//            view.root.setCenter(control.view.getRoot());
        }
        private void onUserButton() {
//            user control = new user() ;
//            view.root.setCenter(control.view.getRoot());
        }
        private void onDepthBookButton() {
//            depthBook control = new depthBook();
//            view.root.setCenter(control.view.getRoot());
        }
        private void onTransactionButton() {
            TransactionController control = new TransactionController();
            view.root.setCenter(control.view.getRoot());
        }

    }