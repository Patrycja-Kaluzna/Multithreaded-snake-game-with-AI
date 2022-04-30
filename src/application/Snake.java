package application;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake implements Player {
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    List<Point> Body = new ArrayList();
    Point Head = null;

    String headImage = null;
    String imageColor = null;

    int Direction = 0;

    int Score = 0;
    boolean isGameOver = false;

    Snake(int snakeLength, int startXPos, int startYPos, String newHeadImage, String newHeadColor, int newDirection) {
        for (int i = 0; i < snakeLength; i++) {
            Body.add(new Point(startXPos, startYPos));
        }
        Head = Body.get(0);

        headImage = newHeadImage;
        imageColor = newHeadColor;

        Direction = newDirection;
    }

    private void moveRight() {
        Head.x++;
    }

    private void moveLeft() {
        Head.x--;
    }

    private void moveUp() {
        Head.y--;
    }

    private void moveDown() {
        Head.y++;
    }

    public void Move() {
        for (int i = Body.size() - 1; i >= 1; i--) {
            Body.get(i).x = Body.get(i - 1).x;
            Body.get(i).y = Body.get(i - 1).y;
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

    public void Eat() {
        Body.add(new Point(-1, -1));
    }
}