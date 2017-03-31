package unit;

import data.Market;
import data.abstracts.Thing;
import data.things.Car;
import data.things.Guitar;
import data.things.Watch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MarketTest {
    private Market market;

    @Before
    public void setup(){
        market = new Market();
        market.add(new Car(1, "Bentley", 199216.99, 612));
        market.add(new Car(2, "RollsRoyce", 252016.99, 599));
        market.add(new Car(3, "BMW 760Li", 125216.99, 620));
        market.add(new Car(4, "Audi s8", 125216.99, 540));
        market.add(new Guitar(5, "Fender", 216.99, 5));
        market.add(new Watch(6, "Longines", 1216.99, 1));
    }

    @Test
    public void userWantAddItem(){
        Market emptyMarket = new Market();
        emptyMarket.add(new Car(1, "bentley", 125216.99, 612));
        Assert.assertEquals(1, emptyMarket.count());
    }

    @Test
    public void userWantGetItemById(){
        market.add(new Car(10, "Mercedes", 2300000,600));
        Thing th = market.getItemById(10);
        Assert.assertEquals("Mercedes", th.getTitle());
        th = market.getItemById(1);
        Assert.assertEquals("Bentley", th.getTitle());
        Assert.assertNull(market.getItemById(12512));
    }

    @Test
    public void userWantDeleteItemById(){
        Assert.assertEquals("Fender", market.getItemById(5).getTitle());
        market.deleteItemById(5);
        Assert.assertNull(market.getItemById(5));
    }

    @Test
    public void userWantAddToItemsWithSameIds(){
        Boolean result = market.add(new Car(1, "Bentley FlyingSpur", 229216.99, 630));
        Assert.assertTrue(!result);
    }

    @Test
    public void userWantDeleteItemByIllegalId(){
        Boolean res = market.deleteItemById(512);
        Assert.assertTrue(!res);
        Assert.assertTrue(market.deleteItemById(5));
    }
}
