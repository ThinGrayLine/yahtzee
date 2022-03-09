package YahtzeeProject;

import java.util.ArrayList;

public class Player extends Game {
    //private boolean isWinner;
    //private Player winningPlayer;
    private int total;
    private String playerName;
    private int upperSectionTotal;
    private ArrayList<Dice> hand;
    
    public Player() {
        total = 0;
        // give hand?
    }
    
    // SET NAME METHOD;
    
    
    public int getTotal() {
        return total;
    } 
    
    public void setTotal(int num) {
        total += num;
    }
    
    public int getUpperSectionTotal() {
        return upperSectionTotal;
    }
    
    public void setUpperSectionTotal() {
        //upperSectionTotal CALL onesScore, twosScore... 
        if (isOnes == false && isTwos == false...) {
		upperSectionTotal = getOnes() + getTwos() + getThrees() + getFours() + getFives() + getSixes();
	}
    }
    
}
