package by.ilmovsky.arraytask.service;

import by.ilmovsky.arraytask.basearray.BaseArray;
import by.ilmovsky.arraytask.exception.BaseArrayException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;


public class SearchBaseArrayService {

    private final static String ARRAY_MIN_ITEM_FOUND = "min array item is";
    private final static String ARRAY_MAX_ITEM_FOUND = "max array item is";
    private final static String ARRAY_ITEMS_REPLACED = "array elements replaced";
    private final static String ERR_ARRAY_IS_NULL    = "array is empty";
    private final static String ARRAY_TOTAL_FOUND    = "array total value";
    private final static String ARRAY_AVERAGE_FOUND  = "array total average";
    private final static String ARRAY_AVERAGE_EMPTY  = "array is empty, average is zero";
    private final static String ARRAY_POSITIVE_FOUND = "array total positive summary";
    private final static String ARRAY_NEGATIVE_FOUND = "array total negative summary";
    private final static String ERR_INVALID_INDEX    = "invalid array index";


    static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(SearchBaseArrayService.class);

    public int searchBaseArrayMin(BaseArray array) throws BaseArrayException {
        if (array.isEmpty()) {
            throw new IllegalArgumentException(ERR_ARRAY_IS_NULL + array.getClass());
        }
        int min;
        try {
            min = array.getItem(0);
            for (int i = 0; i < array.getBaseArrayLength(); i++) {
                if (min > array.getItem(i)) {
                    min = array.getItem(i);
                }
            }
            Logger.log(Level.DEBUG, String.format("%s(): %s %d", "searchBaseArrayMin", ARRAY_MIN_ITEM_FOUND, min));
        } catch ( Exception e) {
            String errorMessage = String.format("%s(): %s", "searchBaseArrayMin", ERR_INVALID_INDEX);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
        return min;
    }

    public int searchBaseArrayMax(BaseArray array) throws BaseArrayException {
        if (array.isEmpty()) {
            throw new IllegalArgumentException(ERR_ARRAY_IS_NULL + array.getClass());
        }
        int max;
        try {
            max = array.getItem(0);
            for (int i = 0; i <array.getBaseArrayLength(); i++) {
                int item = array.getItem(i);
                if (max < item) {
                    max = item;
                }
            }
        } catch (Exception e) {
            String errorMessage = String.format("%s(): %s", "searchBaseArrayMax", ERR_INVALID_INDEX);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }

        Logger.log(Level.DEBUG, String.format("%s(): %s %d", "searchBaseArrayMax", ARRAY_MAX_ITEM_FOUND, max));
        return max;
    }

    public int replaceItem(BaseArray array, int oldValue, int newValue) throws BaseArrayException {
        if (array.isEmpty()) {
            throw new IllegalArgumentException(ERR_ARRAY_IS_NULL + array.getClass());
        }

        int replacedCounter = 0;
        for (int i = 0; i <array.getBaseArrayLength(); i++) {
            if (array.getItem(i) == oldValue)
            try {
                array.setItem(i, newValue);
                replacedCounter++;
            } catch ( BaseArrayException e) {
                String errorMessage = String.format("%s(): setItem(%d,%d) exception","replaceItem", i, newValue);
                Logger.log(Level.ERROR, errorMessage);
                throw new BaseArrayException(errorMessage);
            }
        }
        Logger.log(Level.DEBUG, String.format("%s(%d,%d) %s %d times",
                "replaceItem", oldValue, newValue, ARRAY_ITEMS_REPLACED, replacedCounter));
        return replacedCounter;
    }

    public double getBaseArraySum(BaseArray array) throws BaseArrayException {
        if (array.isEmpty()) {
            throw new IllegalArgumentException(ERR_ARRAY_IS_NULL + array.getClass());
        }
        double summ = 0;
        int arrLength = array.getBaseArrayLength();
        for (int i = 0; i < arrLength; i++) {
            summ = summ + array.getItem(i);
        }
        Logger.log(Level.DEBUG, String.format("%s(): %s %f", "getBaseArraySum", ARRAY_TOTAL_FOUND, summ));
        return summ;
    }

    public double getBaseArrayAverage(BaseArray array) throws BaseArrayException {
        if (array.isEmpty()) {
            throw new IllegalArgumentException(ERR_ARRAY_IS_NULL + array.getClass());
        }
        double average = 0;
        int arrLength = array.getBaseArrayLength();
        if (arrLength > 0) {
            average = getBaseArraySum(array) / array.getBaseArrayLength();
            Logger.log(Level.DEBUG, String.format("%s(): %s %f", "getBaseArrayAverage", ARRAY_AVERAGE_FOUND, average));
        } else {
            Logger.log(Level.WARN, String.format("%s(): %s", "getBaseArrayAverage", ARRAY_AVERAGE_EMPTY));
        }
        return average;
    }

    public int getBaseArrayPositives(BaseArray array) throws BaseArrayException {
        if (array.isEmpty()) {
            throw new IllegalArgumentException(ERR_ARRAY_IS_NULL + array.getClass());
        }

        int summ = 0;
        try {
            for (int i = 0; i < array.getBaseArrayLength(); i++) {
                int item = array.getItem(i);
                if (item >0) {
                    summ = summ + item;
                }
            }
        } catch ( Exception e) {
            String errorMessage = String.format("%s(): %s", "getBaseArrayPositives", ERR_INVALID_INDEX);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
        Logger.log(Level.DEBUG, String.format("%s(): %s %d", "getBaseArrayPositives", ARRAY_POSITIVE_FOUND, summ));
        return summ;
    }

    public int getBaseArrayNegatives(BaseArray array) throws BaseArrayException {
        if (array.isEmpty()) {
            throw new IllegalArgumentException(ERR_ARRAY_IS_NULL + array.getClass());
        }
        int summ = 0;
        try {
            for (int i = 0; i < array.getBaseArrayLength(); i++) {
                int item = array.getItem(i);
                if (item <0) {
                    summ = summ + item;
                }
            }
        } catch ( Exception e) {
            String errorMessage = String.format("%s(): %s", "getBaseArrayNegatives", ERR_INVALID_INDEX);
            Logger.log(Level.ERROR, errorMessage);
            throw new BaseArrayException(errorMessage);
        }
        Logger.log(Level.DEBUG, String.format("%s(): %s %d", "getBaseArrayNegatives", ARRAY_NEGATIVE_FOUND, summ));
        return summ;
    }
}