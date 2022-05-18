package application.FrogPack;

import application.SnakePack.Snake;

import java.awt.*;
import java.util.List;

public class FrogThread implements Runnable {
    private Frog frog;
    private List <Snake> Snakes;
    private List<Point> AllPoints;
    int ROWS;
    int COLUMNS;

    public FrogThread(Frog fro, List<Snake> snakes, List<Point> OccupiedFields, int rows, int columns){
        this.frog=fro;
        this.Snakes=snakes;
        this.AllPoints=OccupiedFields;
        this.ROWS=rows;
        this.COLUMNS=columns;
    }

    public void run(){
        frog.FrogBestMove(Snakes, AllPoints, ROWS, COLUMNS);
    }
}
