package data.things;

import data.abstracts.Thing;

public class Guitar extends Thing{
    private int stringCount;

    public String getInfoAboutMe() {
        return super.getInfoAboutMe();
    }

    public Guitar(int id, String title, double price, int stringCount) {
        super(id, title, price);
        this.stringCount = stringCount;
    }

    public int getStringCount() {
        return stringCount;
    }

}
