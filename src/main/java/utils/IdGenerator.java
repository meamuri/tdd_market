package utils;

public class IdGenerator {
    private long currId = 0;
    public long getNextId(){
        return ++currId;
    }
    public long getCurrentID(){
        return currId;
    }
    public void setZeroIdAndStartAgain() {
        currId = 0;
    }
}
