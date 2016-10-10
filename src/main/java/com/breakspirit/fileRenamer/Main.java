package com.breakspirit.fileRenamer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SuppressWarnings("FieldCanBeLocal")
public class Main extends Application {

    private Logger logger = Logger.getLogger("Main");

    @Override
    public void start(final Stage primaryStage) throws Exception{

        logger.log(Level.INFO, "Starting the File Renamer app");
        Parent root = FXMLLoader.load(getClass().getResource("/fileRenamer.fxml"));

        primaryStage.setTitle("File Renamer");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY); // Removes the border around the window

        // Set up the scene and show the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
