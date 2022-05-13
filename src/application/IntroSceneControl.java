package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class IntroSceneControl {
    @FXML
    public static AnchorPane IntroSceneParent;
    @FXML
    private Button EXIT_Button;

    //Start button handler
    public void game_click(ActionEvent actionEvent) {
        Game game= new Game();
        double size= (double) (((Node)actionEvent.getSource()).getScene().getHeight());
        game.Game_Start((Stage)((Node)actionEvent.getSource()).getScene().getWindow(),size);
    }
    public void EXIT(ActionEvent actionEvent) {
        Stage stage;
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }

}
