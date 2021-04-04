package by.ilmovsky.arraytask.reader;

import by.ilmovsky.arraytask.exception.BaseArrayException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BaseArrayFileReader {

    private final static String FILE_LOADING_FAILED = "Failed to load file '%s'";
    private final static String FILENAME_IS_EMPTY   = "File name mustn't be empty";

    static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger();

    public List<String> loadStringsFromFile(String fileName) throws BaseArrayException {
        if (fileName.isEmpty()) {
            Logger.log(Level.ERROR, FILENAME_IS_EMPTY);
            throw new BaseArrayException(FILENAME_IS_EMPTY);
        }
        List<String> listOfFileStrings;
        try {
            Path txtPath = new File(fileName).toPath();
            listOfFileStrings = Files.readAllLines(txtPath, StandardCharsets.UTF_16);
        } catch (IOException e) {
            String errorMessage = String.format(FILE_LOADING_FAILED, fileName);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
        return listOfFileStrings;
    }
}


