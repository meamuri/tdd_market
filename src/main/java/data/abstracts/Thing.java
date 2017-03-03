package data.abstracts;

public abstract class Thing implements Item {
    private long id;
    private String title;
    private double price;
    public Thing(int id, String title, double price){
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public double getPrice() {
        return price;
    }
}
