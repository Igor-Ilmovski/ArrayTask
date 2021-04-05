package by.ilmovsky.arraytask.parser;

import by.ilmovsky.arraytask.exception.BaseArrayException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class BaseArrayStringParser {

    private final static String STRING_IS_EMPTY             = "Argument String is null or zero length";
    private final static String SEPARATOR_IS_EMPTY          = "Separator is empty";
    private final static String INVALID_VALUE_IN_STRING     = "Invalid value in line '%s'";

    static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger();

    public int[] parseStringAsInt(String stringAsIntegers, String separator) throws BaseArrayException {
        if (separator.isEmpty()) {
            throw new IllegalArgumentException(SEPARATOR_IS_EMPTY);
        }
        if (stringAsIntegers.isEmpty()) {
            throw new IllegalArgumentException(STRING_IS_EMPTY);
        }

        String[] strArray = stringAsIntegers.split(separator);
        int[] values = new int[strArray.length];
        try {
            for (int i =0; i < strArray.length; i++) {
                values[i] = Integer.parseInt( strArray[i]);
            }
        } catch ( NumberFormatException e) {
            String errorMessage = String.format(INVALID_VALUE_IN_STRING, stringAsIntegers);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
        return values;
    }

}
