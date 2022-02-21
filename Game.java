package YahtzeeProject;

import java.util.ArrayList;

public class Game {
    public boolean game;
    public ArrayList<Player> players; 
    
    public Game() {
        
    }
    
    public Player getWinner() {
        if (players.get(0).getTotal() > players.get(1).getTotal()) {
            // DISPLAY PLAYER 1 AS WINNER
        } else if (players.get(0).getTotal() < players.get(1).getTotal()) {
            // DISPLAY PLAYER 2 AS WINNER
        } else {
            // TIE
        }
    }
    
    public boolean isRealGame() {
        // sees if game is active? do we even need this?
    }
    
}
