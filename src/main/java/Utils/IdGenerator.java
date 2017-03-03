package Utils;

public class IdGenerator {
    static private long currId = 0;
    static public long getMeNextId(){
        return currId++;
    }
}
