package data;


import data.abstracts.Thing;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Market{
    LinkedList<Thing> things;
    LinkedHashSet<Integer> ids;

    public Market() {
        things = new LinkedList<Thing>();
    }

    public void add(Thing thing) {
        things.add(thing);
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

    public boolean deleteItemById(int i) {
        int k = 0;
        for (Thing thing: things) {
            if (i == thing.getId()) {
                things.remove(k);
                return true;
            }
            ++k;
        }
        return false;
    }
}
