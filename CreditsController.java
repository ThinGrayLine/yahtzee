package YahtzeeProject;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 *
 * @author gyenc
 */
public class CreditsController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Button returnButton;

    public void returnButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneGuiFinal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

}
