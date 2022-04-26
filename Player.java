package YahtzeeProject;

import java.util.ArrayList;

public class Player extends Game { // doesn't extend game anymore?
    //private boolean isWinner;
    //private Player winningPlayer;
    private int total;
    private String playerName;
    private int upperSectionTotal;
    private int lowerSectionTotal;
    public ArrayList<Dice> hand;
    private Roll roll;
    
    public Player() {
        total = 0;
        //ArrayList<Dice> hand = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Dice die = new Dice();
            hand.add(die);
        }
        
    }

    public ArrayList<Dice> getHand() {
        return hand;
    }
    
    private void setPlayerName(String name) {
        playerName = name;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    
    public int getTotal() {
        return total;
    } 
    
    public void setTotal() {
        total = upperSectionTotal + lowerSectionTotal;
    }
    
    public int getUpperSectionTotal() {
        return upperSectionTotal;
    }
    
    public void setUpperSectionTotal() {
        //upperSectionTotal CALL onesScore, twosScore... 
        if (roll.isAvailableOnes() == false && roll.isAvailableTwos() == false && roll.isAvailableThrees() == false && roll.isAvailableFours() == false && roll.isAvailableFives() == false && roll.isAvailableSixes() == false) {
		upperSectionTotal = roll.getOnes() + roll.getTwos() + roll.getThrees() + roll.getFours() + roll.getFives() + roll.getSixes();
	} 
        
    }

    public int getLowerSectionTotal() {
        return lowerSectionTotal;
    }

    public void setLowerSectionTotal() {
        if (roll.isAvailableSmallStraight() == false && roll.isAvailableLargeStraight() == false && roll.isAvailableThreeOfAKind() == false && roll.isAvailableFourOfAKind() == false && roll.isAvailableFullHouse() == false && roll.isAvailableChance() == false && roll.isAvailableYahtzee() == false) {
		lowerSectionTotal = roll.getSmallStraight() + roll.getLargeStraight() + roll.getThreeOfAKind() + roll.getFourOfAKind() + roll.getFullHouse() + roll.getChance() + roll.getYahtzee();
	} 
    }
    
    
    
}

