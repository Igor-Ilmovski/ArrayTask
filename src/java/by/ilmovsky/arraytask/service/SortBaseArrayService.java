package by.ilmovsky.arraytask.service;

import by.ilmovsky.arraytask.basearray.BaseArray;
import by.ilmovsky.arraytask.exception.BaseArrayException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class SortBaseArrayService {

    private final static String ERR_ARRAY_IS_NULL    = "Array is empty";
    private final static String ARRAY_BUBBLE_STARTS  = "bubble sort array starts...";
    private final static String ARRAY_BUBBLE_FINISH  = "bubble sort array finished";
    private final static String ARRAY_SORT_STARTS    = "selection sort array starts...";
    private final static String ARRAY_SORT_FINISH    = "selection sort array finished";

    static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(SortBaseArrayService.class);

    public BaseArray sortByBubble(BaseArray array) throws BaseArrayException {
        if (array.isEmpty()) {
            throw new IllegalArgumentException(ERR_ARRAY_IS_NULL);
        }
        Logger.log(Level.DEBUG, ARRAY_BUBBLE_STARTS);

        for (int i = 0; i < array.getBaseArrayLength() - 1; i++) {
            for (int j = 0; j < array.getBaseArrayLength() - i - 1; j++) {
                if (array.getItem(j) > array.getItem(j + 1)) {
                    int item = array.getItem(j);
                    array.setItem(j, array.getItem(j + 1));
                    array.setItem(j + 1, item);
                }
            }
        }
        Logger.log(Level.DEBUG, ARRAY_BUBBLE_FINISH);
        return array;
    }

    public BaseArray sortBySelection(BaseArray array) throws BaseArrayException {
        if (array.isEmpty()) {
            throw new IllegalArgumentException(ERR_ARRAY_IS_NULL);
        }
        Logger.log(Level.DEBUG, ARRAY_SORT_STARTS);

        for (int i = 0; i < array.getBaseArrayLength(); i++) {
            int item = array.getItem(i);
            int minIdx = i;
            for (int j = i+1; j < array.getBaseArrayLength(); j++) {
                if (array.getItem(j) < item) {
                    item = array.getItem(j);
                    minIdx = j;
                }
            }
            if (i != minIdx) {
                item = array.getItem(i);
                array.setItem(i, array.getItem(minIdx));
                array.setItem(minIdx, item);
            }
        }
        Logger.log(Level.DEBUG, ARRAY_SORT_FINISH);
        return array;
    }
}
