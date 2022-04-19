package YahtzeeProject;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class GuiController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private Button playButton;
    @FXML private Button creditsButton;
    @FXML private Button rulesButton;
    @FXML private Button exitButton;
    
    public void play(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("play.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void credits(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("credits.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void rules(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("rules.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void exit(ActionEvent event) {
        System.out.println("closing game, thanks for playing!");
        Platform.exit();
        
    }
    
}
