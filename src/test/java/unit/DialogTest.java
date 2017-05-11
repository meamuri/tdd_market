package unit;

import application.Dialog;
import application.MenuItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

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

}
