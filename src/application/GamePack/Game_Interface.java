package application.GamePack;

import javafx.scene.canvas.GraphicsContext;
import application.SnakePack.Snake;
import application.FrogPack.Frog;
import java.io.IOException;
import javafx.stage.Stage;

/**
 *
 */
public interface Game_Interface {

    /**
     *
     * @param primaryStage
     * @param size
     */
    void Game_Start(Stage primaryStage, double size);
    /**
     *
     * @param gc
     */
    void drawBackground(GraphicsContext gc);
    /**
     *
     * @param gc
     */
    void drawFood(GraphicsContext gc);
    /**
     *
     * @param gc
     */
    void drawWall(GraphicsContext gc);
    /**
     *
     * @param gc
     * @param frog
     */
    static void drawFrog(GraphicsContext gc, Frog frog) {}
    /**
     *
     * @param gc
     * @param snake
     */
    static void drawSnake(GraphicsContext gc, Snake snake) {}
    /**
     *
     */
    void drawScore();
    /**
     *
     * @throws InterruptedException
     * @throws IOException
     */
    void drawGameOver()throws InterruptedException, IOException;
    /**
     *
     */
    void SumOfOTakenPoints();
    /**
     *
     * @param WithOutThis
     */
    void SumOfOTakenPoints(Frog WithOutThis);

}
