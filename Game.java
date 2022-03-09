package YahtzeeProject;

import java.util.ArrayList;

public class Game {
    public boolean game;
    public ArrayList<Player> players;
    private Player winner;
    
    public Game() {
        
    }
    
    public Player isWinner(ArrayList<Player> players) {
        if (players.get(0).getTotal() > players.get(1).getTotal()) {
            // DISPLAY PLAYER 1 AS WINNER
            winner = players.get(0);
            
        } else if (players.get(0).getTotal() < players.get(1).getTotal()) {
            // DISPLAY PLAYER 2 AS WINNER
            winner = players.get(1);
            
        } else {
            // TIE
            // display tie?
            return null;
        }
        return winner; // do winner.getName();
    }
    
    //public Player
    /*
    public Player getWinner() {
        return players.getWinner();
    }*/
    
    public boolean isRealGame() {
        // sees if game is active? do we even need this?
    }
    
}
