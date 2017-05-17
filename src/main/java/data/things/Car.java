package data.things;

import data.abstracts.Thing;

public class Car extends Thing{
    public String getInfoAboutMe() {
        return super.getInfoAboutMe() + horsePower + "\t|c";
    }

    private int horsePower;

    public int getHorsePower() {
        return horsePower;
    }

    public Car(long id, String title, double price, int horsePower) {
        super(id, title, price);
        this.horsePower = horsePower;
    }

    @Override
    public int getSpecific() {
        return horsePower;
    }

    @Override
    public void editMe(String title, double price, int spec) {
        super.editMe(title, price);
        horsePower = spec;
    }
}
