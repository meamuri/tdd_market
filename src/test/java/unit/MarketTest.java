package unit;

import data.KindOfItem;
import data.Market;
import data.abstracts.Thing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MarketTest {
    private Market market;

    @Before
    public void setup(){
        market = new Market();
        market.addItemToMarket("Bentley", 199216.99, 612, KindOfItem.CAR);
        market.addItemToMarket("RollsRoyce", 252016.99, 599, KindOfItem.CAR);
        market.addItemToMarket("BMW 760Li", 125216.99, 620, KindOfItem.CAR);
        market.addItemToMarket("Audi s8", 125216.99, 540, KindOfItem.CAR);
        market.addItemToMarket("Fender", 216.99, 5, KindOfItem.GUITAR);
        market.addItemToMarket("Longines", 1216.99, 1, KindOfItem.WATCH);
    }

    @Test
    public void userWantAddItem(){
        Assert.assertEquals(6, market.count());
    }

    @Test
    public void userWantGetItemById(){
        Thing th = market.getItemById(1);
        Assert.assertEquals("Bentley", th.getTitle());
        Assert.assertNull(market.getItemById(12512));
    }

    @Test
    public void userWantDeleteItemById(){
        Thing th = market.getItemById(5);
        Assert.assertEquals("Fender", th.getTitle());
        market.deleteItemById(5);
        Assert.assertNull(market.getItemById(5));
    }

    @Test
    public void userWantDeleteItemByIllegalId(){
        Boolean res = market.deleteItemById(512);
        Assert.assertFalse(res);
        Assert.assertTrue(market.deleteItemById(6));
    }

    @Test
    public void userWantEditItem() {
        Thing th = market.getItemById(2);
        Assert.assertTrue(th.getTitle().equals("RollsRoyce"));
        market.editById(2L, "RollsRoyce Ghost", 400000.4);
        th = market.getItemById(2);
        Assert.assertTrue(th.getTitle().equals("RollsRoyce Ghost"));
    }

    @Test
    public void userWantMoreSpecificEditing() {
        Thing th = market.getItemById(1);
        Assert.assertTrue(th.getTitle().equals("Bentley"));
        market.editById(1L, "Bentley Flying Spur", 600000.4, 612);
        th = market.getItemById(1);
        Assert.assertEquals(th.getSpecific(), 612);
    }

    @Test
    public void deleteByRangeFromTo(){
        Assert.assertNotNull(market.getItemById(2));
        Assert.assertEquals(6, market.count());
        market.deleteByRange(1, 4);
        Assert.assertNull(market.getItemById(2));
        Assert.assertEquals(2, market.count());
    }

    @Test
    public void deleteByArrayOfIds(){
        Assert.assertNotNull(market.getItemById(2));
        Assert.assertNotNull(market.getItemById(3));
        Assert.assertNotNull(market.getItemById(4));
        Assert.assertEquals(6, market.count());

        market.deleteByRange(new long[]{2, 4, 6});

        Assert.assertNull(market.getItemById(2));
        Assert.assertNotNull(market.getItemById(3));
        Assert.assertNull(market.getItemById(4));
        Assert.assertEquals(3, market.count());
    }

}
