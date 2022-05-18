package application.FruitPack;

import application.FrogPack.Frog;
import application.SnakePack.Snake;
import application.WallPack.Wall;

import java.awt.*;
import java.util.List;

public class FruitThread implements Runnable{
    private Fruit fruit;
    private List<Snake> Snakes;
    private List<Fruit> Foods;
    private List<Wall> Walls;
    private List<Frog> Frogs;
    int ROWS;
    int COLUMNS;

    public FruitThread(Fruit fru,List <Snake> snakes, List<Fruit> foods, List<Wall> walls, List<Frog> frogs, int rows, int columns){
       this.fruit=fru;
       this.Snakes=snakes;
       this.Foods=foods;
       this.Walls=walls;
       this.Frogs=frogs;
       this.ROWS=rows;
       this.COLUMNS=columns;
    }

    public void run(){
        fruit.Generate(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS);
    }

}
