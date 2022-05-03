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
    public static boolean availableOnes = true;       
    public static boolean availableTwos = true;
    public static boolean availableThrees = true; 
    public static boolean availableFours = true; 
    public static boolean availableFives = true; 
    public static boolean availableSixes = true; 
    public static boolean availableSmallStraight = true; 
    public static boolean availableLargeStraight = true; 
    public static boolean availableThreeOfAKind = true; 
    public static boolean availableFourOfAKind = true; 
    public static boolean availableFullHouse = true; 
    public static boolean availableChance = true;
    public static boolean availableYahtzee = true; 
    public ArrayList<Dice> list = new ArrayList<Dice>();
    
    
    private Probability probs;
    
    public Roll() {
        
    }
    
    public Roll(ArrayList<Dice> hand) { // INITIALIZES ROLL ON TURN 1
        for (int i = 0; i < 5; i++) {
            list.add(hand.get(i));
        }
        
    } 
    
    public void reroll() { // USE FOR TURNS 2 AND 3 FOR ROLL BUTTONS
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelected() == true) {
                list.get(i).setDiceValue();
            }
        }
        
    }
    
    public boolean isOnes(ArrayList<Dice> list) {
        if (availableOnes == false) {
            return false;
        }
        
        /*
        ArrayList<Dice> hand = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            System.out.print(list.get(i).getDiceValue() + " ");
            hand.add(list.get(i));
        }
        */
        
        for (int i = 0; i < 5; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 1) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isTwos(ArrayList<Dice> list) {
        if (availableTwos == false) {
            return false;
        }
        
        for (int i = 0; i < 5; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 2) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isThrees(ArrayList<Dice> list) {
        if (availableThrees == false) {
            return false;
        }
        
        ArrayList<Dice> hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hand.add(list.get(i));
        }
        
        for (int i = 0; i < 5; i++) {
            int num = hand.get(i).getDiceValue();
            if (num == 3) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isFours(ArrayList<Dice> list) {
        if (availableFours == false) {
            return false;
        }
        
        ArrayList<Dice> hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hand.add(list.get(i));
        }
        
        for (int i = 0; i < 5; i++) {
            int num = hand.get(i).getDiceValue();
            if (num == 4) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isFives(ArrayList<Dice> list) {
        if (availableFives == false) {
            return false;
        }
        
        ArrayList<Dice> hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hand.add(list.get(i));
        }
        
        for (int i = 0; i < 5; i++) {
            int num = hand.get(i).getDiceValue();
            if (num == 5) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isSixes(ArrayList<Dice> list) {
        if (availableSixes == false) {
            return false;
        }
        
        ArrayList<Dice> hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hand.add(list.get(i));
        }
        
        for (int i = 0; i < 5; i++) {
            int num = hand.get(i).getDiceValue();
            if (num == 6) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isThreeOfAKind(ArrayList<Dice> list) {
        if (availableThreeOfAKind == false) {
            return false;
        }
        
        
        ArrayList<Integer> hand = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            hand.add(list.get(i).getDiceValue());
        }
        
        // counters
        ArrayList<Integer> counters = new ArrayList<>();
        counters.add(0); // 1
        counters.add(0); // 2
        counters.add(0); // 3
        counters.add(0); // 4
        counters.add(0); // 5
        counters.add(0); // 6
        
        for (int j = 0; j < hand.size(); j++) {
            for (int i = 1; i < 7; i++) {
                if (hand.get(j) == i) {
                    counters.set(i-1, counters.get(i-1) + 1); 
                } 
            }
        }

        if (counters.contains(3) || counters.contains(4) || counters.contains(5)) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean isFourOfAKind(ArrayList<Dice> list) {
        if (availableFourOfAKind == false) {
            return false;
        }
        
        ArrayList<Integer> hand = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            hand.add(list.get(i).getDiceValue());
        }
        
        // counters
        ArrayList<Integer> counters = new ArrayList<>();
        counters.add(0); // 1
        counters.add(0); // 2
        counters.add(0); // 3
        counters.add(0); // 4
        counters.add(0); // 5
        counters.add(0); // 6
        
        
        
        for (int j = 0; j < hand.size(); j++) {
            for (int i = 1; i < 7; i++) {
                if (hand.get(j) == i) {
                    counters.set(i-1, counters.get(i-1) + 1); 
                } 
            }
        }
        
        if (counters.contains(4) || counters.contains(5)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isSmallStraight(ArrayList<Dice> list) {
        if (availableSmallStraight == false) {
            return false;
        }
        
        // see if it's 1,2,3,4 or 2,3,4,5 or 3,4,5,6 
        // reorder in increasing order
        ArrayList<Integer> hand = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            hand.add(list.get(i).getDiceValue());
        }
        
        if (hand.contains(1) && hand.contains(2) && hand.contains(3) && hand.contains(4)) {
            return true;
        } else if (hand.contains(2) && hand.contains(3) && hand.contains(4) && hand.contains(5)) {
            return true;
        } else if (hand.contains(3) && hand.contains(4) && hand.contains(5) && hand.contains(6)) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean isLargeStraight(ArrayList<Dice> list) {
        if (availableLargeStraight == false) {
            return false;
        }
        
        // see if it's 1,2,3,4,5 or 2,3,4,5,6  
        
        ArrayList<Integer> hand = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            hand.add(list.get(i).getDiceValue());
        }
        
        if (hand.contains(1) && hand.contains(2) && hand.contains(3) && hand.contains(4) && hand.contains(5)) {
            return true;
        } else if (hand.contains(2) && hand.contains(3) && hand.contains(4) && hand.contains(5) && hand.contains(6)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isFullHouse(ArrayList<Dice> list) {
        // should always be available unless chosen
        if (availableFullHouse == false) {
            return false;
        }
        
        // Dice to Integer
        ArrayList<Integer> hand = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            hand.add(list.get(i).getDiceValue());
        }
        
        // creates a counter of all dice
        ArrayList<Integer> counters = new ArrayList<>();
        counters.add(0); // 1
        counters.add(0); // 2
        counters.add(0); // 3
        counters.add(0); // 4
        counters.add(0); // 5
        counters.add(0); // 6
        
        // sets the counts
        for (int j = 0; j < hand.size(); j++) {
            for (int i = 1; i < 7; i++) {
                if (hand.get(j) == i) {
                    counters.set(i-1, counters.get(i-1) + 1); 
                } 
            }
        }
        
        // checks to see if count has a 2 and a 3 --> which is a full house
        if (counters.contains(2) && counters.contains(3)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isChance(ArrayList<Dice> list) {
        // should always be available unless chosen
        if (availableChance == false) {
            return false;
        }
        
        return true;
    }
    
    public boolean isYahtzee(ArrayList<Dice> list) {
        if (availableYahtzee == false) {
            return false;
        }
        
        ArrayList<Integer> hand = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            hand.add(list.get(i).getDiceValue());
        }
        
        for (int i = 1; i < 5; i++) {
            if (list.get(0).getDiceValue() != list.get(i).getDiceValue()) {
                return false;
            }
        }
        return true;
        /*
        if (probs.diceFace(hand) == 5) {
            return true;
        } else {
            return false;
        }*/
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
        
        availableOnes = false;
    }
    
    public void chooseTwos(ArrayList<Dice> list) {
        int numOfTwo = 0;
        
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 1) {
                numOfTwo += 1;
            }
        }
        
        twosScore = numOfTwo*TWOS;
        
        availableTwos = false;
    }
    
    public void chooseThrees(ArrayList<Dice> list) {
        int numOfThree = 0;
        
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 1) {
                numOfThree += 1;
            }
        }
        
        threesScore = numOfThree*THREES;
        
        availableThrees = false;
    }
    
    public void chooseFours(ArrayList<Dice> list) {
        int numOfFours = 0;
        
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 1) {
                numOfFours += 1;
            }
        }
        
        foursScore = numOfFours*FOURS;
        
        availableFours = false;
    }
    
    public void chooseFives(ArrayList<Dice> list) {
        int numOfFives = 0;
        
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 1) {
                numOfFives += 1;
            }
        }
        
        fivesScore = numOfFives*FIVES;
        
        availableFives = false;
    }
    
    public void chooseSixes(ArrayList<Dice> list) {
        int numOfSixes = 0;
        
        for (int i = 0; i < 6; i++) {
            int num = list.get(i).getDiceValue();
            if (num == 1) {
                numOfSixes += 1;
            }
        }
        
        sixesScore = numOfSixes*SIXES;
        
        availableSixes = false;
    }
    
    public void chooseSmallStraight(ArrayList<Dice> list) {
        smallStraightScore = 30; // SET THIS IN ATTRIBUTES? **SET IT TO ZERO IN ATTRIBUTES??**
        availableSmallStraight = false;
    }
    
    public void chooseLargeStraight(ArrayList<Dice> list) {
        largeStraightScore = 40; // SET THIS IN ATTRIBUTES?
        availableLargeStraight = false;
    }
    
    public void chooseThreeOfAKind(ArrayList<Dice> list) {
        for (int i = 0; i < list.size(); i++) {
		threeOfAKindScore += list.get(i).getDiceValue();
	}
        
        availableThreeOfAKind = false;
    }
    
    public void chooseFourOfAKind(ArrayList<Dice> list) {
        for (int i = 0; i < list.size(); i++) {
		fourOfAKindScore += list.get(i).getDiceValue();
	}
        
        availableFourOfAKind = false;
    }
    
    public void chooseFullHouse(ArrayList<Dice> list) {
        fullHouseScore = 25; // SET THIS IN ATTRIBUTES?
        availableFullHouse = false;
    }
    
    public void chooseChance(ArrayList<Dice> list) {
        for (int i = 0; i < list.size(); i++) {
		chanceScore += list.get(i).getDiceValue();
	}
        
        availableChance = false;
    }
    
    public void chooseYahtzee(ArrayList<Dice> list) {
        yahtzeeScore = 50; // SET THIS IN ATTRIBUTES?
        availableYahtzee = false;
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

    public boolean isAvailableOnes() {
        isOnes(list);
        return availableOnes;
    }

    public boolean isAvailableTwos() {
        isTwos(list);
        return availableTwos;
    }

    public boolean isAvailableThrees() {
        isThrees(list);
        return availableThrees;
    }

    public boolean isAvailableFours() {
        isFours(list);
        return availableFours;
    }

    public boolean isAvailableFives() {
        isFives(list);
        return availableFives;
    }

    public boolean isAvailableSixes() {
        isSixes(list);
        return availableSixes;
    }

    public boolean isAvailableSmallStraight() {
        isSmallStraight(list);
        return availableSmallStraight;
    }

    public boolean isAvailableLargeStraight() {
        isLargeStraight(list);
        return availableLargeStraight;
    }

    public boolean isAvailableThreeOfAKind() {
        isThreeOfAKind(list);
        return availableThreeOfAKind;
    }

    public boolean isAvailableFourOfAKind() {
        isFourOfAKind(list);
        return availableFourOfAKind;
    }

    public boolean isAvailableFullHouse() {
        isFullHouse(list);
        return availableFullHouse;
    }

    public boolean isAvailableChance() {
        isChance(list);
        return availableChance;
    }

    public boolean isAvailableYahtzee() {
        isYahtzee(list);
        return availableYahtzee;
    }

    public static void setAvailableOnes(boolean availableOnes) {
        Roll.availableOnes = availableOnes;
    }

    public static void setAvailableTwos(boolean availableTwos) {
        Roll.availableTwos = availableTwos;
    }

    public static void setAvailableThrees(boolean availableThrees) {
        Roll.availableThrees = availableThrees;
    }

    public static void setAvailableFours(boolean availableFours) {
        Roll.availableFours = availableFours;
    }

    public static void setAvailableFives(boolean availableFives) {
        Roll.availableFives = availableFives;
    }

    public static void setAvailableSixes(boolean availableSixes) {
        Roll.availableSixes = availableSixes;
    }

    public static void setAvailableSmallStraight(boolean availableSmallStraight) {
        Roll.availableSmallStraight = availableSmallStraight;
    }

    public static void setAvailableLargeStraight(boolean availableLargeStraight) {
        Roll.availableLargeStraight = availableLargeStraight;
    }

    public static void setAvailableThreeOfAKind(boolean availableThreeOfAKind) {
        Roll.availableThreeOfAKind = availableThreeOfAKind;
    }

    public static void setAvailableFourOfAKind(boolean availableFourOfAKind) {
        Roll.availableFourOfAKind = availableFourOfAKind;
    }

    public static void setAvailableFullHouse(boolean availableFullHouse) {
        Roll.availableFullHouse = availableFullHouse;
    }

    public static void setAvailableChance(boolean availableChance) {
        Roll.availableChance = availableChance;
    }

    public static void setAvailableYahtzee(boolean availableYahtzee) {
        Roll.availableYahtzee = availableYahtzee;
    }

    public void setOnesScore(int onesScore) {
        this.onesScore = onesScore;
    }

    public void setTwosScore(int twosScore) {
        this.twosScore = twosScore;
    }

    public void setThreesScore(int threesScore) {
        this.threesScore = threesScore;
    }

    public void setFoursScore(int foursScore) {
        this.foursScore = foursScore;
    }

    public void setFivesScore(int fivesScore) {
        this.fivesScore = fivesScore;
    }

    public void setSixesScore(int sixesScore) {
        this.sixesScore = sixesScore;
    }

    public void setSmallStraightScore(int smallStraightScore) {
        this.smallStraightScore = smallStraightScore;
    }

    public void setLargeStraightScore(int largeStraightScore) {
        this.largeStraightScore = largeStraightScore;
    }

    public void setThreeOfAKindScore(int threeOfAKindScore) {
        this.threeOfAKindScore = threeOfAKindScore;
    }

    public void setFourOfAKindScore(int fourOfAKindScore) {
        this.fourOfAKindScore = fourOfAKindScore;
    }

    public void setFullHouseScore(int fullHouseScore) {
        this.fullHouseScore = fullHouseScore;
    }

    public void setChanceScore(int chanceScore) {
        this.chanceScore = chanceScore;
    }

    public void setYahtzeeScore(int yahtzeeScore) {
        this.yahtzeeScore = yahtzeeScore;
    }
    
}
