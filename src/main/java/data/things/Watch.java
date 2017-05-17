package data.things;

import data.abstracts.Thing;

public class Watch extends Thing {
    private int clockFaceCount;

    public String getInfoAboutMe() {
        return super.getInfoAboutMe() + clockFaceCount + "\t|w";
    }

    public Watch(long id, String title, double price, int clockFaceCount) {
        super(id, title, price);
        this.clockFaceCount = clockFaceCount;
    }

    public int getClockFaceCount() {
        return clockFaceCount;
    }

    @Override
    public int getSpecific() {
        return clockFaceCount;
    }

    @Override
    public void editMe(String title, double price, int spec) {
        super.editMe(title, price);
        clockFaceCount = spec;
    }
}
