package se.juneday.throwaway.androidpokerclient2;

import java.util.List;
import java.util.ArrayList;

public class Session {
    private static Session instance;
    public HandInfo handInfo;
    public List<Player> players;

    private Session() {
        players = new ArrayList<>();
    }

    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }
}
