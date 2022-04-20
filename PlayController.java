package YahtzeeProject;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author gyenc
 */
public class PlayController extends Player {
    // attributes
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    // game details
    private Player player;
    private boolean wasComboSelected = false; // HAVE THIS IN ALL COMBO METHODS, SET TRUE AND CHECK TURN COUNTER --> ENDS TURN. SET TO FALSE IN TURN COUNTER AND END LOOP.
    
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
    
    // dice
    @FXML private Button diceOne;
    @FXML private Button diceTwo;
    @FXML private Button diceThree;
    @FXML private Button diceFour;
    @FXML private Button diceFive;
    
    // Dice Images
    @FXML private Image diceImg1 = new Image("1Face.png");
    @FXML private Image diceImg2 = new Image("2Face.png");
    @FXML private Image diceImg3 = new Image("3Face.png");
    @FXML private Image diceImg4 = new Image("4Face.png");
    @FXML private Image diceImg5 = new Image("5Face.png");
    @FXML private Image diceImg6 = new Image("6Face.png");
    @FXML private ImageView dice1 = new ImageView(diceImg1);
    @FXML private ImageView dice2 = new ImageView(diceImg2);
    @FXML private ImageView dice3 = new ImageView(diceImg3);
    @FXML private ImageView dice4 = new ImageView(diceImg4);
    @FXML private ImageView dice5 = new ImageView(diceImg5);
    @FXML private ImageView dice6 = new ImageView(diceImg6);
    
    
    public ArrayList<Dice> hand = new ArrayList<>();
    
    // turn counter (for rolls 1, 2 and 3)
    private int turn = 1;
    
    // methods
    // ******TO DO:******
    // CHANGE PROB TEXT BASED ON POSSIBILITY IN TURN + 1
    // MAKE IT SO THAT THE PROBS ARE CALCULATED IMMEDIATELY AND IMAGES ARE CHANGED PER ROLL
    // figure out probabilities of upper section
    
    
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
        // call isSelected method
        // see if it is true -> make false for that position and change css
        // if false -> make true and add red border to button
    }
    
    // ROLLS DICE
    public void rollDice(ActionEvent event) throws IOException { ///////////////////////////////////////////////
        switch (turn) {
            case 1:
                for (int i = 0; i < 5; i++) {
                    Dice dice = new Dice();
                    hand.add(dice);
                }
                comboCheck();
                setDice();
                turn++;
                break;
            case 2:
                // checks to see if a dice is selected, then rerolls it
                for (int i = 0; i < 5; i++) {
                    if (hand.get(i).isSelected() == true) {
                        hand.get(i).setDiceValue();
                    }   
                }
                
                if (hand.get(0).isSelected() == false && hand.get(1).isSelected() == false && hand.get(2).isSelected() == false && hand.get(3).isSelected() == false && hand.get(4).isSelected() == false) {
                    // EXIT LOOP
                    
                    turn = 1;
                }
                comboCheck();
                setDice();
                turn++;
                break;
            case 3:
                for (int i = 0; i < 5; i++) {
                    if (hand.get(i).isSelected() == true) {
                        hand.get(i).setDiceValue();
                    }   
                }
                if (hand.get(0).isSelected() == false && hand.get(1).isSelected() == false && hand.get(2).isSelected() == false && hand.get(3).isSelected() == false && hand.get(4).isSelected() == false) {
                    // EXIT LOOP
                    
                    turn = 1;
                }
                comboCheck();
                setDice();
                turn = 1;
                break;
            default:
                break;
        }
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
            case 1:
                diceOne.setGraphic(dice1);
                break;
            case 2:
                diceOne.setGraphic(dice2);
                break;
            case 3:    
                diceOne.setGraphic(dice3);
                break;
            case 4:
                diceOne.setGraphic(dice4);
                break;
            case 5:
                diceOne.setGraphic(dice5);
                break;
            case 6:
                diceOne.setGraphic(dice6);
                break;    
        }
        
        switch (hand.get(1).getDiceValue()) {
            case 1:
                diceTwo.setGraphic(dice1);
                break;
            case 2:
                diceTwo.setGraphic(dice2);
                break;
            case 3:    
                diceTwo.setGraphic(dice3);
                break;
            case 4:
                diceTwo.setGraphic(dice4);
                break;
            case 5:
                diceTwo.setGraphic(dice5);
                break;
            case 6:
                diceTwo.setGraphic(dice6);
                break;    
        }
        
        switch (hand.get(2).getDiceValue()) {
            case 1:
                diceThree.setGraphic(dice1);
                break;
            case 2:
                diceThree.setGraphic(dice2);
                break;
            case 3:    
                diceThree.setGraphic(dice3);
                break;
            case 4:
                diceThree.setGraphic(dice4);
                break;
            case 5:
                diceThree.setGraphic(dice5);
                break;
            case 6:
                diceThree.setGraphic(dice6);
                break;    
        }
        
        switch (hand.get(3).getDiceValue()) {
            case 1:
                diceFour.setGraphic(dice1);
                break;
            case 2:
                diceFour.setGraphic(dice2);
                break;
            case 3:    
                diceFour.setGraphic(dice3);
                break;
            case 4:
                diceFour.setGraphic(dice4);
                break;
            case 5:
                diceFour.setGraphic(dice5);
                break;
            case 6:
                diceFour.setGraphic(dice6);
                break;    
        }
        
        switch (hand.get(4).getDiceValue()) {
            case 1:
                diceFive.setGraphic(dice1);
                break;
            case 2:
                diceFive.setGraphic(dice2);
                break;
            case 3:    
                diceFive.setGraphic(dice3);
                break;
            case 4:
                diceFive.setGraphic(dice4);
                break;
            case 5:
                diceFive.setGraphic(dice5);
                break;
            case 6:
                diceFive.setGraphic(dice6);
                break;    
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
    public void onesProb(ActionEvent event) throws IOException { // figure this out
        if (turn == 1 || turn == 2) {
            double prob = 1/6;
            onesProb.setText(String.valueOf(prob));
        } else if (turn == 3) {
            onesProb.setText("");
        }
    } 
    
    ///
    ///
    ///
    ///
    ///
    
    public void TOAKProb(ActionEvent event) throws IOException {
        
    }
    
    public void FOAKProb(ActionEvent event) throws IOException {
        
    }
    
    public void FHProb(ActionEvent event) throws IOException {
        
    }
    
    public void SSProb(ActionEvent event) throws IOException {
        
    }
    
    public void LSProb(ActionEvent event) throws IOException {
        
    }
    
    public void chanceProb(ActionEvent event) throws IOException {
        
    }
    
    public void yahtzeeProb(ActionEvent event) throws IOException {
        
    }
    
    
/*
    @FXML
    public void initialize() {
        Game gameStart = new Game();
        Player p1 = new Player();
        
        //Player p2 = new Player();
        // initialize p1 with dice?
    }

    // SET UPDATED DICE IMAGE DEPENDING ON DICE FACE  
    // RUN IN INITIALIZE METHOD
    // WHENEVER THE BUTTON IS PRESSED, DISPLAY DICE BASED ON ROLLED DICE
    
    (rollbutton).setOnClick(e -> {

            Dice hand = new Roll();
            switch (turn) {
                    case 1:
                            hand.reroll();

                            isAvailable();
                            turn++;
                            break;
                    case 2:
                            isAvailable();
                            if (hand.isSelected() == false) {
                                    hand.reroll();
                            }
                            break;
                    case 3: 
                            (rollbutton.setColor...)
                            isAvailable();
                            turns = 0;
                            break;
                    default:
                            break;
            }

    });

    ///////////////////////////////////////////////
    // do isavailable method all at once with a threadpool
    // specify n and n-1 threadpools every time a combo is selected, meaning it looks through less each time something is chosen
    // do n-1 depending on # of rounds passed

    //Threadpool

    public class isAvailable() implements Runnable {
            // HOW TO PASS DICE HAND HERE!!!

            public void run() {
                    Roll roll = new Roll();
                    if (isOnes(hand) == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isTwos(hand) == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isThrees(hand) == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isFours(hand) == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isFives(hand) == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isSixes(hand) == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isThreeOfAKind == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isFourOfAKind(hand) == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isSmallStraight(hand) == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isLargeStraight(hand) == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isChance(hand) == false) {
                            (onesbutton).setDisable(true);
                    }

                    if (isYahtzee(hand) == false) {
                            (onesbutton).setDisable(true);
                    }


            }

            public static void main(String args[]) {
                    (new Thread(new isAvailable())).start();
            }

    }

    DISABLE http://www.learningaboutelectronics.com/Articles/How-to-disable-button-after-click-in-JavaFX.php

    /////////////////////////////////////////////
    (onesbutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (twosbutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (threesbutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (foursbutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (fivesbutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (sixesbutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (TOKbutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (FOKbutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (SSbutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (LSbutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (chancebutton).setOnAction(e -> {
            // add score
            //disable button

    });

    (yahtzeebutton).setOnAction(e -> {
            // add score
            //disable button

    });

    */
}
