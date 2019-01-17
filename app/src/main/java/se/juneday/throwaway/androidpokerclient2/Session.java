package se.juneday.throwaway.androidpokerclient2;

import java.util.List;
import java.util.ArrayList;

public class Session {
    private static Session instance;
    public HandInfo handInfo;
    public List<Player> players;
    public String noOfPlayers;
    public List<String> communityCards;
    public List<List<Street>> streets;

    private Session() { players = new ArrayList<>();
                        communityCards = new ArrayList<>();
                        streets = new ArrayList<>();
    }


    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }
}
