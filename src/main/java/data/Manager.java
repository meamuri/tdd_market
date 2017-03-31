package data;

public class Manager {
    private Market market;

    public Manager(Market market) {
        this.market = market;
    }

    public Manager() {
        market = new Market();
    }

}
