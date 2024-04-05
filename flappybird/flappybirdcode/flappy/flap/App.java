package flap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the TitleScreen.fxml
        Parent root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));

        // Set the scene on the primary stage
        primaryStage.setScene(new Scene(root));
        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}