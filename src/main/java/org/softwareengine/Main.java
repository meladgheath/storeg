package org.softwareengine;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.softwareengine.core.controller.loginController;
import org.softwareengine.core.model.Paths;
import org.softwareengine.core.controller.HomeController;



import java.io.*;
import java.time.InstantSource;
import java.util.Objects;


public class Main extends Application {
    public static void main(String[] args) throws IOException {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
      /*  HomeController control = new HomeController();
        primaryStage.setScene(control.view.getScene());
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Paths.ICONS.getPath()))));
*/
        if (!checkDB()) {
            try {
                putItThere();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        primaryStage.show();
        loginController control = new loginController(primaryStage);
//        primaryStage.setScene(control.view.getScene());
//        primaryStage.show();


    }

    public static boolean checkDB() {
        File file = new File(System.getProperty("user.home")+"/db.db") ;
        return (file.exists()) ;
    }
    public static void putItThere() throws IOException {

        InputStream in = Main.class.getResourceAsStream("/database/db.db") ;
        OutputStream out = new FileOutputStream(System.getProperty("user.home")+"/db.db");
        int i  ;

        while ((i=in.read()) != -1)
            out.write(i);

        out.close();
        in.close();
    }
}