package org.softwareengine.core.model;

public enum Paths {
    SETTING(iconPath()+"icons8-settings-64.png"),
    ITEMS(iconPath() +"icons8-open-box-64.png"),
    BANKS(iconPath()+"icons8-merchant-account-64.png"),
    STORE(iconPath()+"icons8-warehouse-64.png"),
    TRANSFER(iconPath()+"icons8-data-transfer-64.png"),
    CUSTOMER(iconPath()+"icons8-customer-64.png"),
    ICONS(iconPath()+"adding.png"),
    TREASURY(iconPath()+"piggy-bank.png"),
    EMPLOYMENT(iconPath()+"man.png"),
    USERS(iconPath()+"icons8-landlord-64.png"),
    TRANSACTION(iconPath()+"icons8-transaction-list-64.png"),
    LOCK(iconPath()+"icons8-lock-512.png"),
    UNLOCK(iconPath()+"icons8-padlock-512.png"),
    PERMISSIONS(iconPath()+"icons8-user-rights-64.png"),
    DEBTBOOK(iconPath()+"icons8-copybook-64.png"),
    ADD(iconPath()+"add.png"),
    PACKAGE(iconPath()+"package.png"),
    DISBURSEMENT(iconPath()+"icons8-bank-card-missing-64.png"),

    ATTUCH(iconPath()+"icons8-attachment-file-32.png"),
    SAVE(iconPath()+"icons8-save-50.png"),
    PRINT(iconPath()+"icons8-print-100.png"),
    SAVE_PURCHASE_BUTTON(iconPath()+"icons8-return-purchase-80.png");

    public final String path ;

    Paths(String p) {
        path = p ;
    }

    public String getPath(){
        return path ;
    }
//    private final static String imagePath(){
//        return     "src\\main\\resources\\img\\" ;
//    }
    private static String iconPath() {
        return "/icons/";
    }
}