package application.FrogPack;

import application.SnakePack.Snake;

import java.awt.*;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FrogThread implements Runnable {
    private Frog frog;
    private List <Snake> Snakes;
    private List<Point> AllPoints;
    int ROWS;
    int COLUMNS;
    CyclicBarrier barrier;

    public FrogThread(Frog fro, List<Snake> snakes, List<Point> OccupiedFields, int rows, int columns, CyclicBarrier Barrier){
        this.frog=fro;
        this.Snakes=snakes;
        this.AllPoints=OccupiedFields;
        this.ROWS=rows;
        this.COLUMNS=columns;
        this.barrier=Barrier;
    }

    public void run(){
        try {
            frog.FrogBestMove(Snakes, AllPoints, ROWS, COLUMNS,barrier);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
