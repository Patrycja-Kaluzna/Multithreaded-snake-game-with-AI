package application.Scenes;

import application.HighScoresPack.Scores;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import application.GamePack.Game;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import java.util.Collections;
import javafx.scene.Parent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;
import javafx.scene.Node;
import javafx.fxml.FXML;
import java.util.List;
import java.io.File;

/**
 * Klasa sceny widocznej po przegranej grze.
 */
public class GameOverControl {

    /**
     * Lista puntkow
     */
    private List<Scores> scores = new ArrayList();
    /**
     * Punkty gracza
     */
    @FXML
    private Text SCORE;
    /**
     * Nazwa gracza
     */
    @FXML
    private TextField NAMEBOX;
    /**
     * Przycisk MENU
     */
    @FXML
    private Button MENU;
    /**
     * Przycisk TRY AGAIN
     */
    @FXML
    private Button TRY_AGAIN;

    /**
     * Inicjalizuje scene
     */
    @FXML
   public void initialize() throws FileNotFoundException {}

    /**
     * Obluguje wcisniecie przycisku MENU. Jesli nie
     * wpisano nazwy gracza, to otwiera sie okienko
     * z ostrzezeniem, a jesli wpisano nazwe gracza,
     * to zapisuje sie jego wynik do pliku.
     */
    public void MENU_click(ActionEvent actionEvent) throws IOException {
        if (NAMEBOX.getText() != "") {
            SaveScore();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("IntroScene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NAME ERROR");
            alert.setHeaderText("Incorrect name");
            alert.setContentText("Please enter the correct name to save score");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {}
            });
        }
    }

    /**
     * Obsluguje wcisniecie przycisku TRY AGAIN. Jesli nie
     * wpisano nazwy gracza, to otwiera sie okienko z
     * ostrzezeniem, a jesli wpisano nazwe gracza,
     * to zapisuje sie jego wynik do pliku i
     * ropoczyna nowa gre.
     */
    public void TRY_AGAIN_click(ActionEvent actionEvent) throws IOException {
        if (NAMEBOX.getText() != "") {
            SaveScore();
            Game game = new Game();
            double size = (double) (((Node) actionEvent.getSource()).getScene().getHeight());
            game.Game_Start((Stage) ((Node) actionEvent.getSource()).getScene().getWindow(), size);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NAME ERROR");
            alert.setHeaderText("Incorrect name");
            alert.setContentText("Please enter the correct name to save score");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {}
            });
        }
    }

    /**
     * Ustawia wartosc pola SCORE dotyczacego punktow
     * gracza na "SCORE: " + podana liczba puntkow.
     *
     * @param score Liczba punktow
     */
    public void setScore(String score){
        SCORE.setText("SCORE: " + score);
    }

    /**
     * Zapisuje wyniki do pliku w kolejnosci
     * od najwiekszego do najmniejszego.
     */
    public void SaveScore() throws FileNotFoundException {
        scores.clear();
        File file = new File("src/application/HighScoresPack/Highscores.txt");
        Scanner sc = new Scanner(file);
        String ScoreSplit[] = SCORE.getText().split(" ");
        scores.add(new Scores(NAMEBOX.getText(), Integer.parseInt(ScoreSplit[1])));
        while (sc.hasNextLine()) {
            String loaded[] = sc.nextLine().split(" ");
            scores.add(new Scores(new String(loaded[0]), Integer.parseInt(loaded[1])));
        }
        Collections.sort(scores);
        File file1 = new File("src/application/HighScoresPack/Highscores.txt");
        PrintWriter writer = new PrintWriter(file1);
        for (Scores s : scores) {
            writer.println(s.name + " " + Integer.toString(s.score));
        }
        writer.close();
    }

}
