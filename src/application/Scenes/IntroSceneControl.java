package application.Scenes;

import application.Game;
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

import java.io.IOException;

public class IntroSceneControl {
    @FXML
    public static AnchorPane IntroSceneParent;
    @FXML
    private Button EXIT_Button;

    //Start button handler
    public void game_click(ActionEvent actionEvent) throws IOException {
        Game game= new Game();
        double size= (double) (((Node)actionEvent.getSource()).getScene().getHeight());
        game.Game_Start((Stage)((Node)actionEvent.getSource()).getScene().getWindow(),size);
    }
    public void EXIT(ActionEvent actionEvent) {
        Stage stage;
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }

    public void Highscores_click (ActionEvent actionEvent) throws IOException {
        Stage stage;
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HighscoresScene.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
