package YahtzeeProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author gyenc
 */
public class PlayController implements Runnable {
    // attributes
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    // game details
    private Player player;
    private boolean wasComboSelected = false; // HAVE THIS IN ALL COMBO METHODS, SET TRUE AND CHECK TURN COUNTER --> ENDS TURN. SET TO FALSE IN TURN COUNTER AND END LOOP.
    
    // extras
    @FXML private Text onesLabel;
    @FXML private Text twosLabel;
    @FXML private Text threesLabel;
    @FXML private Text foursLabel;
    @FXML private Text fivesLabel;
    @FXML private Text sixesLabel;
    @FXML private Text toakLabel;
    @FXML private Text foakLabel;
    @FXML private Text ssLabel;
    @FXML private Text lsLabel;
    @FXML private Text fhLabel;
    @FXML private Text chanceLabel;
    @FXML private Text yahtzeeLabel;        
    @FXML private Label UPLabel;       
    @FXML private Label LWLabel;       
    @FXML private Label prob1Label;      
    @FXML private Label prob2Label;       
    @FXML private Label handLabel;       
    @FXML private Label totalLabel;
    @FXML private Text returnLabel;  
    @FXML private GridPane upperSectionGrid;
    @FXML private GridPane lowerSectionGrid;
    @FXML private Rectangle rec1;
    @FXML private Rectangle rec2;
    @FXML private Rectangle background;
    @FXML private Rectangle whiteBack;
    @FXML private AnchorPane anchor;
    @FXML private ImageView arrowBack;       
            
    // choose a combo buttons
    @FXML private Button chooseOneButton;
    @FXML private Button chooseTwoButton;
    @FXML private Button chooseThreeButton;
    @FXML private Button chooseFourButton;
    @FXML private Button chooseFiveButton;
    @FXML private Button chooseSixButton;
    @FXML private Button chooseTOAKButton;
    @FXML private Button chooseFOAKButton;
    @FXML private Button chooseFHButton;
    @FXML private Button chooseSSButton;
    @FXML private Button chooseLSButton;
    @FXML private Button chooseChanceButton;
    @FXML private Button chooseYahtzeeButton;
    
    // probabilities 
    @FXML private TextField onesProb;
    @FXML private TextField twosProb;
    @FXML private TextField threesProb;
    @FXML private TextField foursProb;
    @FXML private TextField fivesProb;
    @FXML private TextField sixesProb;
    @FXML private TextField TOAKProb;
    @FXML private TextField FOAKProb;
    @FXML private TextField FHProb;
    @FXML private TextField SSProb;
    @FXML private TextField LSProb;
    @FXML private TextField chanceProb;
    @FXML private TextField yahtzeeProb;
    
    @FXML private TextField totalScoreText;
    
    // roll button
    @FXML private Button rollButton;
    
    // return
    @FXML private Button returnButton;
    
    // dice
    @FXML private GridPane diceHandGridPane;
    @FXML private Button diceOne;
    @FXML private Button diceTwo;
    @FXML private Button diceThree;
    @FXML private Button diceFour;
    @FXML private Button diceFive;
    
    // Dice Images
    @FXML private Image diceImg1 = new Image("/YahtzeeProject/1Face.png", 86, 86, true, true); // Image(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)
    @FXML private Image diceImg2 = new Image("/YahtzeeProject/2Face.png", 86, 86, true, true); 
    @FXML private Image diceImg3 = new Image("/YahtzeeProject/3Face.png", 86, 86, true, true);
    @FXML private Image diceImg4 = new Image("/YahtzeeProject/4Face.png", 86, 86, true, true);
    @FXML private Image diceImg5 = new Image("/YahtzeeProject/5Face.png", 86, 86, true, true);
    @FXML private Image diceImg6 = new Image("/YahtzeeProject/6Face.png", 86, 86, true, true);
    @FXML private ImageView dice1 = new ImageView(diceImg1); 
    @FXML private ImageView dice2 = new ImageView(diceImg2);
    @FXML private ImageView dice3 = new ImageView(diceImg3);
    @FXML private ImageView dice4 = new ImageView(diceImg4);
    @FXML private ImageView dice5 = new ImageView(diceImg5);
    @FXML private ImageView dice6 = new ImageView(diceImg6);
    
    public ArrayList<Dice> hand;
    private Probability prob;
    private Roll roll;
    
    private Thread createPlayer = new Thread(new Runnable() {
       @Override
       public void run() {
           //Player player = new Player();
           hand.addAll(player.getHand());
       }
    });
    
    // change the image in the Dice Buttons
    // set so that this occurs first and THEN they can pick something to do
    private Thread changeDiceMedia = new Thread(new Runnable() {
        @Override
        public void run() {
            // lock
            lock.lock();
            try {
                setDice();
            } finally {
                lock.unlock();
            }
            
            // finally { unlock } 
        }
    });
    
    /*
    // change border color when picked
    private Thread selectDiceBorders = new Thread(new Runnable() {
        @Override
        public void run() {
            selectDice();
        }
    });
    */
    
    private Thread probSetting = new Thread(new Runnable() {
        @Override
        public void run() {
            
            try {
                onesProb();
                twosProb();
                threesProb();
                foursProb();
                fivesProb();
                sixesProb();
                FHProb();
                TOAKProb();
                FOAKProb();
                SSProb();
                LSProb();
                chanceProb();
                yahtzeeProb();
                // unlock?
            } catch (IOException ex) {
                System.out.println("IOException in probSetting.");
                System.exit(0);
            }
            
        }
    });
    
    private ReentrantLock lock = new ReentrantLock();
    
    // turn counter (for rolls 1, 2 and 3)
    private int turn = 1;
    
    // methods
    // ******TO DO:******
    // FIX ERROR, STACK OVERFLOW OF DICE AND PLAYER
        // IT CALLS PLAYER INFINITELY FOR SOME REASON -> SO IT CREATES INFINITE DICE
    
    @FXML
    public void initialize() {
        lock.lock();
        try {
            createPlayer.start();
            changeDiceMedia.start();
        } finally {
            lock.unlock();
        }
        setDice(); // lock here??
    }
    
    
    @Override
    public void run() { // needs thread.start();
        // create instance of Player and run the thread using that?
        // javatpoint.com/java thread run method
        
        // thread checks if dice is selected -> lock button (CHECK IF ANY DICE IS SELECTED, IF NONE ARE, LOCK ROLL BUTTON FOR TURNS 2 AND 3)
        
        // thread select dice -> css
        
        // roll -> change media and update values for points and probabilities, THEN allow the player to do stuff (lock and unlock)
    }
    
    // RETURN TO MAIN MENU
    public void returnHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneGuiFinal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    // SELECT A DICE FOR REROLL
    public void selectDice(ActionEvent event) throws IOException {
        for (int i = 0; i < 5; i++) {
            if (hand.get(i).isSelected()) {
                hand.get(i).setSelected(false);
                
                // css turn off
                switch (i) {
                    case 0 -> diceOne.setStyle("-fx-border-color: black");
                    case 1 -> diceTwo.setStyle("-fx-border-color: black");
                    case 2 -> diceThree.setStyle("-fx-border-color: black");
                    case 3 -> diceFour.setStyle("-fx-border-color: black");
                    case 4 -> diceFive.setStyle("-fx-border-color: black");         
                }
                
            } else if (hand.get(i).isSelected() == false) {
                hand.get(i).setSelected(true);
                
                // css on
                switch (i) {
                    case 0 -> diceOne.setStyle("-fx-border-color: red");
                    case 1 -> diceTwo.setStyle("-fx-border-color: red");
                    case 2 -> diceThree.setStyle("-fx-border-color: red");
                    case 3 -> diceFour.setStyle("-fx-border-color: red");
                    case 4 -> diceFive.setStyle("-fx-border-color: red");         
                }
            }
            
            
        }
        
        // call isSelected method
        // see if it is true -> make false for that position and change css
        // if false -> make true and add red border to button
    }
    
    // ROLLS DICE
    public void rollDice(ActionEvent event) throws IOException { ///////////////////////////////////////////////
        switch (turn) {
            case 1 -> {
                for (int i = 0; i < 5; i++) {
                    Dice dice = new Dice();
                    hand.add(dice);
                }
                changeDiceMedia.start();
                
                comboCheck();
                turn++;
            }
            case 2 -> {
                // checks to see if a dice is selected, then rerolls it 
                
                
                
                for (int i = 0; i < 5; i++) {
                    if (hand.get(i).isSelected() == true) {
                        rollButton.setDisable(false);
                        hand.get(i).setDiceValue();
                    }   
                }
                changeDiceMedia.start();
                
                comboCheck();
                turn++;
            }
            case 3 -> {
                for (int i = 0; i < 5; i++) {
                    if (hand.get(i).isSelected() == true) {
                        rollButton.setDisable(false);
                        hand.get(i).setDiceValue();
                    }   
                }
                
                changeDiceMedia.start();
                
                comboCheck();
            }
            default -> {
            }
        }
        // runLater here? (cause GUI updating?)
        // probability setting
        probSetting.start();
        
    }
    
    // check available combinations all at once, set them to true or false in multiple if statements
    // edits button text to have points
    public void comboCheck() {
        Roll roll = new Roll();
        
        if (roll.isOnes(hand) == false) {
            chooseOneButton.setDisable(true);
        } else {
            chooseOneButton.setDisable(false);
            int numOfOne = 0;
        
            for (int i = 0; i < 6; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 1) {
                    numOfOne += 1;
                }
            }
            
            chooseOneButton.setText(String.valueOf(numOfOne));
        }
        
        if (roll.isTwos(hand) == false) {
            chooseTwoButton.setDisable(true);
        } else {
            chooseTwoButton.setDisable(false);
            int numOfTwo = 0;
        
            for (int i = 0; i < 6; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 1) {
                    numOfTwo += 1;
                }
            }
            
            chooseTwoButton.setText(String.valueOf(numOfTwo));
            
        }
        
        if (roll.isThrees(hand) == false) {
            chooseThreeButton.setDisable(true);
        } else {
            chooseThreeButton.setDisable(false);
            int numOfThree = 0;
        
            for (int i = 0; i < 6; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 1) {
                    numOfThree += 1;
                }
            }
            
            chooseThreeButton.setText(String.valueOf(numOfThree));
        }
        
        if (roll.isFours(hand) == false) {
            chooseFourButton.setDisable(true);
        } else {
            chooseFourButton.setDisable(false);
            int numOfFour = 0;
        
            for (int i = 0; i < 6; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 1) {
                    numOfFour += 1;
                }
            }
            
            chooseFourButton.setText(String.valueOf(numOfFour));
        }
        
        if (roll.isFives(hand) == false) {
            chooseFiveButton.setDisable(true);
        } else {
            chooseFiveButton.setDisable(false);
            int numOfFive = 0;
        
            for (int i = 0; i < 6; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 1) {
                    numOfFive += 1;
                }
            }
            
            chooseFiveButton.setText(String.valueOf(numOfFive));
        }
        
        if (roll.isSixes(hand) == false) {
            chooseSixButton.setDisable(true);
        } else {
            chooseSixButton.setDisable(false);
            int numOfSix = 0;
        
            for (int i = 0; i < 6; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 1) {
                    numOfSix += 1;
                }
            }
            
            chooseSixButton.setText(String.valueOf(numOfSix));
        }
        
        if (roll.isThreeOfAKind(hand) == false) {
            chooseTOAKButton.setDisable(true);
        } else {
            chooseTOAKButton.setDisable(false);
            chooseTOAKButton.setDisable(false);
            int toak = 0;
            for (int i = 0; i < 5; i++) {
                toak += hand.get(i).getDiceValue();
            }
            chooseTOAKButton.setText(String.valueOf(toak));
        }
        
        if (roll.isFourOfAKind(hand) == false) {
            chooseFOAKButton.setDisable(true);
        } else {
            chooseFOAKButton.setDisable(false);
            int foak = 0;
            for (int i = 0; i < 5; i++) {
                foak += hand.get(i).getDiceValue();
            }
            chooseFOAKButton.setText(String.valueOf(foak));
        }
        
        if (roll.isSmallStraight(hand) == false) {
            chooseSSButton.setDisable(true);
        } else {
            chooseSSButton.setDisable(false);
            chooseSSButton.setText(String.valueOf(30));
        }
        
        if (roll.isLargeStraight(hand) == false) {
            chooseLSButton.setDisable(true);
        } else {
            chooseLSButton.setDisable(false);
            chooseLSButton.setText(String.valueOf(40));
        }
        
        if (roll.isFullHouse(hand) == false) {
            chooseFHButton.setDisable(true);
        } else {
            chooseFHButton.setDisable(false);
            chooseFHButton.setText(String.valueOf(25));
        }
                
        
        if (roll.isChance(hand) == false) {
            chooseChanceButton.setDisable(true);
        } else {
            chooseChanceButton.setDisable(false);
            int chance = 0;
            for (int i = 0; i < 5; i++) {
                chance += hand.get(i).getDiceValue();
            }
            chooseChanceButton.setText(String.valueOf(chance));
        }
        
        if (roll.isYahtzee(hand) == false) {
            chooseYahtzeeButton.setDisable(true);
        } else {
            chooseYahtzeeButton.setDisable(false);
            chooseYahtzeeButton.setText(String.valueOf(50));
        }
        
    }
    
    // SETS IMAGES FOR FIVE DICE
    public void setDice() {
        switch (hand.get(0).getDiceValue()) {
            case 1 -> diceOne.setGraphic(dice1);
            case 2 -> diceOne.setGraphic(dice2);
            case 3 -> diceOne.setGraphic(dice3);
            case 4 -> diceOne.setGraphic(dice4);
            case 5 -> diceOne.setGraphic(dice5);
            case 6 -> diceOne.setGraphic(dice6);    
        }
        
        switch (hand.get(1).getDiceValue()) {
            case 1 -> diceTwo.setGraphic(dice1);
            case 2 -> diceTwo.setGraphic(dice2);
            case 3 -> diceTwo.setGraphic(dice3);
            case 4 -> diceTwo.setGraphic(dice4);
            case 5 -> diceTwo.setGraphic(dice5);
            case 6 -> diceTwo.setGraphic(dice6);    
        }
        
        switch (hand.get(2).getDiceValue()) {
            case 1 -> diceThree.setGraphic(dice1);
            case 2 -> diceThree.setGraphic(dice2);
            case 3 -> diceThree.setGraphic(dice3);
            case 4 -> diceThree.setGraphic(dice4);
            case 5 -> diceThree.setGraphic(dice5);
            case 6 -> diceThree.setGraphic(dice6);    
        }
        
        switch (hand.get(3).getDiceValue()) {
            case 1 -> diceFour.setGraphic(dice1);
            case 2 -> diceFour.setGraphic(dice2);
            case 3 -> diceFour.setGraphic(dice3);
            case 4 -> diceFour.setGraphic(dice4);
            case 5 -> diceFour.setGraphic(dice5);
            case 6 -> diceFour.setGraphic(dice6);    
        }
        
        switch (hand.get(4).getDiceValue()) {
            case 1 -> diceFive.setGraphic(dice1);
            case 2 -> diceFive.setGraphic(dice2);
            case 3 -> diceFive.setGraphic(dice3);
            case 4 -> diceFive.setGraphic(dice4);
            case 5 -> diceFive.setGraphic(dice5);
            case 6 -> diceFive.setGraphic(dice6);    
        }
    }
    
    // CHOOSE COMBINATIONS METHODS
    public void chooseOne(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseOnes(hand);
    }
    
    public void chooseTwo(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseTwos(hand);
    }
    
    public void chooseThree(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseThrees(hand);
    }
    
    public void chooseFour(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseFours(hand);
    }
    
    public void chooseFive(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseFives(hand);
    }
    
    public void chooseSix(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseSixes(hand);
    }
    
    public void chooseSS(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseSmallStraight(hand);
    }
    
    public void chooseLS(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseLargeStraight(hand);
    }
    
    public void chooseTOAK(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseThreeOfAKind(hand);
    }
    
    public void chooseFOAK(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseFourOfAKind(hand);
    }
    
    public void chooseFH(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseFullHouse(hand);
    }
    
    public void chooseChance(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseChance(hand);
    }
    
    public void chooseYahtzee(ActionEvent event) throws IOException {
        Roll roll = new Roll();
        roll.chooseYahtzee(hand);
    }
    
    // PROBABILITIES METHODS
    // HAVE ALL PROBS (5 ROWS)???
    public ArrayList<Integer> counters() {
        ArrayList<Integer> counters = new ArrayList<>();
        counters.add(0); // 1
        counters.add(0); // 2
        counters.add(0); // 3
        counters.add(0); // 4
        counters.add(0); // 5
        counters.add(0); // 6

        for (int j = 0; j < hand.size(); j++) {
            for (int i = 1; i < 7; i++) {
                if (hand.get(j).getDiceValue() == i) {
                    counters.set(i-1, counters.get(i-1) + 1); 
                } 
            }
        }
        
        return counters;
    }
    
    public void onesProb() throws IOException { // figure this out
        if (counters().contains(1)) {
            onesProb.setText("100");
        } else {
            onesProb.setText(String.valueOf(1/6));
        }
    } 
    
    public void twosProb() throws IOException { // figure this out
        if (counters().contains(2)) {
            onesProb.setText("100");
        } else {
            onesProb.setText(String.valueOf(1/6));
        }
    }
    
    public void threesProb() throws IOException { // figure this out
        if (counters().contains(3)) {
            onesProb.setText("100");
        } else {
            onesProb.setText(String.valueOf(1/6));
        }
    }
    
    public void foursProb() throws IOException { // figure this out
        if (counters().contains(4)) {
            onesProb.setText("100");
        } else {
            onesProb.setText(String.valueOf(1/6));
        }
    }
    
    public void fivesProb() throws IOException { // figure this out
        if (counters().contains(5)) {
            onesProb.setText("100");
        } else {
            onesProb.setText(String.valueOf(1/6));
        }
    }
    
    public void sixesProb() throws IOException { // figure this out
        if (counters().contains(6)) {
            onesProb.setText("100");
        } else {
            onesProb.setText(String.valueOf(1/6));
        }
    }
    
    public void TOAKProb() throws IOException {
        if (roll.isAvailableFourOfAKind() == false) {
            FHProb.setText("");
            return;
        }
        
        String value = String.valueOf(prob.probThreeOfAKind(hand, turn));
        TOAKProb.setText(value);
    }
    
    public void FOAKProb() throws IOException {
        if (roll.isAvailableThreeOfAKind() == false) {
            FHProb.setText("");
            return;
        }
        
        String value = String.valueOf(prob.probFourOfAKind(hand, turn));
        TOAKProb.setText(value);
    }
    
    public void FHProb() throws IOException {
        if (roll.isAvailableFullHouse() == false) {
            FHProb.setText("");
            return;
        }
        
        String value = String.valueOf(prob.probFullHouse(hand, turn));
        FHProb.setText(value);
    }
    
    public void SSProb() throws IOException {
        if (roll.isAvailableSmallStraight() == false) {
            SSProb.setText("");
            return;
        }
        
        String value = String.valueOf(prob.probSmallStraight(hand, turn));
        SSProb.setText(value);
    }
    
    public void LSProb() throws IOException {
        if (roll.isAvailableSmallStraight() == false) {
            LSProb.setText("");
            return;
        }
        
        String value = String.valueOf(prob.probLargeStraight(hand, turn));
        LSProb.setText(value);
    }
    
    public void chanceProb() throws IOException {
        if (roll.isAvailableChance() == false) {
            chanceProb.setText("");
            return;
        } else {
            chanceProb.setText("100");
            return;
        }
    }
    
    public void yahtzeeProb() throws IOException {
        if (roll.isAvailableYahtzee() == false) {
            yahtzeeProb.setText("");
            return;
        }
        
        String value = String.valueOf(prob.probYahtzee(hand, turn));
        yahtzeeProb.setText(value);
    }
    
}
