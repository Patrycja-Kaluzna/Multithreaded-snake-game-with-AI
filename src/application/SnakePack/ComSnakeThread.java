package application.SnakePack;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import application.FruitPack.Fruit;
import application.FrogPack.Frog;
import application.WallPack.Wall;
import java.util.List;

/**
 * Klasa watku sterujacego wezem ze sztuczna inteligencja.
 */
public class ComSnakeThread implements Runnable {

    /**
     * Waz
     */
    private Snake snake;
    /**
     * Lista wezy na planszy
     */
    private List<Snake> Snakes;
    /**
     * Lista owocow na planszy
     */
    private List<Fruit> Foods;
    /**
     * Lista zab na planszy
     */
    private List<Frog> Frogs;
    /**
     * Lista scian na planszy
     */
    private List<Wall> Walls;
    /**
     * Rozmiar planszy
     */
    private int GameSize;
    /**
     * Bariera synchronizujaca watki
     */
    CyclicBarrier barrier;

    /**
     * Inicjalizuje caly obiekt podanymi
     * wartosciami przy tworzeniu.
     *
     * @param Snake Waz
     * @param snakes Lista wezy na planszy
     * @param foods Lista owocow na planszy
     * @param frogs Lista zab na planszy
     * @param walls Lista scian na planszy
     * @param gamesize Rozmiar planszy
     * @param Barrier Bariera synchronizujaca watki
     */
    public ComSnakeThread(Snake Snake, List<Snake> snakes, List<Fruit> foods, List<Frog> frogs, List<Wall> walls, int gamesize, CyclicBarrier Barrier) {
        this.snake = Snake;
        this.Snakes = snakes;
        this.Foods = foods;
        this.Frogs = frogs;
        this.Walls = walls;
        this.GameSize = gamesize;
        this.barrier = Barrier;
    }

    /**
     * Uruchamia watek sterujacy wezem ze sztyczna inteligencja.
     */
    public void run() {
        try {
            snake.SnakeBestMove(Snakes, Foods, Frogs, Walls, GameSize, barrier);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
