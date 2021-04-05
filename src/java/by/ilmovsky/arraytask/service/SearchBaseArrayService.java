package by.ilmovsky.arraytask.service;

import by.ilmovsky.arraytask.basearray.BaseArray;
import by.ilmovsky.arraytask.exception.BaseArrayException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;


public class SearchBaseArrayService {

    private final static String MIN_ARRAY_ITEM_FOUND = "min array item is";
    private final static String MAX_ARRAY_ITEM_FOUND = "max array item is";
    private final static String ARRAY_ITEMS_REPLACED = "array elements replaced";
    private final static String ERR_ARRAY_IS_NULL    = "Array is empty";
    private final static String ARRAY_TOTAL_FOUND    = "array total value";
    private final static String ARRAY_AVERAGE_FOUND  = "array total average";
    private final static String ARRAY_POSITIVE_FOUND = "array total positive summary";
    private final static String ARRAY_NEGATIVE_FOUND = "array total negative summary";


    static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(SearchBaseArrayService.class);

    public int searchBaseArrayMin(BaseArray array) {
        if (array.isEmpty()) {
            throw new BaseArrayException(ERR_ARRAY_IS_NULL);
        }
        int min = array.getItem(0);
        for (int i=0; i <array.getBaseArrayLength(); i++) {
            if (min > array.getItem(0)) {
                min = array.getItem(0);
            }
        }
        Logger.log(Level.DEBUG, String.format("%s(): %s %d", "searchBaseArrayMin", MIN_ARRAY_ITEM_FOUND, min));
        return min;
    }

    public int searchBaseArrayMax(BaseArray array) {
        if (array.isEmpty()) {
            throw new BaseArrayException(ERR_ARRAY_IS_NULL);
        }
        int max = array.getItem(0);
        for (int i=0; i <array.getBaseArrayLength(); i++) {
            if (max < array.getItem(0)) {
                max = array.getItem(0);
            }
        }
        Logger.log(Level.DEBUG, String.format("%s(): %s %d", "searchBaseArrayMax", MAX_ARRAY_ITEM_FOUND, max));
        return max;
    }

    public int replaceItem(BaseArray array, int oldValue, int newValue) throws BaseArrayException {
        if (array.isEmpty()) {
            throw new BaseArrayException(ERR_ARRAY_IS_NULL);
        }
        int item;
        int replacedCount = 0;
        for (int i=0; i <array.getBaseArrayLength(); i++) {
            item = array.getItem(i);
            if (item == oldValue)
            try {
                array.setItem(i, newValue);
                replacedCount++;
            } catch ( BaseArrayException e) {
                String errorMessage = String.format("%s(): setItem(%d,%d) exception","replaceItem", i, newValue);
                Logger.log(Level.ERROR, String.format(errorMessage);
                throw new BaseArrayException(errorMessage);
            }
        }
        Logger.log(Level.DEBUG, String.format("%s(%d,%d) %s %d times",
                "replaceItem", oldValue, newValue, ARRAY_ITEMS_REPLACED, replacedCount));
        return replacedCount;
    }

    public double getBaseArraySumm(BaseArray array) {
        if (array.isEmpty()) {
            throw new BaseArrayException(ERR_ARRAY_IS_NULL);
        }
        double summ = 0;

        for (int i = 0; i < array.getBaseArrayLength(); i++) {
            summ = summ + array.getItem(i);
        }
        Logger.log(Level.DEBUG, String.format("%s(): %s %f", "getBaseArraySumm", ARRAY_TOTAL_FOUND, summ));
        return summ;
    }


    public double getBaseArrayAverage(BaseArray array) {
        if (array.isEmpty()) {
            throw new BaseArrayException(ERR_ARRAY_IS_NULL);
        }

        double average = getBaseArraySumm(array) / array.getBaseArrayLength();
        Logger.log(Level.DEBUG, String.format("%s(): %s %f", "getBaseArrayAverage", ARRAY_AVERAGE_FOUND, average));
        return average;
    }

    public int getBaseArrayPositive(BaseArray array) {
        if (array.isEmpty()) {
            throw new BaseArrayException(ERR_ARRAY_IS_NULL);
        }
        int summ = 0;
        int item;

        for (int i = 0; i < array.getBaseArrayLength(); i++) {
            item = array.getItem(i);
            if (item >0) {
                summ = summ + item;
            }
        }
        Logger.log(Level.DEBUG, String.format("%s(): %s %f", "getBaseArrayPositive", ARRAY_POSITIVE_FOUND, summ));
        return summ;
    }

    public int getBaseArrayNegative(BaseArray array) {
        if (array.isEmpty()) {
            throw new BaseArrayException(ERR_ARRAY_IS_NULL);
        }
        int summ = 0;
        int item;

        for (int i = 0; i < array.getBaseArrayLength(); i++) {
            item = array.getItem(i);
            if (item <0) {
                summ = summ + item;
            }
        }
        Logger.log(Level.DEBUG, String.format("%s(): %s %f", "getBaseArrayNegative", ARRAY_NEGATIVE_FOUND, summ));
        return summ;
    }
}
