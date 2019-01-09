package se.juneday.throwaway.androidpokerclient2;

public class HandInfo {

    private final String location;
    private final String gametype;
    private static int smallBlind;
    private static int bigBlind;

    public HandInfo(String location, String gametype, int smallBlind, int bigBlind) {
        this.location = location;
        this.gametype = gametype;
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
    }

    public String location() { return location; }
    public String gametype() { return gametype; }
    public int smallBlind() { return smallBlind; }
    public int bigBlind() { return bigBlind; }

    @Override
    public String toString(){
        return location + " | " + gametype + ", " + smallBlind + "/" + bigBlind;
    }

    public String printHandInfo(){
        return location + " | " + gametype + ", " + smallBlind + "/" + bigBlind;
    }

}
