package gui.pkgfinal;

import java.io.IOException;
import java.net.URL; 
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class GuiFinal extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
            
        Parent root = FXMLLoader.load(getClass().getResource("src\\resource\\guifinal\\SceneGuiFinal.fxml"));
       
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Yahtzee");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
