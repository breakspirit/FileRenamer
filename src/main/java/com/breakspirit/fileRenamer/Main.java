package com.breakspirit.fileRenamer;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.breakspirit.fileRenamer.model.FileToRename;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

@SuppressWarnings("FieldCanBeLocal")
public class Main extends Application {

    private static double windowWidth = 800;
    private static double windowHeight = 600;

    private final ObservableList<FileToRename> filesToRename = FXCollections.observableArrayList();

    private Logger logger = Logger.getLogger("Main");

    @Override
    public void start(final Stage primaryStage) throws Exception{

        logger.log(Level.INFO, "Starting the File Renamer app");
//        Parent root = FXMLLoader.load(getClass().getResource("/fileRenamer.fxml"));
        primaryStage.setTitle("File Renamer");
        primaryStage.setResizable(false);

        // Set up the panes
        VBox rootPane = new VBox();
        rootPane.setAlignment(Pos.TOP_CENTER);
        rootPane.setPrefWidth(windowWidth);
        rootPane.setSpacing(10);
        rootPane.setPadding(new Insets(5));

        GridPane buttonsPane = new GridPane();
//        buttonsPane.setPrefWidth(windowWidth);
        buttonsPane.setPadding(new Insets(10));
        buttonsPane.setHgap(10);
        buttonsPane.setVgap(10);

        final TableView table = new TableView();
        table.setPrefWidth(windowWidth);
        Button openFileButton = new Button("Choose Files to Rename");
        openFileButton.setPrefWidth(windowWidth / 2);
        rootPane.getChildren().add(openFileButton);
        rootPane.getChildren().add(table);
        rootPane.getChildren().add(buttonsPane);

        // Set up the file list table
        TableColumn originalFileNameColumn = new TableColumn("Original File Name");
        originalFileNameColumn.setPrefWidth((windowWidth / 2) - 1);
        TableColumn updatedFileNameColumn = new TableColumn("Updated File Name Preview");
        updatedFileNameColumn.setPrefWidth((windowWidth / 2) - 1);
        table.getColumns().addAll(originalFileNameColumn, updatedFileNameColumn);
        originalFileNameColumn.setCellValueFactory(new PropertyValueFactory<FileToRename, String>("originalFileName"));
        updatedFileNameColumn.setCellValueFactory(new PropertyValueFactory<FileToRename, String>("updatedFileName"));

        // Set up the UI elements
        Label prefixLabel = new Label("Prefix");
        TextField prefixField = new TextField();
        final Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        applyButton.setDisable(true);

        GridPane.setHalignment(prefixLabel, HPos.RIGHT);
        buttonsPane.add(prefixLabel, 0, 0);

//        GridPane.setHalignment(lNameLbl, HPos.RIGHT);
//        buttonsPane.add(lNameLbl, 0, 1);

        GridPane.setHalignment(prefixField, HPos.LEFT);
        buttonsPane.add(prefixField, 1, 0);


//        GridPane.setHalignment(lNameFld, HPos.LEFT);
//        buttonsPane.add(lNameFld, 1, 1);

//        GridPane.setHalignment(openFileButton, HPos.RIGHT);
//        buttonsPane.add(openFileButton, 0, 2, 2, 1);

        GridPane.setHalignment(applyButton, HPos.RIGHT);
        buttonsPane.add(applyButton, 3, 3, 2, 1);

        // Set up the file chooser
        final FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File("C:\\Users\\break\\Desktop\\file rename test folder"));
        fileChooser.setTitle("Choose files to be renamed");
        openFileButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                logger.log(Level.INFO, "Should be showing file select window");

                List<File> filesSelected = fileChooser.showOpenMultipleDialog(primaryStage);

                if(filesSelected != null) {
                    logger.log(Level.INFO, "Number of files chosen: " + filesSelected.size());


                    for(File file : filesSelected) {
                        FileToRename fileToRename = new FileToRename(file.getName(), file.getName());
                        filesToRename.add(fileToRename);
                    }

                    applyButton.setDisable(false);
                    table.setItems(filesToRename);

                } else {
                    logger.log(Level.INFO, "No files were chosen");
                }
            }
        });

        // Set up the apply button
        applyButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                logger.log(Level.INFO, "User chose to apply the changes");

                if(filesToRename.isEmpty()) {
                    logger.log(Level.SEVERE, "User tried to apply changes to an empty list of files, which should not happen");
                    return;
                }

                for(FileToRename fileToRename : filesToRename) {
                    //todo do stuff to the files here
                }
            }
        });

        // Set up the scene and show the stage
        Scene scene = new Scene(rootPane, windowWidth, windowHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
