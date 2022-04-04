package testCode;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {

    /**
     * @param args the command line arguments
     */
    
    // states
    static double[][] state1 = {{1},{0}, {0}, {0}, {0}};
    static double[][] state2 = {{0},{1}, {0}, {0}, {0}};
    static double[][] state3 = {{0},{0}, {1}, {0}, {0}};
    static double[][] state4 = {{0},{0}, {0}, {1}, {0}};
    static double[][] state5 = {{0},{0}, {0}, {0}, {1}};
    static double[][] currentState = new double[5][1]; // checks hand
    
    // PROBABILITY IS DONE BY MARKOV CHAIN
    static double[][] markovChain = {   // Lower Triangular Matrix that is multiplied by the states to determine the probability at any point
        {0.092592593, 0, 0, 0, 0},
        {0.694444444, 0.555555556, 0, 0, 0},
        {0.192901235, 0.37037037, 0.694444444, 0, 0},
        {0.019290123, 0.069444444, 0.277777778, 0.833333333, 0},
        {0.000771605, 0.00462963, 0.027777778, 0.166666667, 1}
    }; // 5x5
    
    public static void main(String[] args) {
        
        ArrayList<Integer> diceHand = new ArrayList<>();
        //double[][] diceHand = new double[5][5];
        for (int i = 0; i < 5; i++) {
            
            diceHand.add((int)((Math.random() * (7-1)) + 1));
            
        }
        
        System.out.println(diceHand);
        
        double[][] array = chain(diceHand);
        System.out.println(diceFace(diceHand.toString())); // WRONG?
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        
        /*
        double[][] array = state1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.println(array[i][j]);
            }
            
        }
        */
        
        
        // **HOW STATES WORK:**
        // STATE 1: ABCDE (5 UNIQUE DICE, SMALL AND LARGE STRAIGHT ARE SUBSETS OF STATE 1)
        // STATE 2: AABCD OR AABCC (TWO OF A KIND, BUT NO THREE OF A KIND)
        // STATE 3: AAABC OR AAABB (THREE OF A KIND OR A FULL HOUSE)
        // STATE 4: AAAAB (FOUR OF A KIND)
        // STATE 5: AAAAA (YAHTZEE!)
        
        // **MARKOV CHAIN**
        // MULTIPLIES A BASE TRANSITION MATRIX BY A STATE
        // A STATE REFLECTS WHAT THE PLAYER'S HAND LOOKS LIKE AT ONE MOMENT
        // THE RESULT FROM THE MULTIPLICATION TELLS US HOW LIKELY SOMEONE IS TO MOVE FROM ONE STATE TO ANOTHER
        // MEANING, HOW LIKELY THEY ARE TO GET A BETTER HAND (ASSUMING THEY KEEP THE DICE THAT GOT THEM TO THAT STATE)
        
        
        
        // state 1
        double[][] state1Result = new double[5][1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                
                state1Result[i][0] = markovChain[i][0]*state1[0][0];
                
            }
        }
        
        // state 2
        double[][] state2Result = new double[5][1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                
                state2Result[i][0] = markovChain[i][1]*state2[1][0];
                
            }
        }
        
        // state 3
        double[][] state3Result = new double[5][1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                
                state3Result[i][0] = markovChain[i][2]*state3[2][0];
                
            }
        }
        
        // state 4
        double[][] state4Result = new double[5][1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                
                state4Result[i][0] = markovChain[i][3]*state4[3][0];
                
            }
        }
        
        // state 5
        double[][] state5Result = new double[5][1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                
                state5Result[i][0] = markovChain[i][4]*state5[4][0];
                
            }
        }
        
        // test
        /*
        for (int k = 0; k < 5; k++) {
            System.out.println(state1Result[k][0]*100);
        }*/
        
        
    }
    
    public static double[][] chain(ArrayList<Integer> dice) {
        
        double[][] state = findState(dice);
        
        // calculate probabilites
        double[][] probabilities = new double[5][1];
        //BigDecimal bd = new BigDecimal();
        
        for (int i = 0; i < markovChain.length; i++) {
            for (int k = 0; k < markovChain[i].length; k++) {
                probabilities[i][0] += markovChain[i][k]*state[k][0]*100.0;;
            }
        }
        
        return probabilities;
        
    }
    
    public static int diceFace(String hand) { // NEED TO CHECK FOR FULL HOUSE/STRAIGHTS!
        // PASS AN ARRAYLIST FIRST? COUNT FROM ARRAYLIST AND NOT FROM COLLECTIONS.FREQUENCY
        
        // broken, needs fix
        // have individual counts
        // compare each count at end
        
        String mostUsedChar = ""; 
        ArrayList<Integer> numCounts = new ArrayList<>();
        int count = 0;
        
        // number comparing arraylists
        ArrayList<Integer> val1 = new ArrayList<Integer>();
        val1.add(0); // redundant
        ArrayList<Integer> val2 = new ArrayList<Integer>();
        val2.add(0);
        ArrayList<Integer> val3 = new ArrayList<Integer>();
        val3.add(0);
        ArrayList<Integer> val4 = new ArrayList<Integer>();
        val4.add(0);
        ArrayList<Integer> val5 = new ArrayList<Integer>();
        val5.add(0);
        ArrayList<Integer> val6 = new ArrayList<Integer>();
        val6.add(0);
        
        String[] handLength = hand.split(", ");
        ArrayList<String> handLengthSorted = new ArrayList<>(java.util.Arrays.asList(handLength));
        
        
        // SORT THEN COMPARE?
        Collections.sort(handLengthSorted);
        System.out.println("aaaaa " + handLengthSorted);
        
        for (int i = 0; i < 6; i++) {
            numCounts.add(0);
        }
        
        Set<String> st = new HashSet<String>(handLengthSorted);
        int i = 0;
        for (String s : st) {
            if (handLengthSorted.get(i) == "1") {
                Integer num = Collections.frequency(handLengthSorted, s);
                if (val1.get(0) == num) {
                    continue;
                }
                
                //Integer value = Integer.valueOf(s);
                //val1.add(value);
                val1.set(0, num);
            } else if (handLengthSorted.get(i) == "2") {
                Integer num = Collections.frequency(handLengthSorted, s);
                if (val2.get(0) == num) {
                    continue;
                }
                //Integer value = Integer.valueOf(s);
                //val2.add(value);
                val2.set(0, num);
            } else if (handLengthSorted.get(i) == "3") {
                Integer num = Collections.frequency(handLengthSorted, s);
                if (val3.get(0) == num) {
                    continue;
                }
                //Integer value = Integer.valueOf(s);
                //val3.add(value);
                val3.add(num);
            } else if (handLengthSorted.get(i) == "4") {
                Integer num = Collections.frequency(handLengthSorted, s);
                if (val4.get(0) == num) {
                    continue;
                }
                //Integer value = Integer.valueOf(s);
                //val4.add(value);
                val4.add(num);
            } else if (handLengthSorted.get(i) == "5") {
                Integer num = Collections.frequency(handLengthSorted, s);
                if (val5.get(0) == num) {
                    continue;
                }
                //Integer value = Integer.valueOf(s);
                //val5.add(value);
                val5.add(num);
            } else if (handLengthSorted.get(i) == "6") {
                Integer num = Collections.frequency(handLengthSorted, s);
                if (val6.get(0) == num) {
                    continue;
                }
                //Integer value = Integer.valueOf(s);
                //val6.add(value);
                val6.add(num);
            }
            
            i++;
        }
        
        ArrayList<Integer> occurrences = new ArrayList<>();
        occurrences.add(val1.get(0));
        occurrences.add(val2.get(0));
        occurrences.add(val3.get(0));
        occurrences.add(val4.get(0));
        occurrences.add(val5.get(0));
        occurrences.add(val6.get(0));
        
        
        /*
        for (int k = 0; k < handLengthSorted.size(); k++) { // GOTTA ACCOUNT FOR FULL HOUSES/STRAIGHTS!
            for (int j = k+1; j < handLengthSorted.size(); j++) {
                if (occurrences.get(k) > occurrences.get(j)) {
                    
                }
            }
        }*/
        
        
        for (int k = 0; k < handLengthSorted.size(); k++) {
            int tempCount = 0;

            for (int j = k+1; j < handLengthSorted.size(); j++) {
                
                if (occurrences.get(k) == occurrences.get(j)) {
                    tempCount++;
                }
                
                if (tempCount > count) {
                    count = tempCount; 
                }
            }
        }
        
        return count;
    }
    
    public static double[][] findState(ArrayList<Integer> dice) {
        String hand = dice.toString();
        
        // determine state
        switch (diceFace(hand)) {
            case 1:
                currentState = state1;
                break;
            case 2:
                currentState = state2;
                break;
            case 3:
                currentState = state3;
                break;
            case 4:
                currentState = state4;
                break;
            case 5:
                currentState = state5;
                break;
            default:
                break;
        }
        
        return currentState;
    }
    
}
