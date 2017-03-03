package data.things;

import data.abstracts.Thing;

public class Car extends Thing{
    private int horsePower;
    public void infoAboutMe() {

    }

    public int getHorsePower() {
        return horsePower;
    }

    public Car(int id, String title, double price, int horsePower) {
        super(id, title, price);
        this.horsePower = horsePower;
    }
}
