package org.softwareengine.core.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.NodeOrientation;
import javafx.stage.FileChooser;
import org.softwareengine.config.languages;
import org.softwareengine.core.view.SettingView;


import java.io.*;

import java.util.Locale;

public class SettingController {

    SettingView view ;


    public SettingController() {
        view = new SettingView();

        initiate();
        setupLanguages() ;

    }
    private void setupLanguages() {
        languages lang = new languages();

        view.backUp.setText(lang.getWord("backup"));
        view.importDB.setText(lang.getWord("import")+" "+lang.getWord("database"));

        view.languagesTex.setText(lang.getWord("languages"));


        view.languages.getItems().set(0,lang.getWord("arabic")) ;
        view.languages.getItems().set(1,lang.getWord("english"));

        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar"))
            view.root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        else
            view.root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
    }

    public void initiate() {

        getComboBox();
        setupLanguages();

        view.backUp.setOnAction(onBackupButton());
        view.importDB.setOnAction(onImportDatabaseButton()) ;

        view.languages.setOnAction(onLanguageChange());



    }

    private void getComboBox() {

        view.languages.getItems().add("arabic") ;
        view.languages.getItems().add("english") ;
    }

    private EventHandler<ActionEvent> onLanguageChange() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Locale locale ;

                if (view.languages.getSelectionModel().getSelectedIndex() == 0 )
                    locale = new Locale("ar") ;
                else
                    locale = new Locale("en");

              Locale.setDefault(locale);
//              initiate();
                setupLanguages();
            }
        };
    }

    private EventHandler<ActionEvent> onBackupButton() {

        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                FileChooser saveBox = new FileChooser();
                saveBox.setTitle("database location");


                saveBox.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("DATABASE files (*_*) db ","*.db")
                ) ;
                saveBox.setInitialFileName("backup");

                File location = saveBox.showSaveDialog(null);


                InputStream file =  getClass().getResourceAsStream("/database/Me.db");
                OutputStream out  = null ;

                try {
                    out = new FileOutputStream(location) ;


                    int i ;
                    while ((i=file.read())!= -1 )
                        out.write(i);

                    file.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        };
    }// end of OnBackUpButton () . . .



    private EventHandler<ActionEvent> onImportDatabaseButton() {

        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("importing know . . . ") ;


                FileChooser openBox = new FileChooser();
                openBox.setTitle("database location");

                openBox.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("DATABASE files (*_*) db ","*.db")
                ) ;

                File location  = openBox.showOpenDialog(null) ;


                // InputStream file =  getClass().getResourceAsStream("/database/Me.db");
                InputStream file = null ;
                // OutputStream out  = getClass().getResourceAsStream("/database/Me.db"); ;
                OutputStream out = null ;

                try {

                    System.out.println(getClass().getResource("/database/Me.db").getFile());
                    file = new FileInputStream(location);
                    out = new FileOutputStream(getClass().getResource("/database/Me.db").getFile()) ;


                    int i ;
                    while ((i=file.read())!= -1 )
                        out.write(i);

                    file.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }// end of onImportDB


}// end of class
