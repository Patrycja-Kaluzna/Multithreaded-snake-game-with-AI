package application.FruitPack;

import application.FrogPack.Frog;
import application.SnakePack.Snake;
import application.WallPack.Wall;

import java.util.List;

public interface Fruit_Interface {
    void Generate(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS);
}
