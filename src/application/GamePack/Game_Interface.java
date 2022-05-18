package application.GamePack;

import application.FrogPack.Frog;
import application.FruitPack.Fruit;
import application.SnakePack.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public interface Game_Interface {
    void Game_Start(Stage primaryStage, double size);
    void drawBackground(GraphicsContext gc);
    void drawFood(GraphicsContext gc);
    void drawWall(GraphicsContext gc);
    static void drawFrog(GraphicsContext gc, Frog frog){

    }

    static void drawSnake(GraphicsContext gc, Snake snake) {

    }

    void drawScore();
    void drawGameOver()throws InterruptedException, IOException;
    void SumOfOTakenPoints();
    void SumOfOTakenPoints(Frog WithOutThis);
}
