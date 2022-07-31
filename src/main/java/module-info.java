module storeg {
    requires java.sql ;

    requires javafx.graphics;
    requires javafx.controls;

    requires com.jfoenix;
    requires org.controlsfx.controls;
    requires org.xerial.sqlitejdbc;
//    requires dynamicreports.core;
    requires jasperreports;


    exports org.softwareengine;
    exports org.softwareengine.core.model;
    exports org.softwareengine.core.controller;
    exports org.softwareengine.core.view;
    exports org.softwareengine.config;
    exports org.softwareengine.utils.service;
    exports org.softwareengine.utils.ui;
}