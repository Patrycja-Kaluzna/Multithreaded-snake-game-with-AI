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
    int numbi;
    public FrogThread(Frog fro, List<Snake> snakes, List<Point> OccupiedFields, int rows, int columns,int numb){
        this.frog=fro;
        this.Snakes=snakes;
        this.AllPoints=OccupiedFields;
        this.ROWS=rows;
        this.COLUMNS=columns;
        this.numbi=numb;
    }

    public void run(){
        System.out.println(numbi);
        frog.FrogBestMove(Snakes, AllPoints, ROWS, COLUMNS);
    }
}
