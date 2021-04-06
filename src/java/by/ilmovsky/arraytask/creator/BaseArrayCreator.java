package by.ilmovsky.arraytask.creator;

import by.ilmovsky.arraytask.basearray.BaseArray;
import by.ilmovsky.arraytask.exception.BaseArrayException;
import org.apache.logging.log4j.LogManager;

public class BaseArrayCreator {

    private final static String ERR_ARRAY_IS_NULL           = "Array is empty";

    static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger();

    public static BaseArray create(int[] intValues) throws BaseArrayException {
        if (intValues == null) {
            throw new BaseArrayException(ERR_ARRAY_IS_NULL);
        }
        BaseArray baseArray = new BaseArray(intValues);
        return baseArray;
    }
}
