package application.SnakePack;

import application.FrogPack.Frog;

import java.awt.*;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class PlayerSnakeThread implements Runnable {
    private Snake snake;
    private int Direction;
    CyclicBarrier barrier;
    public PlayerSnakeThread(Snake Snake,int Dir, CyclicBarrier Barrier){
       this.snake=Snake;
       this.Direction=Dir;
       this.barrier=Barrier;
    }

    public void run(){
        try {
            barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        snake.MoveSnake(Direction);
    }

}
