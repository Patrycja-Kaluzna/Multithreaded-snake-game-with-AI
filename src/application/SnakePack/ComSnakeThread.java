package application.SnakePack;

import application.FrogPack.Frog;
import application.FruitPack.Fruit;
import application.WallPack.Wall;

import java.awt.*;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComSnakeThread implements Runnable {
    private Snake snake;
    private List<Snake> Snakes;
    private List<Fruit> Foods;
    private List<Frog> Frogs;
    private List<Wall> Walls;
    private int GameSize;
    CyclicBarrier barrier;
    public ComSnakeThread(Snake Snake, List<Snake> snakes, List<Fruit> foods, List<Frog> frogs, List<Wall> walls, int gamesize, CyclicBarrier Barrier){

        this.snake=Snake;
        this.Snakes=snakes;
        this.Foods=foods;
        this.Frogs=frogs;
        this.Walls=walls;
        this.GameSize=gamesize;
        this.barrier=Barrier;
    }

    public void run(){
        try {
            snake.SnakeBestMove(Snakes,Foods,Frogs,Walls,GameSize,barrier);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}