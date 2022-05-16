package application.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Text Player2;
    @FXML
    private Text Player3;
    @FXML
    private Text Player4;
    @FXML
    private Text Player5;

   @FXML
   public void initialize() throws FileNotFoundException {
       File file = new File("src/application/Highscores.txt");
       Scanner sc =new Scanner(file);

       Player1.setText("1. "+sc.nextLine());
       Player2.setText("2. "+sc.nextLine());
       Player3.setText("3. "+sc.nextLine());
       Player4.setText("4. "+sc.nextLine());
       Player5.setText("5. "+sc.nextLine());
   }

    public void EXIT_Highscores(ActionEvent actionEvent) throws IOException {
        Stage stage;
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Scenes/IntroScene.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
