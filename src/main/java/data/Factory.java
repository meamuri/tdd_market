package data;

import data.abstracts.Thing;
import data.things.Car;
import data.things.Guitar;
import data.things.Watch;

public class Factory {
    public static Thing ThingByRawString(String[] params){
        trimParams(params);

        Long id = Long.parseLong(params[0]);
        String title = params[1];
        double cost = Double.parseDouble(params[2]);
        int info = Integer.parseInt(params[3]);
        switch (params[4].charAt(0)){
            case 'w':
                return new Watch(id, title, cost, info);
            case 'c':
                return new Car(id, title, cost, info);
            case 'g':
                return new Guitar(id, title, cost, info);
            default:
                return null;
        }
    }

    private static void trimParams(String[] params) {
        for (int i = 0; i < params.length; ++i) {
            params[i] = params[i].trim();
        }
    }
}
