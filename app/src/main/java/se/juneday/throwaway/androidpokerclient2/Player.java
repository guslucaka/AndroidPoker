package se.juneday.throwaway.androidpokerclient2;
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
    }

    public Player(String name, String stack, String pos, String kort1, String kort2){
        this.name = name;
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
        return "(" + position + ")" + name + " " + stack + " " + " | " + card1 + " | " + card2;
    }

    public double pay(double amount) {
        this.stack -= amount;
        return amount;
    }
}
