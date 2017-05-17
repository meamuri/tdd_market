package unit;

import application.Dialog;
import application.enums.DeleteOptions;
import application.enums.MenuItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DialogTest {
    private Dialog dialog;
    @Before
    public void setup(){
        dialog = new Dialog();
    }

    @Test
    public void userWantExit(){
        Assert.assertTrue(dialog.getUserAction("0") == MenuItem.EXIT);
    }

    @Test
    public void userInputIncorrectDigit(){
        Assert.assertTrue(dialog.getUserAction("14") == MenuItem.NOT_IN_RANGE);
    }

    @Test
    public void userMakeSomeActionsInDialog(){
        Assert.assertTrue(dialog.getUserAction("0") == MenuItem.EXIT);
        Assert.assertTrue(dialog.getUserAction("3") == MenuItem.ADD);
        Assert.assertTrue(dialog.getUserAction("-124") == MenuItem.ERROR_INPUT);
        Assert.assertTrue(dialog.getUserAction("124") == MenuItem.NOT_IN_RANGE);
    }

    @Test
    public void userWantDeleteRangeItems(){
        List<String> arr = new ArrayList<>();

        DeleteOptions opts = dialog.deleteCheckOptions("13-15", arr);
        Assert.assertEquals(DeleteOptions.RANGE, opts);
        Assert.assertEquals(2, arr.size());
        Assert.assertEquals(13, Integer.parseInt(arr.get(0)));
        Assert.assertEquals(15, Integer.parseInt(arr.get(1)));

        opts = dialog.deleteCheckOptions("  14  -   24   ", arr);
        Assert.assertEquals(DeleteOptions.RANGE, opts);
        Assert.assertEquals(2, arr.size());
        Assert.assertEquals(14, Integer.parseInt(arr.get(0)));
        Assert.assertEquals(24, Integer.parseInt(arr.get(1)));

        opts = dialog.deleteCheckOptions("1, 4, 6, 125", arr);
        Assert.assertEquals(DeleteOptions.DISCRETELY, opts);
        Assert.assertEquals(4, arr.size());
        Assert.assertEquals(1, Integer.parseInt(arr.get(0)));
        Assert.assertEquals(6, Integer.parseInt(arr.get(2)));


        opts = dialog.deleteCheckOptions("  1  ,   4,  6,   125, 12   ,  2 , 4", arr);
        Assert.assertEquals(DeleteOptions.DISCRETELY, opts);
        Assert.assertEquals(7, arr.size());
        Assert.assertEquals(4, Integer.parseInt(arr.get(6)));
        Assert.assertEquals(2, Integer.parseInt(arr.get(5)));
    }
}
