package se.juneday.throwaway.androidpokerclient2;
import android.util.Log;

import java.util.*;

public class Player {

    public enum Position {
        SB,
        BB,
        UTG,
        UTG1,
        UTG2,
        MP,
        LJ,
        HJ,
        CO,
        BUT;
    }

    private String name;
    private double stack;
    protected double bet;
    public double prevBet;
    private Position position;
    private final Card card1;
    private final Card card2;
    private String pos;
    private String kort1;
    private String kort2;
    private String stakk;

/* //Dessa behövs inte nu då vi inte har tid att lösa problemen som uppstått.
    public Player(String name, double stack, Position position, Card card1, Card card2){
        this.name = name;
        this.stack = stack;
        this.position = position;
        this.bet = 0;
        this.prevBet = 0;
        this.card1 = card1;
        this.card2 = card2;
    }

    public Player(String name, double stack, Position position){
        this.name = name;
        this.stack = stack;
        this.bet = 0;
        this.prevBet = 0;
        this.position = position;
        card1=null;
        card2=null;
    } */
        // Tillfällig lösning för att förenkla byggandet av Player-objekt i EditPlayersActivity
    public Player(String name, String stakk, String pos, String kort1, String kort2){
        this.name = name;
        this.stakk = stakk;
        this.pos = pos;
        this.kort1 = kort1;
        this.kort2 = kort2;
        this.bet = 0;
        this.prevBet = 0;
        this.card1 = null;
        this.card2 = null;
    }

    public String name() { return name; }
    public double stack() { return stack; }
    public double bet() { return bet; }
    public double prevBet() { return prevBet; }
    public void resetPrevBet() {
        this.prevBet = 0;
    }

    public Position position() { return position; }
    public Card card1() { return card1; }
    public Card card2() { return card2; }

    public boolean card1Folded(){
        return !(card1==null);
    }
    public boolean card2Folded(){
        return !(card2==null);
    }

    @Override
    public String toString(){
        return "(" + pos + ")" + name + " " + stakk + "kr " + " | " + kort1 + " | " + kort2;
    }

    public double pay(double amount) {
        this.stack -= amount;
        return amount;
    }
}
