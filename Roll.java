package YahtzeeProject;

import java.util.ArrayList;

/**
 *
 * @author gyenc
 */
public class Roll extends Dice {
    // make scores 0 initially?
    // MAKE ALL BASE SCORES STATIC!?
    private final int ONES = 1;
    private final int TWOS = 2;
    private final int THREES = 3;
    private final int FOURS = 4;
    private final int FIVES = 5;
    private final int SIXES = 6;
    //private final int THREE_OF_A_KIND; // remove
    //private final int FOUR_OF_A_KIND; // remove      
    private final int SMALL_STRAIGHT = 30;       
    private final int LARGESTRAIGHT = 40;   
    private final int FULL_HOUSE = 25;       
    private final int YAHTZEE = 50;
    private int onesScore;       
    private int twosScore;
    private int threesScore; 
    private int foursScore; 
    private int fivesScore; 
    private int sixesScore; 
    private int smallStraightScore; 
    private int largeStraightScore; 
    private int threeOfAKindScore; 
    private int fourOfAKindScore; 
    private int fullHouseScore; 
    private int chanceScore;
    private int yahtzeeScore;
    private boolean availableOnes;       
    private boolean availableTwos;
    private boolean availableThrees; 
    private boolean availableFours; 
    private boolean availableFives; 
    private boolean availableSixes; 
    private boolean availableSmallStraight; 
    private boolean availableLargeStraight; 
    private boolean availableThreeOfAKind; 
    private boolean availableFourOfAKind; 
    private boolean availableFullHouse; 
    private boolean availableChance;
    private boolean availableYahtzee; 
    
    public Roll() {
        // inherit arraylist of dice --> should do it cause hand is public
    } 
    
    public boolean checkRolls() { // check this
        // determine whether the boolean attributes are true or not. Make one for each?
        
    }
    
    public boolean isOnes(ArrayList<Dice> list) {
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 1) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isTwos(ArrayList<Dice> list) {
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 2) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isThrees(ArrayList<Dice> list) {
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 3) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isFours(ArrayList<Dice> list) {
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 4) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isFives(ArrayList<Dice> list) {
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 5) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isSixes(ArrayList<Dice> list) {
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 6) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isThreeOfAKind(ArrayList<Dice> list) {
        
    }
    
    public boolean isFourOfAKind(ArrayList<Dice> list) {
        
    }
    
    public boolean isSmallStraight(ArrayList<Dice> list) {
        // see if it's 1,2,3,4 or 2,3,4,5 or 3,4,5,6 
        // reorder in increasing order
    }
    
    public boolean isLargeStraight(ArrayList<Dice> list) {
        // see if it's 1,2,3,4,5 or 2,3,4,5,6  
        // reorder in increasing order
    }
    
    public boolean isChance(ArrayList<Dice> list) {
        // should always be available unless chosen
    }
    
    public boolean isYahtzee(ArrayList<Dice> list) {
        int[] num = new int[list.size()];
        
        for (int i = 0; i < 6; i++) {
            int number = list.get(i).getDiceValue();
            num[i] = number;
        }
        
        
        
        
        if (num[0] == num[1] == num[2] == num[3] == num[4] == num[5]) {
            return true;
        } else {
            return false;
        }
    }
    
    public void chooseOnes(ArrayList<Dice> list) {
        int numOfOne = 0;
        
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 1) {
                numOfOne += 1;
            }
        }
        
        onesScore = numOfOne*ONES;
        
    }
    
    public void chooseTwos(ArrayList<Dice> list) {
        twosScore = TWOS*list.getSize();
    }
    
    public void chooseThrees(ArrayList<Dice> list) {
        threesScore = THREES*list.getSize();
    }
    
    public void chooseFours(ArrayList<Dice> list) {
        foursScore = FOURS*list.getSize();
    }
    
    public void chooseFives(ArrayList<Dice> list) {
        fivesScore = FIVES*list.getSize();
    }
    
    public void chooseSixes(ArrayList<Dice> list) {
        sixesScore = SIXES*list.getSize();
    }
    
    public void chooseSmallStraight(ArrayList<Dice> list) {
        smallStraightScore = 30; // SET THIS IN ATTRIBUTES? **SET IT TO ZERO IN ATTRIBUTES??**
    }
    
    public void chooseLargeStraight(ArrayList<Dice> list) {
        largeStraightScore = 40; // SET THIS IN ATTRIBUTES?
    }
    
    public void chooseThreeOfAKind(ArrayList<Dice> list) {
        for (int i = 0; i < list.getSize(); i++) {
		threeOfAKindScore += list.get(i);
	}
    }
    
    public void chooseFourOfAKind(ArrayList<Dice> list) {
        for (int i = 0; i < list.getSize(); i++) {
		fourOfAKindScore += list.get(i);
	}
    }
    
    public void chooseFullHouse(ArrayList<Dice> list) {
        fullHouseScore = 25; // SET THIS IN ATTRIBUTES?
    }
    
    public void chooseChance(ArrayList<Dice> list) {
        for (int i = 0; i < list.getSize(); i++) {
		chanceScore += list.get(i);
	}
    }
    
    public void chooseYahtzee(ArrayList<Dice> list) {
        yahtzeeScore = 50; // SET THIS IN ATTRIBUTES?
    }
    
    public int getOnes() {
        return onesScore;
    }
    
    public int getTwos() {
        return twosScore;
    }
    
    public int getThrees() {
        return threesScore;
    }
    
    public int getFours() {
        return foursScore;
    }
    
    public int getFives() {
        return fivesScore;
    }
    
    public int getSixes() {
        return sixesScore;
    }
    
    public int getSmallStraight() {
        return smallStraightScore;
    }
    
    public int getLargeStraight() {
        return largeStraightScore;
    }
    
    public int getThreeOfAKind() {
        return threeOfAKindScore;
    }
    
    public int getFourOfAKind() {
        return fourOfAKindScore;
    }
    
    public int getChance() {
        return chanceScore;
    }
    
    public int getFullHouse() {
        return fullHouseScore;
    }
    
    public int getYahtzee() {
        return yahtzeeScore;
    }
    
    // getProbability method;
    
}
