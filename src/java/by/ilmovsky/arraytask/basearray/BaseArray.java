package by.ilmovsky.arraytask.basearray;

import java.lang.*;

import by.ilmovsky.arraytask.exception.BaseArrayException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class BaseArray {

    private final static String INVALID_ARRAY_LENGTH    = "Invalid array length";
    private final static String ERR_ARRAY_IS_NULL       = "Array is empty";
    private final static String INVALID_ITEM_VALUE      = "Invalid array item value: %d";
    private final static String INVALID_ARRAY_INDEX     = "invalid array index";

    static final Logger Logger = LogManager.getLogger();
    private int array[];

    public BaseArray(int[] array) {
        setBaseArray(array);
    }

    public BaseArray(int arrayLength) {
        if (arrayLength >= 0) {
            setBaseArray(new int[arrayLength]);
            Logger.log(Level.DEBUG, "BaseArray object created");
        } else {
            String errorMessage = String.format("%s(%d): " + INVALID_ARRAY_LENGTH, "BaseArray", arrayLength);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
    }

    public boolean isEmpty() {
        return (array == null) || (array.length == 0);
    }

    public int getBaseArrayLength() {
        if (array == null) {
            Logger.log(Level.ERROR, ERR_ARRAY_IS_NULL);
            throw new BaseArrayException(ERR_ARRAY_IS_NULL);
        }
        return array.length;
    }

    private BaseArray setBaseArray(int[] array) {
        if (array == null) {
            String errorMessage = String.format("%s(): ","setBaseArray", ERR_ARRAY_IS_NULL);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
        this.array = Arrays.copyOf(array, array.length);
        return this;
    }

    public int[] getBaseArray() {
        if (array == null) {
            String errorMessage = String.format("%s(): ","getBaseArray", ERR_ARRAY_IS_NULL);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
        return Arrays.copyOf(array, array.length);
    }

    public int getItem( int index) {
        if (this.isEmpty()) {
            throw new  IllegalArgumentException("getItem: " + ERR_ARRAY_IS_NULL);
        }
        if (index < 0 || index >= array.length) {
            String errorMessage = String.format("%s(%d): %s", "getItem", index, INVALID_ARRAY_INDEX);
            Logger.log(Level.ERROR, errorMessage);
            throw new ArrayIndexOutOfBoundsException(errorMessage);
        }
        int item = 0;
        try {
            item = array[index];
        } catch (Exception e) {
            String errorMessage = String.format("%s(%d): %s %d", "getItem", index, INVALID_ITEM_VALUE, array[index]);
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
            String errorMessage = String.format("%s(%d,%d): %s", "setItem", index, newValue, INVALID_ARRAY_INDEX);
            Logger.log(Level.ERROR, errorMessage);
            throw new ArrayIndexOutOfBoundsException(errorMessage);
        }
        try {
             array[index] = newValue;
            }
        catch ( Exception e) {
            String errorMessage = String.format("%s(%d,%d): %s", "setItem", index, newValue, INVALID_ITEM_VALUE);
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
        int result = 1;
        for (int i = 0; i < array.length; i++) {
            result = prime * result + (int) (array[i] ^ (array[i] >>> 32));
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass() || !(obj instanceof BaseArray))
            return false;

        BaseArray arr = (BaseArray) obj;
        return Arrays.equals(this.array, ((BaseArray) arr).array);
    }
}
