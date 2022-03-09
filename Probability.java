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
