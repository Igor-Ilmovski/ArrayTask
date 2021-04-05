package by.ilmovsky.arraytask.basearray;

import java.lang.*;

import by.ilmovsky.arraytask.exception.BaseArrayException;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class BaseArray {

    private final static String ERR_INVALID_ARRAY_LENGTH    = "Invalid array length";
    private final static String ERR_ARRAY_IS_NULL           = "Array is empty";
    private final static String ERR_INVALID_ITEM_VALUE      = "Invalid array item value: %d";
    private final static String ERR_INVALID_ARRAY_INDEX     = "invalid array index";
    private final static String ERR_ARRAY_CREATION          = "error due to array creation";
    private final static String INF_ARRAY_CREATED           = "array object created";

    static final Logger Logger = LogManager.getLogger();
    private int[] array;

    public BaseArray(int[] array) throws BaseArrayException {
        if (array == null) {
            throw new IllegalArgumentException();
        }
        try {
            setBaseArray(array);
        } catch (Exception e) {
            throw new BaseArrayException(ERR_ARRAY_CREATION);
        }
        Logger.log(Level.DEBUG, INF_ARRAY_CREATED);
    }

    public BaseArray(int arrayLength) throws BaseArrayException {
        if (arrayLength >= 0) {
            setBaseArray(new int[arrayLength]);
        } else {
            String errorMessage = String.format("%s(%d): " + ERR_INVALID_ARRAY_LENGTH, "BaseArray", arrayLength);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
    }

    private BaseArray setBaseArray(int[] array) {
        if (array == null) {
            Logger.log(Level.ERROR, String.format("%s(): %s","setBaseArray", ERR_ARRAY_IS_NULL));
            throw new IllegalArgumentException();
        }
        this.array = Arrays.copyOf(array, array.length);
        return this;
    }

    public boolean isEmpty() {
        return (array == null) || (array.length == 0);
    }

    public int getBaseArrayLength() throws BaseArrayException {
        if (array == null) {
            Logger.log(Level.ERROR, ERR_ARRAY_IS_NULL);
            throw new BaseArrayException(ERR_ARRAY_IS_NULL);
        }
        return array.length;
    }

    public int[] getBaseArray() throws BaseArrayException {
        if (array == null) {
            String errorMessage = String.format("%s(): %s","getBaseArray", ERR_ARRAY_IS_NULL);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
        return Arrays.copyOf(array, array.length);
    }

    public int getItem( int index) throws BaseArrayException {
        if (this.isEmpty()) {
            throw new BaseArrayException("getItem: " + ERR_ARRAY_IS_NULL);
        }
        if (index < 0 || index >= array.length) {
            String errorMessage = String.format("%s(%d): %s", "getItem", index, ERR_INVALID_ARRAY_INDEX);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
        int item;
        try {
            item = array[index];
        } catch (NullPointerException e) {
            String errorMessage = String.format("%s(%d): %s %d", "getItem", index, ERR_INVALID_ITEM_VALUE, array[index]);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
        return item;
    }

    public void setItem( int index, int newValue) throws BaseArrayException {
        if (this.isEmpty()) {
            throw new BaseArrayException("setItem: " + ERR_ARRAY_IS_NULL);
        }
        if (index < 0 || index >= array.length) {
            String errorMessage = String.format("%s(%d,%d): %s", "setItem", index, newValue, ERR_INVALID_ARRAY_INDEX);
            Logger.log(Level.ERROR, errorMessage);
            throw new ArrayIndexOutOfBoundsException(errorMessage);
        }
        try {
             array[index] = newValue;
            }
        catch ( Exception e) {
            String errorMessage = String.format("%s(%d,%d): %s", "setItem", index, newValue, ERR_INVALID_ITEM_VALUE);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        for (int item : array) {
            strBuilder.append(item);
            strBuilder.append(" ");
        }
        return strBuilder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * Arrays.hashCode(array);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        BaseArray cast_array = (BaseArray) obj;
        return Arrays.equals(this.array, cast_array.array);
    }
}
