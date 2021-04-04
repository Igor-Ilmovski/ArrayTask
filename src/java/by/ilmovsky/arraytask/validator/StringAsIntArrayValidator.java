package by.ilmovsky.arraytask.validator;

public class StringAsIntArrayValidator {
    private final static String STRING_AS_INT_REGEX = "\\d+(?:,\\d+)*";

    public boolean isStringAsIntValid(String strAsInt) {
        return strAsInt.matches(STRING_AS_INT_REGEX);
    }
}
