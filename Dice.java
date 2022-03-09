public class Dice extends Player {
    private int diceValue;
    
    public Dice() {
        diceValue = (int)((Math.random() * (7-1)) + 1);
    }
    
    public int getDiceValue() {
        return diceValue;
    }
    
    public void setDiceValue() {
        diceValue = (int)((Math.random() * (7-1)) + 1);
    }
    
}
