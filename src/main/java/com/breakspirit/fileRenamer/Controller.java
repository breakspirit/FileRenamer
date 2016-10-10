package com.breakspirit.fileRenamer;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.breakspirit.fileRenamer.model.FileToRename;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class Controller {

    private final ObservableList<FileToRename> filesToRename = FXCollections.observableArrayList();

    // UI elements
    public GridPane rootGrid;
    public Button chooseFilesButton;
    public Button applyButton;
    public TableView table;
    public TableColumn originalFileNameColumn;
    public TableColumn updatedFileNameColumn;
    public TextField prefixField;

    private Logger logger = Logger.getLogger("Controller");

    @FXML
    public void initialize() {
        logger.log(Level.INFO, "Initializing the Controller");

        originalFileNameColumn.setCellValueFactory(new PropertyValueFactory<FileToRename, String>("originalFileName"));
        updatedFileNameColumn.setCellValueFactory(new PropertyValueFactory<FileToRename, String>("updatedFileName"));

        applyButton.setDisable(true);
    }

    public void openFileChooserDialog(ActionEvent actionEvent) {
        logger.log(Level.INFO, "Should be showing file select window");

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\break\\Desktop\\file rename test folder"));
        fileChooser.setTitle("Choose files to be renamed");

        List<File> filesSelected = fileChooser.showOpenMultipleDialog(rootGrid.getScene().getWindow());

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

    public void applyRenameOperations(ActionEvent actionEvent) {
        logger.log(Level.INFO, "User chose to apply the changes");

        if(filesToRename.isEmpty()) {
            logger.log(Level.SEVERE, "User tried to apply changes to an empty list of files, which should not happen");
            return;
        }

        for(FileToRename fileToRename : filesToRename) {
            //todo do stuff to the files here
        }
    }
}
