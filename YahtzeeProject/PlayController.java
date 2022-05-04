package YahtzeeProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
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


public class PlayController implements Runnable {
    // attributes
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    // game details
    private Player player = new Player();
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
    @FXML public ImageView dice1 = new ImageView(diceImg1); 
    @FXML public ImageView dice2 = new ImageView(diceImg2); 
    @FXML public ImageView dice3 = new ImageView(diceImg3); 
    @FXML public ImageView dice4 = new ImageView(diceImg4); 
    @FXML public ImageView dice5 = new ImageView(diceImg5); 
    @FXML public ImageView dice6 = new ImageView(diceImg6); 
    
    
    
    public ArrayList<Dice> hand = new ArrayList<>();
    public static Probability gameProb;
    public static Roll gameRoll = new Roll();
    
    
    private Thread createPlayer = new Thread(new Runnable() {
       @Override
       public void run() {
           lock.lock();
           synchronized (this) {
                try {
                    Player player = new Player();
                    // remove this once image issue is fixed!
                    hand.addAll(player.getHand());
                    
                } catch (StackOverflowError ex) {
                    System.out.println("OVERFLOW!");
                }
                
                lock.unlock();
           }
           
       }
    });
    
    // change the image in the Dice Buttons
    // set so that this occurs first and THEN they can pick something to do
    private Thread changeDiceMedia = new Thread(new Runnable() {
        @Override
        public void run() {
            
            // lock
            lock.lock();
            synchronized (lock) {
                try {
                    while (hand.size() != 5) {
                        handChanged.await();
                    }

                    for (int i = 0; i < 5; i++) {
                        System.out.println(hand.get(i).getDiceValue());
                    }

                    setDice();
                } catch (InterruptedException ex) {
                    System.out.println("Interruption in changeDiceMedia thread.");
                } finally {
                    lock.unlock();
                }
            }
            
            
        }
    });
    
    /*
    // change border color when picked
    private Thread selectDiceBorders = new Thread(new Runnable() {
        @Override
        public void run() {
            selectDice(ActionEvent event);
        }
    });
    */
    
    private Thread probSetting = new Thread(new Runnable() {
        @Override
        public void run() {
            
            //lock.lock();
            synchronized (lock) {
                /*
                while (hand.size() != 5) { // **CONDITION IS THAT HAND HASN'T BEEN ALTERED YET
                    try {
                        handChanged.wait();
                    } catch (InterruptedException ex) {
                        System.out.println("Error in probSetting.");
                    }
                }*/
                
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
            
        }
    });
    
    private ReentrantLock lock = new ReentrantLock();
    private Condition handChanged = lock.newCondition();
    
    // turn counter (for rolls 1, 2 and 3)
    private int turn = 0;
    
    // methods
    // ******TO DO:******
    // FIX ERROR, STACK OVERFLOW OF DICE AND PLAYER
        // IT CALLS PLAYER INFINITELY FOR SOME REASON -> SO IT CREATES INFINITE DICE
    
    @FXML
    public void initialize() throws InterruptedException {
        lock.lock();
        try {
            createPlayer.start();
            synchronized (this) {
                changeDiceMedia.start();
            }
            
            
            
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void run() { // needs thread.start();
        // fix this now
        comboCheck();
        probSetting.start();
        
        /*
        hand.addAll(player.getHand());
        handChanged.signal();
        */
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
    
    
    // change css for dice 1
    public void selectDice1(ActionEvent event) throws IOException {
        if (hand.get(0).isSelected()) {
            hand.get(0).setSelected(false);
            diceOne.setStyle("-fx-border-color: black");
        } else if (hand.get(0).isSelected() == false) {
            hand.get(0).setSelected(true);
            diceOne.setStyle("-fx-border-color: red");
        }  
        
    }            
    
    // change css for dice 2
    public void selectDice2(ActionEvent event) throws IOException {
        if (hand.get(1).isSelected()) {
            hand.get(1).setSelected(false);
            diceTwo.setStyle("-fx-border-color: black");
        } else if (hand.get(1).isSelected() == false) {
            hand.get(1).setSelected(true);
            diceTwo.setStyle("-fx-border-color: red");
        }  
        
    }
    
    // change css for dice 3
    public void selectDice3(ActionEvent event) throws IOException {
        if (hand.get(2).isSelected()) {
            hand.get(2).setSelected(false);
            diceThree.setStyle("-fx-border-color: black");
        } else if (hand.get(2).isSelected() == false) {
            hand.get(2).setSelected(true);
            diceThree.setStyle("-fx-border-color: red");
        }  
        
    }
    
    // change css for dice 4
    public void selectDice4(ActionEvent event) throws IOException {
        if (hand.get(3).isSelected()) {
            hand.get(3).setSelected(false);
            diceFour.setStyle("-fx-border-color: black");
        } else if (hand.get(3).isSelected() == false) {
            hand.get(3).setSelected(true);
            diceFour.setStyle("-fx-border-color: red");
        }  
        
    }
    
    // change css for dice 5
    public void selectDice5(ActionEvent event) throws IOException {
        if (hand.get(4).isSelected()) {
            hand.get(4).setSelected(false);
            diceFive.setStyle("-fx-border-color: black");
        } else if (hand.get(4).isSelected() == false) {
            hand.get(4).setSelected(true);
            diceFive.setStyle("-fx-border-color: red");
        }  
        
    }
    
    // ROLLS DICE
    public void rollDice(ActionEvent event) throws IOException { ///////////////////////////////////////////////
        
        switch (turn) {
            case 0 -> {
                
                for (int i = 0; i < 5; i++) {
                    Dice dice = new Dice();
                    hand.add(dice);
                }
                
                synchronized (this) {
                    changeDiceMedia.run(); // .start()?????
                }
                
                
                comboCheck();
                turn++;
            }
            case 1 -> {
                // checks to see if a dice is selected, then rerolls it 
                
                
                
                for (int i = 0; i < 5; i++) {
                    if (hand.get(i).isSelected() == false) {
                        rollButton.setDisable(false);
                        hand.get(i).setDiceValue();
                    }   
                }
                
                /*
                synchronized (this) {
                    changeDiceMedia.run();
                }
                */
                
                setDiceReroll();
                comboCheck();
                turn++;
            }
            case 2 -> {
                for (int i = 0; i < 5; i++) {
                    if (hand.get(i).isSelected() == true) {
                        rollButton.setDisable(false);
                        hand.get(i).setDiceValue();
                    }   
                }
                
                synchronized (this) {
                    changeDiceMedia.run();
                }
                
                setDiceReroll();
                comboCheck();
            }
            default -> {
                rollButton.setDisable(true);
                
            }
        }
        // runLater here? (cause GUI updating?)
        // probability setting
        probSetting.run();
        
    }
    
    // check available combinations all at once, set them to true or false in multiple if statements
    // edits button text to have points
    public void comboCheck() {
        Roll roll = new Roll(hand);
        
        if (roll.isOnes(hand) == false) {
            if (roll.isAvailableOnes() == false) {
                
            }
            
            //chooseOneButton.setDisable(true);
            chooseOneButton.setText("0");
        } else {
            chooseOneButton.setDisable(false);
            int numOfOne = 0;
        
            for (int i = 0; i < 5; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 1) {
                    numOfOne += 1;
                }
            }
            
            chooseOneButton.setText(String.valueOf(numOfOne));
        }
        
        if (roll.isTwos(hand) == false) {
            //chooseTwoButton.setDisable(true);
            chooseTwoButton.setText("0");
        } else {
            chooseTwoButton.setDisable(false);
            int numOfTwo = 0;
        
            for (int i = 0; i < 5; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 2) {
                    numOfTwo += 2;
                }
            }
            
            chooseTwoButton.setText(String.valueOf(numOfTwo));
            
        }
        
        if (roll.isThrees(hand) == false) {
            //chooseThreeButton.setDisable(true);
            chooseThreeButton.setText("0");
        } else {
            chooseThreeButton.setDisable(false);
            int numOfThree = 0;
        
            for (int i = 0; i < 5; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 3) {
                    numOfThree += 3;
                }
            }
            
            chooseThreeButton.setText(String.valueOf(numOfThree));
        }
        
        if (roll.isFours(hand) == false) {
            //chooseFourButton.setDisable(true);
            chooseFourButton.setText("0");
        } else {
            chooseFourButton.setDisable(false);
            int numOfFour = 0;
        
            for (int i = 0; i < 5; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 4) {
                    numOfFour += 4;
                }
            }
            
            chooseFourButton.setText(String.valueOf(numOfFour));
        }
        
        if (roll.isFives(hand) == false) {
            //chooseFiveButton.setDisable(true);
            chooseFiveButton.setText("0");
        } else {
            chooseFiveButton.setDisable(false);
            int numOfFive = 0;
        
            for (int i = 0; i < 5; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 5) {
                    numOfFive += 5;
                }
            }
            
            chooseFiveButton.setText(String.valueOf(numOfFive));
        }
        
        if (roll.isSixes(hand) == false) {
            //chooseSixButton.setDisable(true);
            chooseSixButton.setText("0");
        } else {
            chooseSixButton.setDisable(false);
            int numOfSix = 0;
        
            for (int i = 0; i < 5; i++) {
                int num = hand.get(i).getDiceValue();
                if (num == 6) {
                    numOfSix += 6;
                }
            }
            
            chooseSixButton.setText(String.valueOf(numOfSix));
            
        }
        
        if (roll.isThreeOfAKind(hand) == false) {
            //chooseTOAKButton.setDisable(true);
            chooseTOAKButton.setText("0");
        } else {
            chooseTOAKButton.setDisable(false);
            int toak = 0;
            for (int i = 0; i < 5; i++) {
                toak += hand.get(i).getDiceValue();
            }
            chooseTOAKButton.setText(String.valueOf(toak));
        }
        
        if (roll.isFourOfAKind(hand) == false) {
            //chooseFOAKButton.setDisable(true);
            chooseFOAKButton.setText("0");
        } else {
            chooseFOAKButton.setDisable(false);
            int foak = 0;
            for (int i = 0; i < 5; i++) {
                foak += hand.get(i).getDiceValue();
            }
            
            chooseFOAKButton.setText(String.valueOf(foak));
        }
        
        if (roll.isSmallStraight(hand) == false) {
            //chooseSSButton.setDisable(true);
            chooseSSButton.setText("0");
        } else {
            chooseSSButton.setDisable(false);
            chooseSSButton.setText(String.valueOf(30));
        }
        
        if (roll.isLargeStraight(hand) == false) {
            //chooseLSButton.setDisable(true);
            chooseLSButton.setText("0");
        } else {
            chooseLSButton.setDisable(false);
            chooseLSButton.setText(String.valueOf(40));
        }
        
        if (roll.isFullHouse(hand) == false) {
            //chooseFHButton.setDisable(true);
            chooseFHButton.setText("0");
        } else {
            chooseFHButton.setDisable(false);
            chooseFHButton.setText(String.valueOf(25));
        }
                
        
        if (roll.isChance(hand) == false) {
            //chooseChanceButton.setDisable(true);
            chooseChanceButton.setText("0");
        } else {
            chooseChanceButton.setDisable(false);
            int chance = 0;
            for (int i = 0; i < 5; i++) {
                chance += hand.get(i).getDiceValue();
            }
            chooseChanceButton.setText(String.valueOf(chance));
        }
        
        if (roll.isYahtzee(hand) == false) {
            //chooseYahtzeeButton.setDisable(true);
            chooseYahtzeeButton.setText("0");
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
        
        
        ImageView img1 = new ImageView(diceImg1);
        ImageView img2 = new ImageView(diceImg2);
        ImageView img3 = new ImageView(diceImg3);
        ImageView img4 = new ImageView(diceImg4);
        ImageView img5 = new ImageView(diceImg5);
        ImageView img6 = new ImageView(diceImg6);
        
        
        switch (hand.get(1).getDiceValue()) {
            case 1 -> diceTwo.setGraphic(img1);
            case 2 -> diceTwo.setGraphic(img2);
            case 3 -> diceTwo.setGraphic(img3);
            case 4 -> diceTwo.setGraphic(img4);
            case 5 -> diceTwo.setGraphic(img5);
            case 6 -> diceTwo.setGraphic(img6);    
        }
        
        
        ImageView img11 = new ImageView(diceImg1);
        ImageView img22 = new ImageView(diceImg2);
        ImageView img33 = new ImageView(diceImg3);
        ImageView img44 = new ImageView(diceImg4);
        ImageView img55 = new ImageView(diceImg5);
        ImageView img66 = new ImageView(diceImg6);
        
        
        switch (hand.get(2).getDiceValue()) {
            case 1 -> diceThree.setGraphic(img11);
            case 2 -> diceThree.setGraphic(img22);
            case 3 -> diceThree.setGraphic(img33);
            case 4 -> diceThree.setGraphic(img44);
            case 5 -> diceThree.setGraphic(img55);
            case 6 -> diceThree.setGraphic(img66);    
        }
        
        ImageView img111 = new ImageView(diceImg1);
        ImageView img222 = new ImageView(diceImg2);
        ImageView img333 = new ImageView(diceImg3);
        ImageView img444 = new ImageView(diceImg4);
        ImageView img555 = new ImageView(diceImg5);
        ImageView img666 = new ImageView(diceImg6);
        
        switch (hand.get(3).getDiceValue()) {
            case 1 -> diceFour.setGraphic(img111);
            case 2 -> diceFour.setGraphic(img222);
            case 3 -> diceFour.setGraphic(img333);
            case 4 -> diceFour.setGraphic(img444);
            case 5 -> diceFour.setGraphic(img555);
            case 6 -> diceFour.setGraphic(img666);    
        }
        
        ImageView img1111 = new ImageView(diceImg1);
        ImageView img2222 = new ImageView(diceImg2);
        ImageView img3333 = new ImageView(diceImg3);
        ImageView img4444 = new ImageView(diceImg4);
        ImageView img5555 = new ImageView(diceImg5);
        ImageView img6666 = new ImageView(diceImg6);
        
        switch (hand.get(4).getDiceValue()) {
            case 1 -> diceFive.setGraphic(img1111);
            case 2 -> diceFive.setGraphic(img2222);
            case 3 -> diceFive.setGraphic(img3333);
            case 4 -> diceFive.setGraphic(img4444);
            case 5 -> diceFive.setGraphic(img5555);
            case 6 -> diceFive.setGraphic(img6666);    
        }
    }
    
    public void setDiceReroll() {
        Dice newDice1 = new Dice();
        
        if (hand.get(0).isSelected() == false) {
            switch (newDice1.getDiceValue()) {
                case 1 -> diceOne.setGraphic(dice1);
                case 2 -> diceOne.setGraphic(dice2);
                case 3 -> diceOne.setGraphic(dice3);
                case 4 -> diceOne.setGraphic(dice4);
                case 5 -> diceOne.setGraphic(dice5);
                case 6 -> diceOne.setGraphic(dice6); 
            }
        }
        
        Dice newDice2 = new Dice();
        if (hand.get(1).isSelected() == false) {
            switch (newDice2.getDiceValue()) {
                case 1 -> diceTwo.setGraphic(dice1);
                case 2 -> diceTwo.setGraphic(dice2);
                case 3 -> diceTwo.setGraphic(dice3);
                case 4 -> diceTwo.setGraphic(dice4);
                case 5 -> diceTwo.setGraphic(dice5);
                case 6 -> diceTwo.setGraphic(dice6); 
            }
        }
        
        Dice newDice3 = new Dice();
        if (hand.get(2).isSelected() == false) {
            switch (newDice3.getDiceValue()) {
                case 1 -> diceThree.setGraphic(dice1);
                case 2 -> diceThree.setGraphic(dice2);
                case 3 -> diceThree.setGraphic(dice3);
                case 4 -> diceThree.setGraphic(dice4);
                case 5 -> diceThree.setGraphic(dice5);
                case 6 -> diceThree.setGraphic(dice6); 
            }
        }
        
        Dice newDice4 = new Dice();
        if (hand.get(3).isSelected() == false) {
            switch (newDice4.getDiceValue()) {
                case 1 -> diceFour.setGraphic(dice1);
                case 2 -> diceFour.setGraphic(dice2);
                case 3 -> diceFour.setGraphic(dice3);
                case 4 -> diceFour.setGraphic(dice4);
                case 5 -> diceFour.setGraphic(dice5);
                case 6 -> diceFour.setGraphic(dice6); 
            }
        }
        
        Dice newDice5 = new Dice();
        if (hand.get(4).isSelected() == false) {
            switch (newDice5.getDiceValue()) {
                case 1 -> diceFive.setGraphic(dice1);
                case 2 -> diceFive.setGraphic(dice2);
                case 3 -> diceFive.setGraphic(dice3);
                case 4 -> diceFive.setGraphic(dice4);
                case 5 -> diceFive.setGraphic(dice5);
                case 6 -> diceFive.setGraphic(dice6); 
            }
        }
        
    }
    
    // CHOOSE COMBINATIONS METHODS
    public void chooseOne(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseOnes(hand);
        gameRoll.setAvailableOnes(false);
        
        gameRoll.setOnesScore(Integer.parseInt(chooseOneButton.getText()));
        
        chooseOneButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseTwo(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseTwos(hand);
        gameRoll.setAvailableTwos(false);
        
        gameRoll.setTwosScore(Integer.parseInt(chooseTwoButton.getText()));
        
        chooseTwoButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseThree(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseThrees(hand);
        gameRoll.setAvailableThrees(false);
        
        gameRoll.setThreesScore(Integer.parseInt(chooseThreeButton.getText()));
        
        chooseThreeButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseFour(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseFours(hand);
        gameRoll.setAvailableFours(false);
        
        gameRoll.setFoursScore(Integer.parseInt(chooseFourButton.getText()));
        
        chooseFourButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
   
    public void chooseFive(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseFives(hand);
        gameRoll.setAvailableFives(false);
        
        gameRoll.setFivesScore(Integer.parseInt(chooseFiveButton.getText()));
        
        chooseFiveButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseSix(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseSixes(hand);
        gameRoll.setAvailableSixes(false);
        
        gameRoll.setSixesScore(Integer.parseInt(chooseSixButton.getText()));
        
        chooseSixButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseSS(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseSmallStraight(hand);
        gameRoll.setAvailableSmallStraight(false);
        
        gameRoll.setSmallStraightScore(Integer.parseInt(chooseSSButton.getText()));
        
        chooseSSButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseLS(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseLargeStraight(hand);
        gameRoll.setAvailableLargeStraight(false);
        
        gameRoll.setLargeStraightScore(Integer.parseInt(chooseLSButton.getText()));
        
        chooseLSButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseTOAK(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseThreeOfAKind(hand);
        gameRoll.setAvailableThreeOfAKind(false);
        
        gameRoll.setThreeOfAKindScore(Integer.parseInt(chooseTOAKButton.getText()));
        
        chooseTOAKButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseFOAK(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseFourOfAKind(hand);
        gameRoll.setAvailableFourOfAKind(false);
        
        gameRoll.setFourOfAKindScore(Integer.parseInt(chooseFOAKButton.getText()));
        
        chooseFOAKButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseFH(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseFullHouse(hand);
        gameRoll.setAvailableFullHouse(false);
        
        gameRoll.setFullHouseScore(Integer.parseInt(chooseFHButton.getText()));
        
        chooseFHButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseChance(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseChance(hand);
        gameRoll.setAvailableChance(false);
        
        gameRoll.setChanceScore(Integer.parseInt(chooseChanceButton.getText()));
        chooseChanceButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    public void chooseYahtzee(ActionEvent event) throws IOException {
        //Roll roll = new Roll();
        gameRoll.chooseYahtzee(hand);
        gameRoll.setAvailableYahtzee(false);
        
        gameRoll.setYahtzeeScore(Integer.parseInt(chooseYahtzeeButton.getText()));
        
        chooseYahtzeeButton.setDisable(true);
        rollButton.setDisable(false);
        turn = 0;
        scoreTotal();
    }
    
    // PROBABILITIES METHODS
    // HAVE ALL PROBS (5 ROWS)???
    public ArrayList<Integer> counters(ArrayList<Dice> hand) {
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
        if (counters(hand).get(0) >= 1) {
            onesProb.setText("100");
        } else {
            double num = 1/6;
            onesProb.setText(String.valueOf(num));
        }
    } 
    
    public void twosProb() throws IOException { // figure this out
        if (counters(hand).get(1) >= 1) {
            twosProb.setText("100");
        } else {
            double num = 1/6;
            twosProb.setText(String.valueOf(num));
        }
    }
    
    public void threesProb() throws IOException { // figure this out
        if (counters(hand).get(2) >= 1) {
            threesProb.setText("100");
        } else {
            double num = 1/6;
            threesProb.setText(String.valueOf(num));
        }
    }
    
    public void foursProb() throws IOException { // figure this out
        if (counters(hand).get(3) >= 1) {
            foursProb.setText("100");
        } else {
            double num = 1/6;
            foursProb.setText(String.valueOf(num));
        }
    }
    
    public void fivesProb() throws IOException { // figure this out
        if (counters(hand).get(4) >= 1) {
            fivesProb.setText("100");
        } else {
            double num = 1/6;
            fivesProb.setText(String.valueOf(num));
        }
    }
    
    public void sixesProb() throws IOException { // figure this out
        if (counters(hand).get(5) >= 1) {
            sixesProb.setText("100");
        } else {
            double num = 1/6;
            sixesProb.setText(String.valueOf(num));
        }
    }
    
    public void TOAKProb() throws IOException {
        //Roll roll = new Roll(hand);
        gameRoll = new Roll(hand);
        if (gameRoll.isAvailableThreeOfAKind() == false) {
            TOAKProb.setText("");
            return;
        }
        
        gameProb = new Probability(hand);
        //String.format("%.6g%n", prob.probThreeOfAKind(hand, turn));
        //String value = String.valueOf(prob.probThreeOfAKind(hand, turn));
        TOAKProb.setText(String.format("%.5g%n", gameProb.probThreeOfAKind(hand, turn)));
        
        if (gameRoll.isThreeOfAKind(hand) == true) {
            TOAKProb.setText("100.00");
        }
        
    }
    
    public void FOAKProb() throws IOException {
        //Roll roll = new Roll(hand);
        gameRoll = new Roll(hand);
        if (gameRoll.isAvailableFourOfAKind() == false) {
            FOAKProb.setText("");
            return;
        }
        
        gameProb = new Probability(hand);
        FOAKProb.setText(String.format("%.5g%n", gameProb.probFourOfAKind(hand, turn)));
        
        if (gameRoll.isFourOfAKind(hand) == true) {
            FOAKProb.setText("100.00");
        }
    }
    
    public void FHProb() throws IOException {
        //Roll roll = new Roll(hand);
        gameRoll = new Roll(hand);
        if (gameRoll.isAvailableFullHouse() == false) {
            FHProb.setText("");
            return;
        }
        
        gameProb = new Probability(hand);
        FHProb.setText(String.format("%.5g%n", gameProb.probFullHouse(hand, turn)));
        
        if (gameRoll.isFullHouse(hand) == true) {
            FHProb.setText("100.00");
        }
    }
    
    public void SSProb() throws IOException {
        //Roll roll = new Roll(hand);
        gameRoll = new Roll(hand);
        if (gameRoll.isAvailableSmallStraight() == false) {
            SSProb.setText("");
            return;
        }
        
        gameProb = new Probability(hand);
        SSProb.setText(String.format("%.5g%n", gameProb.probSmallStraight(hand, turn)));
        
        if (gameRoll.isSmallStraight(hand) == true) {
            SSProb.setText("100.00");
        }
    }
    
    public void LSProb() throws IOException {
        //Roll roll = new Roll(hand);
        gameRoll = new Roll(hand);
        if (gameRoll.isAvailableLargeStraight() == false) {
            LSProb.setText("");
            return;
        }
        
        gameProb = new Probability(hand);
        LSProb.setText(String.format("%.5g%n", gameProb.probLargeStraight(hand, turn)));
        
        if (gameRoll.isLargeStraight(hand) == true) {
            LSProb.setText("100.00");
        }
    }
    
    public void chanceProb() throws IOException {
        //Roll roll = new Roll(hand);
        gameRoll = new Roll(hand);
        if (gameRoll.isAvailableChance() == false) {
            chanceProb.setText("");
        } else {
            chanceProb.setText("100");
        }
    }
    
    public void yahtzeeProb() throws IOException {
        //Roll roll = new Roll(hand);
        gameRoll = new Roll(hand);
        if (gameRoll.isAvailableYahtzee() == false) {
            yahtzeeProb.setText("");
            return;
        }
        
        Probability gameProb = new Probability(hand);
        yahtzeeProb.setText(String.format("%.5g%n", gameProb.probYahtzee(hand, turn)));
        
        if (gameRoll.isYahtzee(hand) == true) {
            yahtzeeProb.setText("100.00");
        }
    }
    
    public void scoreTotal() {
        int total = 0;
        //Roll roll = new Roll(hand);
        if (gameRoll.isAvailableOnes() == false && gameRoll.isAvailableTwos() == false && gameRoll.isAvailableThrees() == false && gameRoll.isAvailableFours() == false && gameRoll.isAvailableFives() == false && gameRoll.isAvailableSixes() == false && gameRoll.isAvailableSmallStraight() == false && gameRoll.isAvailableLargeStraight() == false && gameRoll.isAvailableThreeOfAKind() == false && gameRoll.isAvailableFourOfAKind() == false && gameRoll.isAvailableFullHouse() == false && gameRoll.isAvailableChance() == false && gameRoll.isAvailableYahtzee() == false) {
            total = gameRoll.getOnes() + gameRoll.getTwos() + gameRoll.getThrees() + gameRoll.getFours() + gameRoll.getFives() + gameRoll.getSixes() + gameRoll.getSmallStraight() + gameRoll.getLargeStraight() + gameRoll.getThreeOfAKind() + gameRoll.getFourOfAKind() + gameRoll.getFullHouse() + gameRoll.getChance() + gameRoll.getYahtzee();
            totalScoreText.setText(String.valueOf(total));
        } 
        
    }
    
}