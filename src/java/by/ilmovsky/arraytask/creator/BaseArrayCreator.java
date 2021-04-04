package by.ilmovsky.arraytask.creator;

import by.ilmovsky.arraytask.basearray.BaseArray;
import org.apache.logging.log4j.LogManager;

public class BaseArrayCreator {

    static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger();

    public BaseArray create(int[] intValues) {
        BaseArray baseArray = new BaseArray(intValues);
        return baseArray;
    }
}
