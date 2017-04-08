package data;

import Utils.IdGenerator;
import data.abstracts.Thing;
import data.things.Car;
import data.things.Guitar;
import data.things.Watch;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Market{
    private LinkedList<Thing> things;
    private LinkedHashSet<Long> ids;
    private IdGenerator generator;

    public Market() {
        things = new LinkedList<Thing>();
        ids = new LinkedHashSet<Long>();
        generator = new IdGenerator();
    }

    public boolean addItemToMarket(String title, double price, int param, KindOfItem kind) {
        Thing th = null;
        long id = generator.getNextId();
        switch (kind){
            case CAR:
                th = new Car(id, title, price, param);
                break;
            case GUITAR:
                th = new Guitar(id, title, price, param);
                break;
            case WATCH:
                th = new Watch(id, title, price, param);
                break;

            case UNKNOWN:
                return false;
        }
        addInternalItem(th);
        return true;
    }

    private void addInternalItem(Thing th){
        ids.add(th.getId());
        things.add(th);
    }

    private boolean add(Thing thing) {
        long idOfThing = thing.getId();
        if (ids.contains(idOfThing))
            return false;

        things.add(thing);
        ids.add(idOfThing);
        return true;
    }

    public int count() {
        return things.size();
    }

    public Thing getItemById(long i) {
        if (!ids.contains(i))
            return null;

        Thing res = null;
        for (Thing thing: things){
            if (i == thing.getId()) {
                res = thing;
                break;
            }
        }

        return res;
    }

    public boolean deleteItemById(long id) {
        // если множество сейчас не содержит такой элемент, возвращаем false -- не удалось удалить
        if (!ids.contains(id))
            return false;

        int k = 0;
        for (Thing thing: things) {
            if (id == thing.getId()) {
                break;
            }
            ++k;
        }

        things.remove(k);
        ids.remove(id);
        return true;
    }

    static public KindOfItem getTypeOfItem(int userInput){
        switch (userInput){
            case 1:
                return KindOfItem.CAR;
            case 2:
                return KindOfItem.WATCH;
            case 3:
                return KindOfItem.GUITAR;
            default:
                return KindOfItem.UNKNOWN;
        }
    }

}
