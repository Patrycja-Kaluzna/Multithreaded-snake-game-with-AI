package application.Scenes;

import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;
import javafx.scene.Node;
import javafx.fxml.FXML;
import java.io.File;

/**
 * Klasa sceny widocznej po kliknieciu przycisku
 * RANKING z poziomu pierwszej sceny widocznej
 * po uruchomieniu aplikacji. Scena przestawia
 * najlepszych graczy w rankingu i ich wyniki.
 */
public class HighscoresControl {

    /**
     * Nazwa 1. gracza w rankingu
     */
    @FXML
    private Text Player1;
    /**
     * Nazwa 2. gracza w rankingu
     */
    @FXML
    private Text Player2;
    /**
     * Nazwa 3. gracza w rankingu
     */
    @FXML
    private Text Player3;
    /**
     * Nazwa 4. gracza w rankingu
     */
    @FXML
    private Text Player4;
    /**
     * Nazwa 5. gracza w rankingu
     */
    @FXML
    private Text Player5;

    /**
     * Czyta dane dotyczace najlepszych graczy w rankingu i ich
     * wynikow z pliku oraz wpisuje je w odpowiednie pola klasy.
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        File file = new File("src/application/HighScoresPack/Highscores.txt");
        Scanner sc = new Scanner(file);
        Player1.setText("1. " + sc.nextLine());
        Player2.setText("2. " + sc.nextLine());
        Player3.setText("3. " + sc.nextLine());
        Player4.setText("4. " + sc.nextLine());
        Player5.setText("5. " + sc.nextLine());
    }

    /**
     * Obsluguje wcisniecie przycisku EXIT, czyli
     * powrot do pierwszej sceny widocznej po
     * uruchomieniu aplikacji.
     */
    public void EXIT_Highscores(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("IntroScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
