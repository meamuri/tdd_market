package unit;

import data.Manager;
import data.Market;
import data.things.Car;
import data.things.Guitar;
import data.things.Watch;
import org.junit.Before;

public class ManagerTest {
    private Manager manager;

    @Before
    public void setup() {
        Market market = new Market();

        market.add(new Car(1, "Bentley", 199216.99, 612));
        market.add(new Car(2, "RollsRoyce", 252016.99, 599));
        market.add(new Car(3, "BMW 760Li", 125216.99, 620));
        market.add(new Car(4, "Audi s8", 125216.99, 540));
        market.add(new Guitar(5, "Fender", 216.99, 5));
        market.add(new Watch(6, "Longines", 1216.99, 1));

        manager = new Manager(market);
    }



}
