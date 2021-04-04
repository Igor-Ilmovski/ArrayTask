package by.ilmovsky.arraytask.exception;

public class BaseArrayException extends Exception {

    public BaseArrayException(String messageString) {
        super(messageString);
    }

    public BaseArrayException(Throwable cause) {
        super(cause);
    }

    public BaseArrayException(String messageString, Throwable cause) {

        super(messageString, cause);
    }
}
