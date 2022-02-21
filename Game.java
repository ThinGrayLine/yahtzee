package YahtzeeProject;

import java.util.ArrayList;

public class Game {
    public boolean game;
    public ArrayList<Player> players; 
    
    public Game() {
        
    }
    
    public Player setWinner() {
        if (players.get(0).getTotal() > players.get(1).getTotal()) {
            // DISPLAY PLAYER 1 AS WINNER
            players.get(0).setWinningPlayer();
            
        } else if (players.get(0).getTotal() < players.get(1).getTotal()) {
            // DISPLAY PLAYER 2 AS WINNER
            players.get(1).setWinningPlayer();
            
        } else {
            // TIE
            players.tie();
            
        }
    }
    
    public Player getWinner() {
        return players.getWinner();
    }
    
    public boolean isRealGame() {
        // sees if game is active? do we even need this?
    }
    
}
