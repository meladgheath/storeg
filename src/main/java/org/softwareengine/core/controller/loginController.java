package org.softwareengine.core.controller;

import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.softwareengine.core.model.Paths;
import org.softwareengine.core.model.user;
import org.softwareengine.core.view.loginView;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;


public class loginController {



        public loginView view ;
        Stage primaryStage ;

        public loginController(Stage primaryStage) throws FileNotFoundException {

            this.primaryStage = primaryStage ;
            initiate();
        }
        public void initiate () throws FileNotFoundException {

            InputStream in = getClass().getResourceAsStream("/version.properties");
            Properties ps = new Properties();
            try {
                ps.load(in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            view = new loginView();
            view.appVersion.setText("Version : "+ps.getProperty("application.version"));
            view.lock.setImage(new Image(getClass().getResourceAsStream(Paths.LOCK.getPath())));

            primaryStage.setScene(view.getScene());
            primaryStage.show();

            view.userName.setOnAction(onUserNameField());
            view.password.setOnAction(onPasswordField());

        }

        public EventHandler<ActionEvent> onUserNameField() {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {


                    user.setId(Integer.parseInt(view.userName.getText()));
                    try {
                        user.getUser();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    if (view.userName.getText().equalsIgnoreCase(user.getId()+"")) {
                        if (!view.root.getChildren().contains(view.password))
                            view.root.getChildren().remove(view.pane);
                        view.root.getChildren().add(view.password) ;
                        view.password.requestFocus();
                        view.root.getChildren().add(view.pane);
                    }
                    else {
                        view.root.getChildren().remove(view.password);
                        TranslateTransition firstTransition = new TranslateTransition();
                        firstTransition.setNode(view.userName);
                        firstTransition.setToX(10);
                        firstTransition.setDuration(Duration.seconds(0.02));
                        firstTransition.setCycleCount(2);
                        firstTransition.setAutoReverse(true);

                        firstTransition.play();

                        firstTransition.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                TranslateTransition secondTransition = new TranslateTransition();
                                secondTransition.setNode(view.userName);
                                secondTransition.setToX(-10);
                                secondTransition.setDuration(Duration.seconds(0.02));
                                secondTransition.setAutoReverse(true);
                                secondTransition.setCycleCount(2);
                                secondTransition.play();
                            }
                        });

                    }
                }
            };
        }


        public EventHandler<ActionEvent> onPasswordField () {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (user.getPassword()==null) {
                        try {
                            user.getUser();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    try {
                        if (view.password.getText().equalsIgnoreCase(user.getPassword())) {
                            view.lock.setImage(new Image(getClass().getResourceAsStream(Paths.UNLOCK.getPath())));

                            Task<Void> sleep = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    Thread.sleep(2000);
                                    return null;
                                }
                            };

                            sleep.setOnSucceeded(onSucceeded());

                            new Thread(sleep).start();
                        }
                        else {


                           TranslateTransition transition = new TranslateTransition();
                            transition.setNode(view.lock);
                            transition.setDuration(Duration.seconds(0.01));
                            transition.setToX(15);
                            transition.setCycleCount(2);
                            transition.setAutoReverse(true);
                            transition.play();

                            TranslateTransition transition2 = new TranslateTransition();
                            transition2.setNode(view.lock);
                            transition2.setDuration(Duration.seconds(0.01));
                            transition2.setToX(-15);
                            transition2.setCycleCount(2);
                            transition2.setAutoReverse(true);

                            transition.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    transition2.play();
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }} ;
        }
        public EventHandler<WorkerStateEvent> onSucceeded () {
            return new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {

                    HomeController control = new HomeController(primaryStage);

                    primaryStage.setScene(control.view.getScene());

                    Dimension screensize =Toolkit.getDefaultToolkit().getScreenSize() ;


                    double x = (screensize.getWidth ()  - primaryStage.getWidth()) /2 ;
                    double y = (screensize.getHeight() - primaryStage.getHeight()) /2 ;

                    primaryStage.setX(x);
                    primaryStage.setY(y);

                }
            };
            }


}
