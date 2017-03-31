package data;


import data.abstracts.Thing;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Market{
    private LinkedList<Thing> things;
    private LinkedHashSet<Long> ids;

    public Market() {
        things = new LinkedList<Thing>();
        ids = new LinkedHashSet<Long>();
    }

    public boolean add(Thing thing) {
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

    public Thing getItemById(int i) {
        for (Thing thing: things){
            if (i == thing.getId())
                return thing;
        }
        return null;
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
}
