package data.things;

import data.KindOfItem;
import data.abstracts.Thing;

public class Guitar extends Thing{
    private int stringCount;

    public String getInfoAboutMe() {
        return super.getInfoAboutMe() + stringCount + "\t|g";
    }

    public Guitar(long id, String title, double price, int stringCount) {
        super(id, title, price);
        this.stringCount = stringCount;
    }

    public int getStringCount() {
        return stringCount;
    }

    @Override
    public int getSpecific() {
        return stringCount;
    }

    @Override
    public void editMe(String title, double price, int spec) {
        super.editMe(title, price);
        stringCount = spec;
    }

    @Override
    public KindOfItem getMyKind() {
        return KindOfItem.GUITAR;
    }
}
