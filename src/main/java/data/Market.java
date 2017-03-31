package data;


import data.abstracts.Thing;

import java.util.LinkedList;

public class Market{
    LinkedList<Thing> things;

    public Market() {
        things = new LinkedList<Thing>();
    }

    public void add(Thing thing) {
        things.add(thing);
    }

    public int count() {
        return things.size();
    }
}
