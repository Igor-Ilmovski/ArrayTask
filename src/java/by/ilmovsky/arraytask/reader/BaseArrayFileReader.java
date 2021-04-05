package by.ilmovsky.arraytask.reader;

import by.ilmovsky.arraytask.exception.BaseArrayException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BaseArrayFileReader {

    private final static String FILE_LOADING_FAILED = "Failed to load file '%s'";
    private final static String FILENAME_IS_EMPTY   = "File name mustn't be empty";
    private final static String FILE_NOT_FOUND      = "file '%s' not found";

    static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger();

    public List<String> loadStringsFromFile(String fileName) throws FileNotFoundException {
        if (fileName.isEmpty()) {
            Logger.log(Level.ERROR, FILENAME_IS_EMPTY);
            throw new IllegalArgumentException(FILENAME_IS_EMPTY);
        }
        Path txtPath;
        List<String> listOfFileStrings = null;

        try {
            txtPath = new File(fileName).toPath();
        } catch (Exception  e) {
            Logger.log(Level.ERROR, String.format(FILE_NOT_FOUND, fileName));
            throw new FileNotFoundException();
        }

        try {
            listOfFileStrings = Files.readAllLines(txtPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            Logger.log(Level.ERROR, String.format(FILE_LOADING_FAILED, fileName));
        }
        return listOfFileStrings;
    }
}


