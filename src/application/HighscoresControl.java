package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.IOException;

public class HighscoresControl {
    @FXML
    private Text Player1;
   @FXML
   public void initialize() throws FileNotFoundException {
       File file = new File("C:\\Users\\Ernest\\IdeaProjects\\Multithreaded-snake-game-with-AI\\src\\application\\Highscores.txt");
       Scanner sc =new Scanner(file);

       Player1.setText(sc.nextLine());
   }

    public void EXIT_Highscores(ActionEvent actionEvent) throws IOException {
        Stage stage;
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("IntroScene.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
