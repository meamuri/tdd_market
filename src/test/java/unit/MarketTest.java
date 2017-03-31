package unit;

import data.Market;
import data.abstracts.Thing;
import data.things.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MarketTest {
    private Market market;

    @Before
    public void setup(){
        market = new Market();
    }

    @Test
    public void userWantAddItem(){
        Thing thing = new Car(1, "bentley", 125216.99, 612);
        market.add(thing);
        Assert.assertEquals(1, market.count());
    }

}
