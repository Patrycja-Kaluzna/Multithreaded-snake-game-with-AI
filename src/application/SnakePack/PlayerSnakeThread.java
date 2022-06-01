package application.SnakePack;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Klasa watku sterujacego wezem.
 */
public class PlayerSnakeThread implements Runnable {

    /**
     * Waz
     */
    private Snake snake;
    /**
     * Aktualny kierunek ruchu
     */
    private int Direction;
    /**
     * Bariera synchronizujaca watki
     */
    CyclicBarrier barrier;

    /**
     * Inicjalizuje caly obiekt podanymi
     * wartosciami przy tworzeniu.
     *
     * @param Snake Waz
     * @param Dir Aktualny kierunek ruchu
     * @param Barrier Bariera synchornizujaca watki
     */
    public PlayerSnakeThread(Snake Snake, int Dir, CyclicBarrier Barrier) {
       this.snake = Snake;
       this.Direction = Dir;
       this.barrier = Barrier;
    }

    /**
     * Uruchamia watek sterujacy wezem.
     */
    public void run() {
        try {
            barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        snake.MoveSnake(Direction);
    }

}
