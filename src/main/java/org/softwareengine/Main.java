package org.softwareengine;


import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.softwareengine.core.model.Paths;
import org.softwareengine.core.controller.HomeController;


import java.io.*;
import java.time.InstantSource;
import java.util.Objects;


public class Main extends Application {
    public static void main(String[] args) throws IOException {

   /*   byte[] b = Files.readAllBytes(java.nio.file.Paths.get("add.png"));
        String base = Base64.getEncoder().encodeToString(b);
        System.out.println(base);

        byte [] c = Base64.getDecoder().decode(base);
        System.out.println(c.length);

        FileOutputStream out  = new FileOutputStream("melad.png");
        out.write(c);*/

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HomeController control = new HomeController();
        primaryStage.setScene(control.view.getScene());
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Paths.ICONS.getPath()))));

        if (!checkDB()) {
            try {
                putItThere();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        primaryStage.show();

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