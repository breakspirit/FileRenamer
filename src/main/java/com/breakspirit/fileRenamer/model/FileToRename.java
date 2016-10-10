package com.breakspirit.fileRenamer.model;


import javafx.beans.property.SimpleStringProperty;

public class FileToRename {

    private SimpleStringProperty originalFileName;
    private SimpleStringProperty updatedFileName;

    public FileToRename(String originalFileName, String updatedFileName) {
        this.originalFileName = new SimpleStringProperty(originalFileName);
        this.updatedFileName = new SimpleStringProperty(updatedFileName);
    }

    public String getOriginalFileName() {
        return originalFileName.get();
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName.set(originalFileName);
    }

    public String getUpdatedFileName() {
        return updatedFileName.get();
    }

    public void setUpdatedFileName(String updatedFileName) {
        this.updatedFileName.set(updatedFileName);
    }
}
