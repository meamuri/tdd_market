package data.things;

import data.abstracts.Thing;

public class Car extends Thing{
    public String getInfoAboutMe() {
        return super.getInfoAboutMe();
    }

    private int horsePower;

    public int getHorsePower() {
        return horsePower;
    }

    public Car(int id, String title, double price, int horsePower) {
        super(id, title, price);
        this.horsePower = horsePower;
    }


}
