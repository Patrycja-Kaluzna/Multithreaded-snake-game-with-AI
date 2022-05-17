package application.FrogPack;

import application.FruitPack.Fruit;
import application.SnakePack.Snake;
import application.WallPack.Wall;

import java.awt.*;
import java.util.List;

public interface Frog_Interface {
    void FrogClone (Frog frog);
    void GenerateFrog(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS);
    void MoveFrog(int chosenDirection);
    void moveRight();
    void moveLeft();

    void moveUp();

    void moveDown();

    void FrogBestMove(List<Snake> Snakes, List<Point> OccupiedFields, int ROWS, int COLUMNS);
   double FrogAI(List<Snake> Snakes, List<Point> OccupiedFields, int ROWS, int COLUMNS, int glem, int MAXGLEMP);
}
