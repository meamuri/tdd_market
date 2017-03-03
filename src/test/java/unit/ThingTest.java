package unit;


import data.abstracts.Thing;
import data.things.Car;
import org.junit.Assert;
import org.junit.Test;

public class ThingTest {

    @Test
    public void myItemCanGetMeName(){
        Thing th = new Car(1, "Mercedes", 152.124, 452);
        Assert.assertEquals(152.124, th.getPrice(), 0.001);
    }
}
