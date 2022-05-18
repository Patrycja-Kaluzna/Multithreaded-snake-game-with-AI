package application.SnakePack;

import application.FrogPack.Frog;
import application.FruitPack.Fruit;
import application.WallPack.Wall;

import java.awt.*;
import java.util.List;

public class ComSnakeThread implements Runnable {
    private Snake snake;
    private List<Snake> Snakes;
    private List<Fruit> Foods;
    private List<Frog> Frogs;
    private List<Wall> Walls;
    private int GameSize;
    public ComSnakeThread(Snake Snake, List<Snake> snakes, List<Fruit> foods, List<Frog> frogs, List<Wall> walls, int gamesize){

        this.snake=Snake;
        this.Snakes=snakes;
        this.Foods=foods;
        this.Frogs=frogs;
        this.Walls=walls;
        this.GameSize=gamesize;
    }

    public void run(){
        snake.SnakeBestMove(Snakes,Foods,Frogs,Walls,GameSize);
    }
}