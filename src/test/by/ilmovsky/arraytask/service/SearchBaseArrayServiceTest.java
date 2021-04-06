package by.ilmovsky.arraytask.service;

import by.ilmovsky.arraytask.basearray.BaseArray;
import by.ilmovsky.arraytask.exception.BaseArrayException;
import org.testng.annotations.Test;
import by.ilmovsky.arraytask.creator.BaseArrayCreator;
import by.ilmovsky.arraytask.service.SearchBaseArrayService;
import org.testng.Assert;
import org.testng.internal.ExpectedExceptionsHolder;

public class SearchBaseArrayServiceTest {

    BaseArrayCreator baseArrayCreator = new BaseArrayCreator();
    private SearchBaseArrayService searchService = new SearchBaseArrayService();

    @Test
    public void testCreate() throws BaseArrayException {
        int[] data = {1, 7, 0, 9};
        BaseArray expected = new BaseArray(data);
        BaseArray actual = BaseArrayCreator.create(data);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSearchBaseArrayMin() throws BaseArrayException {
        int[] data = {1, 7, 0, 9, -2, 60};
        int expected = 1;
        int actual   = searchService.searchBaseArrayMin(baseArrayCreator.create(data));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSearchBaseArrayMax() throws BaseArrayException {
        int[] data = {1, 7, 0, 9, -2, 60};
        int expected = 60;
        int actual   = searchService.searchBaseArrayMax(baseArrayCreator.create(data));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReplaceItem() throws BaseArrayException {
        int[] data = {1, 7, 0, 9, -2, 60};
        int expected = 100;
        int actual   = searchService.replaceItem(baseArrayCreator.create(data), 7, 100);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetBaseArraySum() throws BaseArrayException {
        int[] data = {10, 17, 0, -4, 2, 2};
        int expected = 27;
        int actual   = searchService.getBaseArraySum(baseArrayCreator.create(data));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetBaseArrayAverage() throws BaseArrayException {
        int[] data = {10, 17, 0, -4, 2, 2};
        double expected = 4.5;
        double actual   = searchService.getBaseArrayAverage(baseArrayCreator.create(data));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetBaseArrayPositives() throws BaseArrayException {
        int[] data = {10, 17, 0, -4, 2, 2};
        int expected = 31;
        int actual   = searchService.getBaseArrayPositives(baseArrayCreator.create(data));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetBaseArrayNegatives() throws BaseArrayException {
        int[] data = {10, 17, 0, -4, 2, 2};
        int expected = -4;
        int actual   = searchService.getBaseArrayNegatives(baseArrayCreator.create(data));
        Assert.assertEquals(expected, actual);
    }
}