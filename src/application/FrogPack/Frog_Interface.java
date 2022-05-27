package application.FrogPack;

import application.FruitPack.Fruit;
import application.SnakePack.Snake;
import application.WallPack.Wall;

import java.awt.*;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public interface Frog_Interface {
    void FrogClone (Frog frog);
    void GenerateFrog(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS);
    void MoveFrog(int chosenDirection);
    void moveRight();
    void moveLeft();

    void moveUp();

    void moveDown();
    void FrogBestMove(List<Snake> Snakes, List<Point> OccupiedFields, int ROWS, int COLUMNS, CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException;
    double FrogAI(Frog SaveFrog, List<Point> OccupiedFields, int ROWS, int COLUMNS, int glem, int MAXGLEMP);
}
