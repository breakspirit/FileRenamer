package com.breakspirit.fileRenamer;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.google.common.base.Strings;

/**
 * @author Kevin Bernard
 */
class FileNameTransformation {

    static String applyPrefix(String inputFileName, String prefixValue) {
        if(!Strings.isNullOrEmpty(prefixValue)) {

            return prefixValue + inputFileName;
        } else {
            return inputFileName;
        }
    }

    static String applySuffix(String inputFileName, String suffixValue) {
        if(!Strings.isNullOrEmpty(suffixValue)) {
            String fileNameWithoutExtension = FilenameUtils.removeExtension(inputFileName);
            String extension = FilenameUtils.getExtension(inputFileName);

            return fileNameWithoutExtension + suffixValue + "." + extension;
        } else {
            return inputFileName;
        }
    }

    static String applyNewExtension(String inputFileName, String newExtension) {
        if(!Strings.isNullOrEmpty(newExtension)) {
            String fileNameWithoutExtension = FilenameUtils.removeExtension(inputFileName);

            return fileNameWithoutExtension + "." + newExtension;
        } else {
            return inputFileName;
        }
    }

    static String applyTokenizedRename(File inputFile, String tokenizedRenameString, int ascendingNumbersCounter, String ascendingNumbersToken, String creationDateToken) {
//        String tokenizedNameWithoutExtension = FilenameUtils.removeExtension(inputFile.getName());

//        try {
//            BasicFileAttributes fileAttributes = Files.readAttributes(inputFile.toPath(), BasicFileAttributes.class);
//            Metadata metadata = ImageMetadataReader.readMetadata(inputFile);
//
//            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
//            Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
//
//        } catch (ImageProcessingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Apply all the tokens
        tokenizedRenameString = tokenizedRenameString.replaceAll(ascendingNumbersToken, String.valueOf(ascendingNumbersCounter));
//        tokenizedRenameString = tokenizedRenameString.replaceAll(creationDateToken, fileAttributes.creationTime()..ye);

        if(inputFile.getName().contains(".")) {
            // File has an extension, so we need to add it back on
            return tokenizedRenameString + "." + FilenameUtils.getExtension(inputFile.getName());
        }
        return tokenizedRenameString;
    }

    static String applyTextReplace(String inputFileName, String replaceThisString, String withThisString) {
        String tokenizedNameWithoutExtension = FilenameUtils.removeExtension(inputFileName);
        if(inputFileName.contains(".")) {
            // File has an extension, so we need to add it back on
            return tokenizedNameWithoutExtension.replace(replaceThisString, withThisString) + "." + FilenameUtils.getExtension(inputFileName);
        }
        return tokenizedNameWithoutExtension.replace(replaceThisString, withThisString);
    }
}
