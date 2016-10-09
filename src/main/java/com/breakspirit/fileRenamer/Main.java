package com.breakspirit.fileRenamer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Logger.getLogger("uh").log(Level.INFO, "Starting the File Renamer app");
        Parent root = FXMLLoader.load(getClass().getResource("/fileRenamer.fxml"));
        primaryStage.setTitle("File Renamer");

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
