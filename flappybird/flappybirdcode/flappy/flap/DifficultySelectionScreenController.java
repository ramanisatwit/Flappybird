package flap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;

public class DifficultySelectionScreenController {
    @FXML
    private Pane gamePane;
    
    @FXML
    private Button easyButton; // Add @FXML annotation here

    @FXML
    private void handleEasyButtonClick(ActionEvent event) {
        // Clear existing children of gamePane (if any)
        gamePane.getChildren().clear();
        
        // Start the FlappyBirdEasy game
        FlappyBirdEasy flappyBirdEasy = new FlappyBirdEasy();
        gamePane.getChildren().add(flappyBirdEasy);
    }
}
