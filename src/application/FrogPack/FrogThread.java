package application.FrogPack;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import application.SnakePack.Snake;
import java.util.List;
import java.awt.*;

/**
 * Klasa watku sterujacego zaba.
 */
public class FrogThread implements Runnable {

    /**
     * Zaba
     */
    private Frog frog;
    /**
     * Lista wezy na planszy
     */
    private List <Snake> Snakes;
    /**
     * Lista zajetych pol planszy
     */
    private List<Point> AllPoints;
    /**
     * Liczba wierszy planszy
     */
    int ROWS;
    /**
     * Liczba kolumn planszy
     */
    int COLUMNS;
    /**
     * Bariera synchornizujaca watki
     */
    CyclicBarrier barrier;

    /**
     * Inicjalizuje caly obiekt podanymi
     * wartosciami przy tworzeniu.
     *
     * @param fro Zaba
     * @param snakes Lista wezy na planszy
     * @param OccupiedFields Lista zajetych pol planszy
     * @param rows Liczba wieszy planszy
     * @param columns Liczba kolumn planszy
     * @param Barrier Bariera synchornizujaca watki
     */
    public FrogThread(Frog fro, List<Snake> snakes, List<Point> OccupiedFields, int rows, int columns, CyclicBarrier Barrier) {
        this.frog = fro;
        this.Snakes = snakes;
        this.AllPoints = OccupiedFields;
        this.ROWS = rows;
        this.COLUMNS = columns;
        this.barrier = Barrier;
    }

    /**
     * Uruchamia watek sterujacy zaba.
     */
    public void run() {
        try {
            frog.FrogBestMove(Snakes, AllPoints, ROWS, COLUMNS,barrier);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
