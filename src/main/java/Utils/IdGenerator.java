package Utils;

public class IdGenerator {
    static private long currId = 0;
    static public long getMeNextId(){
        return currId++;
    }
    static public long getCurrentMaxID(){
        return currId;
    }
}
