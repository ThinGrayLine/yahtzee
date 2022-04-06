package gui.pkgfinal;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GuiController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void play(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/resource.guifinal/play.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void credits(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("C:\\Users\\noahd\\OneDrive\\Documents\\NetBeansProject\\gui final\\src\\resource\\guifinal\\credits.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void rules(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("\\resource.guifinal\\rules.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void exit(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/resource.guifinal/exit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
