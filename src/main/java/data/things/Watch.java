package data.things;

import data.abstracts.Thing;

public class Watch extends Thing {
    private int ClockFaceCount;
    public void infoAboutMe() {

    }

    public Watch(int id, String title, double price, int clockFaceCount) {
        super(id, title, price);
        ClockFaceCount = clockFaceCount;
    }

    public int getClockFaceCount() {
        return ClockFaceCount;
    }

}
