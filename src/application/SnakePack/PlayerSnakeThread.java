package application.SnakePack;

import application.FrogPack.Frog;

import java.awt.*;
import java.util.List;

public class PlayerSnakeThread implements Runnable {
    private Snake snake;
    private int Direction;
    public PlayerSnakeThread(Snake Snake,int Dir){
       this.snake=Snake;
       this.Direction=Dir;
    }

    public void run(){
        snake.MoveSnake(Direction);
    }

}
