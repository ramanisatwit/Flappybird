package flap;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TitleScreenController {
    @FXML
    private Button levelsButton;

    @FXML
    private void handleLevelsButtonClick(ActionEvent event) {
        try {
            // Load the FXML file for the levels screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DifficultySelectionScreen.fxml"));
            Parent root = loader.load();
            
            // Create a new scene with the levels screen as the root
            Scene scene = new Scene(root);
            
            // Get the stage from the event source
            Stage stage = (Stage) levelsButton.getScene().getWindow();
            
            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load levels screen");
            alert.setContentText("An error occurred while loading the levels screen. Please try again later.");
            alert.showAndWait();
        }
    }
}