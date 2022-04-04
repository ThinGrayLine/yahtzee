package YahtzeeProject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
//import java.text.DecimalFormat;

/**
 *
 * @author gyenc
 */
public class Probability extends Roll {
    private double probability;
    // have list of all base probabilities in attributes?
    private String tOAKProb = "1200/7776";
    private String fOAKProb = "150/7776";
    private String sSProb = "960/7776";
    private String lSProb = "240/7776";
    private String fHProb = "300/7776";
    private String chanceProb = ""; // 100% technically but remove the lower section options
    private String yahtzeeProb = "6/7776";
    
    // turn counter, instantiate a public turn counter in roll?
    public int turn = 1;
    
    // states
    private double[][] state1 = {{1},{0}, {0}, {0}, {0}};
    private double[][] state2 = {{0},{1}, {0}, {0}, {0}};
    private double[][] state3 = {{0},{0}, {1}, {0}, {0}};
    private double[][] state4 = {{0},{0}, {0}, {1}, {0}};
    private double[][] state5 = {{0},{0}, {0}, {0}, {1}};
    private double[][] currentState; // checks hand
    // PROBABILITY IS DONE BY MARKOV CHAIN
    private double[][] markovChain = {   // Lower Triangular Matrix that is multiplied by the states to determine the probability at any point
        {0.092592593, 0, 0, 0, 0},
        {0.694444444, 0.555555556, 0, 0, 0},
        {0.192901235, 0.37037037, 0.694444444, 0, 0},
        {0.019290123, 0.069444444, 0.277777778, 0.833333333, 0},
        {0.000771605, 0.00462963, 0.027777778, 0.166666667, 1}
    };
    
    private double[][] markovChainSquared = {   // Lower Triangular Matrix that is multiplied by the states to determine the probability at any point
        {0.008573388, 0, 0, 0, 0},
        {0.450102881, 0.308641976, 0, 0, 0},
        {0.409022062, 0.462962963, 0.482253086, 0, 0},
        {0.11967021, 0.199331275, 0.424382716, 0.694444444, 0},
        {0.009952275, 0.019418725, 0.054783951, 0.050925926, 1}
    };
    
    private double[][] markovChainCubed = {   // Lower Triangular Matrix that is multiplied by the states to determine the probability at any point
        {0.000793832, 0, 0, 0, 0},
        {0.256010898, 0.171467764, 0, 0, 0},
        {0.452401686, 0.4358139, 0.334897976, 0, 0},
        {0.24476494, 0.316143689, 0.487611454, 0.578703703, 0},
        {0.026728597, 0.039244685, 0.079968279, 0.07021605, 1}
    };

    // markov chain for small straights
    private double[][] SSmarkovChain = {
        {0.092592593, 0, 0, 0}, // probability of staying in state 1 when in state 1 is 9.2592593%, no need to square, it stays the same
        {0.694444444, 0.555555556, 0, 0},
        {0.192901235, 0.37037037, 0.694444444, 0},
        {0.019290123, 0.069444444, 0.277777778, 0.833333333},
        {0.000771605, 0.00462963, 0.027777778, 0.166666667}
    };
    // state 1: 1 dice in small straight, but not 2 (ex: 33333, 11666, 22266 -> since you need a yahtzee or [1,5] [1,6] [2,6] since no SS occur with these)
    // state 2: 2 dice but not 3 (ex: 12555, 35111)
    // state 3: 3 dice but not all 4 (ex: 12366, 24511)
    // state 4: all 4
    
    
    // markov chain for large straights
    private double[][] LSmarkovChain = {   
        {0.012345679, 0, 0, 0, 0},
        {0.200617284, 0.125, 0, 0, 0},
        {0.509259259, 0.513888889, 0.444444444, 0, 0},
        {0.259259259, 0.333333333, 0.5, 0.833333333, 0},
        {0.018518519, 0.027777778, 0.055555556, 0.166666667, 1}
    };
    
    private double[][] LSmarkovChainSquared = {   
        {0.000152416, 0, 0, 0, 0},
        {0.027553917, 0.015625, 0, 0, 0},
        {0.335719593, 0.292631173, 0.197530864, 0, 0},
        {0.540752171, 0.576388889, 0.638888889, 0.694444444, 0},
        {0.095821903, 0.115354939, 0.163580248, 0.305555556, 1}
    };

    private double[][] LSmarkovChainCubed = {   
        {0.00000188168, 0, 0, 0, 0},
        {0.003474817, 0.001953125, 0, 0, 0},
        {0.163445979, 0.138087813, 0.087791495, 0, 0},
        {0.62771076, 0.631847993, 0.631172839, 0.578703703, 0},
        {0.205366563, 0.228111069, 0.281035666, 0.421296297, 1}
    };
    
    public Probability() {
        
    }
    
    // need states -> check hand 
    
    public double[][] markovChain() {
        // if statements for states

        // state 1
        double[][] state1Result = new double[5][1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                
                state1Result[i][0] = markovChain[i][0]*state1[0][0];
                
            }
        }
        
        return state1Result;
        
    }
    
    public void currentState(ArrayList<Dice> hand) {
        // checks hand and sets a state -> implement markov chain
        for ()
                markovChain();
    }
    
    // write in fractional form!
    public double factor(double probability) {
        BigDecimal bd = new BigDecimal(probability).setScale(2, RoundingMode.HALF_UP);
        double factored = bd.doubleValue();
        return factored;
    }
    
    public String fractional() {
        return /*PROBABILITY*/"/7776";
    }
    
    public double probThreeOfAKind(ArrayList<Dice> list) {
        // call findState method
        
        double[][] probabilities = new double[5][1];
        if (turn == 1) {
            for (int i = 0; i < markovChain.length; i++) {
                for (int k = 0; k < markovChain[i].length; k++) {
                    probabilities[i][0] += markovChain[i][k]*currentState[k][0]*100.0;;
                }
            }
        } else if (turn == 2) { 
            for (int i = 0; i < markovChain.length; i++) {
                for (int k = 0; k < markovChain[i].length; k++) {
                    probabilities[i][0] += markovChainSquared[i][k]*currentState[k][0]*100.0;;
                }
            }
        } else if (turn == 3) {
            for (int i = 0; i < markovChain.length; i++) {
                for (int k = 0; k < markovChain[i].length; k++) {
                    probabilities[i][0] += markovChainCubed[i][k]*currentState[k][0]*100.0;;
                }
            }
        }
        
        return probabilities[2][0];
        
    }
    
    public double probFourOfAKind(ArrayList<Dice> list) {
        // call findState method
        
        double[][] probabilities = new double[5][1];
        if (turn == 1) {
            for (int i = 0; i < markovChain.length; i++) {
                for (int k = 0; k < markovChain[i].length; k++) {
                    probabilities[i][0] += markovChain[i][k]*currentState[k][0]*100.0;;
                }
            }
        } else if (turn == 2) { 
            for (int i = 0; i < markovChain.length; i++) {
                for (int k = 0; k < markovChain[i].length; k++) {
                    probabilities[i][0] += markovChainSquared[i][k]*currentState[k][0]*100.0;;
                }
            }
        } else if (turn == 3) {
            for (int i = 0; i < markovChain.length; i++) {
                for (int k = 0; k < markovChain[i].length; k++) {
                    probabilities[i][0] += markovChainCubed[i][k]*currentState[k][0]*100.0;;
                }
            }
        }
        
        return probabilities[3][0];
    }
    
    public double probSmallStraight(ArrayList<Dice> list) {
        // call findState method
        
        double[][] probabilities = new double[5][1];
        if (turn == 1) {
            for (int i = 0; i < SSmarkovChain.length; i++) {
                for (int k = 0; k < SSmarkovChain[i].length; k++) {
                    probabilities[i][0] += SSmarkovChain[i][k]*currentState[k][0]*100.0;;
                }
            }
        } else if (turn == 2) { 
            for (int i = 0; i < SSmarkovChain.length; i++) {
                for (int k = 0; k < SSmarkovChain[i].length; k++) {
                    probabilities[i][0] += SSmarkovChain[i][k]*currentState[k][0]*100.0;;
                }
            }
        } else if (turn == 3) {
            for (int i = 0; i < SSmarkovChain.length; i++) {
                for (int k = 0; k < SSmarkovChain[i].length; k++) {
                    probabilities[i][0] += SSmarkovChain[i][k]*currentState[k][0]*100.0;;
                }
            }
        }
        
        // find way to get state value
        return probabilities[][0];
    }
    
    public double probLargeStraight(ArrayList<Dice> list) {
        // call findState method
        
        double[][] probabilities = new double[5][1];
        if (turn == 1) {
            for (int i = 0; i < LSmarkovChain.length; i++) {
                for (int k = 0; k < LSmarkovChain[i].length; k++) {
                    probabilities[i][0] += LSmarkovChain[i][k]*currentState[k][0]*100.0;;
                }
            }
        } else if (turn == 2) { 
            for (int i = 0; i < LSmarkovChain.length; i++) {
                for (int k = 0; k < LSmarkovChain[i].length; k++) {
                    probabilities[i][0] += LSmarkovChainSquared[i][k]*currentState[k][0]*100.0;;
                }
            }
        } else if (turn == 3) {
            for (int i = 0; i < LSmarkovChain.length; i++) {
                for (int k = 0; k < LSmarkovChain[i].length; k++) {
                    probabilities[i][0] += LSmarkovChainCubed[i][k]*currentState[k][0]*100.0;;
                }
            }
        }
        
        // find way to get state value
        return probabilities[][0];
    }
    
    // do we need this?
    public double probChance(ArrayList<Dice> list) {
        
    }
    
    public double probYahtzee(ArrayList<Dice> list) {
        // call findState method
        
        double[][] probabilities = new double[5][1];
        if (turn == 1) {
            for (int i = 0; i < markovChain.length; i++) {
                for (int k = 0; k < markovChain[i].length; k++) {
                    probabilities[i][0] += markovChain[i][k]*currentState[k][0]*100.0;;
                }
            }
        } else if (turn == 2) { 
            for (int i = 0; i < markovChain.length; i++) {
                for (int k = 0; k < markovChain[i].length; k++) {
                    probabilities[i][0] += markovChainSquared[i][k]*currentState[k][0]*100.0;;
                }
            }
        } else if (turn == 3) {
            for (int i = 0; i < markovChain.length; i++) {
                for (int k = 0; k < markovChain[i].length; k++) {
                    probabilities[i][0] += markovChainCubed[i][k]*currentState[k][0]*100.0;;
                }
            }
        }
        
        return probabilities[4][0];
    }
}
