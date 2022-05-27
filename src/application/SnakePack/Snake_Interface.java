package application.SnakePack;

import application.FrogPack.Frog;
import application.FruitPack.Fruit;
import application.WallPack.Wall;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public interface Snake_Interface {
    void CloneSnake(Snake snake);

    void MoveSnake(int chosenDirection);

    void moveRight();

    void moveLeft();

    void moveUp();

    void moveDown();

    void Eat();
    void SnakeBestMove(List<Snake> Snakes, List<Fruit> Foods, List<Frog> Frogs, List<Wall> Walls, int GameSize, CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException;
    int SnakeAI(List<Snake> Snakes, List<Fruit> Foods, List<Frog> Frogs, List<Wall> Walls, int glem, int MAXGLEMP,int GameSize, int alpha, int beta, boolean toKomp);
}
