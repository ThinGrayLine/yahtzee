package YahtzeeProject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
//import java.text.DecimalFormat;

/**
 *
 * @author gyenc
 */
public class Probability {
    private double probability;
    // have list of all base probabilities in attributes?
    
    
    public Probability() {
        
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
        
    }
    
    public double probFourOfAKind(ArrayList<Dice> list) {
        
    }
    
    public double probSmallStraight(ArrayList<Dice> list) {
        
    }
    
    public double probLargeStraight(ArrayList<Dice> list) {
        
    }
    
    public double probChance(ArrayList<Dice> list) {
        
    }
    
    public double probYahtzee(ArrayList<Dice> list) {
        
    }
}
