package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameOverControl {
    private List<Scores> scores = new ArrayList();
    @FXML
    private Text SCORE;
    @FXML
    private TextField NAMEBOX;
    @FXML
    private Button MENU;
    @FXML
    private Button TRY_AGAIN;


    @FXML
   public void initialize() throws FileNotFoundException {

   }

    public void MENU_click(ActionEvent actionEvent) throws IOException {
        if(NAMEBOX.getText()!=""){
            SaveScore();
            Stage stage;
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("IntroScene.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NAME ERROR");
            alert.setHeaderText("Incorrect name");
            alert.setContentText("Please enter the correct name to save score");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });
        }

    }
    public void TRY_AGAIN_click(ActionEvent actionEvent) throws IOException {
        if(NAMEBOX.getText()!=""){
            Game game= new Game();
            double size= (double) (((Node)actionEvent.getSource()).getScene().getHeight());
            game.Game_Start((Stage)((Node)actionEvent.getSource()).getScene().getWindow(),size);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NAME ERROR");
            alert.setHeaderText("Incorrect name");
            alert.setContentText("Please enter the correct name to save score");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });
        }
    }

    public void setScore(String score){
        SCORE.setText("SCORE: "+score);
    }

    public void SaveScore() throws FileNotFoundException {
        scores.clear();
        File file = new File("src/application/Highscores.txt");
        Scanner sc =new Scanner(file);
        while (sc.hasNextLine()){
            String loaded[] =sc.nextLine().split(" ");
            scores.add(new Scores(new String(loaded[0]),Integer.parseInt(loaded[1])));
        }
        Collections.sort(scores);
        for(Scores s : scores){
            System.out.println(s.name+" "+Integer.toString(s.score));
        }
    }
}
