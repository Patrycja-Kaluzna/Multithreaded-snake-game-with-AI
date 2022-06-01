package application.Scenes;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import application.GamePack.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;

/**
 * Klasa pierwszej sceny widocznej
 * po uruchomieniu aplikacji.
 */
public class IntroSceneControl {

    /**
     *
     */
    @FXML
    public static AnchorPane IntroSceneParent;
    /**
     * Przycisk EXIT
     */
    @FXML
    private Button EXIT_Button;

    /**
     * Obsluguje wcisniecie przycisku
     * PLAY, czyli rozpoczecie gry.
     */
    public void game_click(ActionEvent actionEvent) throws IOException {
        Game game = new Game();
        double size = (double) (((Node) actionEvent.getSource()).getScene().getHeight());
        game.Game_Start((Stage) ((Node) actionEvent.getSource()).getScene().getWindow(), size);
    }

    /**
     * Obsluguje wcisniecie przycisku EXIT,
     * czyli wyjscie z aplikacji.
     */
    public void EXIT(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Obsluguje wcisniecie przycisku RANKING,
     * czyli przejscie do sceny przedtsawiajacej
     * najlepszych graczy w rankingu i ich wyniki.
     */
    public void Highscores_click(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HighscoresScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
