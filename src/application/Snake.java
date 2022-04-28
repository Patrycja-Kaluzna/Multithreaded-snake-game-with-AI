package application;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    public List<Point> snakeBody = new ArrayList();
    public Point snakeHead = null;
    public int Direction = 0;
    public int score =0;

    public String Head=null;
    public String Color=null;

    public boolean gameOver= false;

    Snake(int LenghtOfSnake, int StartXpos, int StartYpos, String head, String color) {
        for (int i = 0; i < LenghtOfSnake; i++) {
            snakeBody.add(new Point(StartXpos, StartYpos));
        }
        snakeHead = snakeBody.get(0);
        Head=head;
        Color=color;
    }

    public void MoveSnake() {
        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }
        switch (Direction) {
            case RIGHT:

                moveRight();
                break;
            case LEFT:

                moveLeft();
                break;
            case UP:
                moveUp();

                break;
            case DOWN:

                moveDown();
                break;
        }
    }

    private void moveRight() {
        snakeHead.x++;
    }

    private void moveLeft() {
        snakeHead.x--;
    }

    private void moveUp() {
        snakeHead.y--;
    }

    private void moveDown() {
        snakeHead.y++;
    }
    public void Eat(){
        snakeBody.add(new Point(-1, -1));
    }


}
