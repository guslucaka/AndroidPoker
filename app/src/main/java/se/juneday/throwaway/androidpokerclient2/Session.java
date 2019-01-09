package se.juneday.throwaway.androidpokerclient2;

import java.util.List;
import java.util.ArrayList;

public class Session {
    private static Session instance;
    public HandInfo handInfo;
    public List<Player> players;

    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }
}
