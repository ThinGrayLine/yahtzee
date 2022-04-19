public class Dice extends Player {
    private int diceValue;
    private boolean selected;
    
    public Dice() {
        diceValue = (int)((Math.random() * (7-1)) + 1);
    }
    
    public int getDiceValue() {
        return diceValue;
    }
    
    public void setDiceValue() {
        diceValue = (int)((Math.random() * (7-1)) + 1);
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean value) {
        selected = value;
    }
    
}
