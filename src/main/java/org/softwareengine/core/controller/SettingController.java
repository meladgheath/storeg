package org.softwareengine.core.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.NodeOrientation;
import javafx.stage.FileChooser;
import org.softwareengine.Main;
import org.softwareengine.config.languages;
import org.softwareengine.core.model.user;
import org.softwareengine.core.view.SettingView;


import java.io.*;

import java.sql.SQLException;
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




                try {
                    Locale.setDefault(locale);
                    user.setLang(locale.getLanguage());
                    user.updateLanguages();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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


                InputStream file =  getClass().getResourceAsStream("/database/db.db");
                OutputStream out  = null ;

                int j = 0 ;
                try {
                    out = new FileOutputStream(location) ;


                    int i ;
                    byte[] buf = new byte[1024] ;

                    while ((i=file.read(buf))!= -1 )
                        out.write(buf,0,i);

                    file.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("the j is "+j);


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



                InputStream file = null ;

                OutputStream out = null ;

                try {


                    file = new FileInputStream(location);
                    out = new FileOutputStream(getClass().getResource("/database/db.db").getFile()) ;


                    int i ;
                    byte[] buf = new byte[1024] ;

                    while ((i=file.read(buf))!= -1 )
                        out.write(buf,0,i);
                    System.out.println("file imported !");

                    file.close();


                    File db = new File(System.getProperty("user.home")+"/db.db") ;
                    System.out.println("File delete : "+db.delete());


                    InputStream in = getClass().getResourceAsStream("/database/db.db") ;

                    OutputStream ou = new FileOutputStream(System.getProperty("user.home")+"/db.db");

                    while ((i=in.read(buf)) != -1)
                        ou.write(buf,0,i);


                    in.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }// end of onImportDB


}// end of class
