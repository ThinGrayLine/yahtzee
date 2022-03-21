package markovChain;

public class Main {

    public static void main(String[] args) {
        // states
        double[][] state1 = {{1},{0}, {0}, {0}, {0}};
        double[][] state2 = {{0},{1}, {0}, {0}, {0}};
        double[][] state3 = {{0},{0}, {1}, {0}, {0}};
        double[][] state4 = {{0},{0}, {0}, {1}, {0}};
        double[][] state5 = {{0},{0}, {0}, {0}, {1}};
        double[][] currentState; // checks hand
        // PROBABILITY IS DONE BY MARKOV CHAIN
        double[][] markovChain = {   // Lower Triangular Matrix that is multiplied by the states to determine the probability at any point
            {0.092592593, 0, 0, 0, 0},
            {0.694444444, 0.555555556, 0, 0, 0},
            {0.192901235, 0.37037037, 0.694444444, 0, 0},
            {0.019290123, 0.069444444, 0.277777778, 0.833333333, 0},
            {0.000771605, 0.00462963, 0.027777778, 0.166666667, 1}
        };
        
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
        for (int k = 0; k < 5; k++) {
            System.out.println(state5Result[k][0]*100);
        }
        
        
    }
    
}
