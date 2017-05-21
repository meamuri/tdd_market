package data.abstracts;

import data.KindOfItem;

public abstract class Thing implements Item {
    private long id;
    private String title;
    private double price;
    public Thing(long id, String title, double price){
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    public KindOfItem getMyKind() {
        return KindOfItem.UNKNOWN;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInfoAboutMe() {
        return id + "\t| " + title + "\t\t| " + price + "\t\t| ";
    }

    public double getPrice() {
        return price;
    }

    public void editMe(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public abstract void editMe(String title, double price, int spec);

    public abstract int getSpecific();
}
