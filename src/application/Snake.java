package application;

import com.sun.source.tree.DoWhileLoopTree;

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
    public int score = 0;

    public String Head = null;
    public String Color = null;

    public boolean gameOver = false;

    Snake(int LenghtOfSnake, int StartXpos, int StartYpos, String head, String color, int direction) {
        for (int i = 0; i < LenghtOfSnake; i++) {
            if(i==0){
            snakeBody.add(new Point(StartXpos, StartYpos));
        }else {
                snakeBody.add(new Point(-1, -1));
            }
        }
        snakeHead = snakeBody.get(0);
        Head = head;
        Color = color;
        Direction = direction;
    }

    public void MoveSnake(int chosenDirection) {
        if (chosenDirection == RIGHT || chosenDirection == LEFT || chosenDirection == UP || chosenDirection == DOWN) {
            Direction = chosenDirection;
        }
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

    public void Eat() {
        snakeBody.add(new Point(-1, -1));
    }

    public void SnakeBestMove(List<Snake> Snakes, List<Fruit> Foods, List<Frog> Frogs, List<Wall> Walls) {
        double wynik = 0, najWynik = -10000;
        int BestMove = 3;
        List<Point> SAVECOR = new ArrayList();
        for (int i = 0; i < snakeBody.size(); i++) {
            SAVECOR.add((Point) snakeBody.get(i).clone());
        }

        for (int a = 3; a >= 0; a--) {

            MoveSnake(a);

            wynik = SnakeAI(Snakes, Foods, Frogs, Walls, 0, 17, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            System.out.println("Wynik: "+ wynik);
            if (wynik > najWynik) {
                najWynik = wynik;
                BestMove = a;
            }

            snakeBody.clear();
            for (int i = 0; i < SAVECOR.size(); i++) {
                snakeBody.add((Point) SAVECOR.get(i).clone());
            }
            snakeHead = snakeBody.get(0);
        }
        System.out.println("BEST: "+BestMove);
        MoveSnake(BestMove);
    }

    int SnakeAI(List<Snake> Snakes, List<Fruit> Foods, List<Frog> Frogs, List<Wall> Walls, int glem, int MAXGLEMP, int alpha, int beta, boolean toKomp) {
        boolean loop = true;
        int wynik = 0;
        int najWynik = -10000;
        int PlayerDir=Snakes.get(0).Direction;
        List<Point> SAVECORCOMP = new ArrayList();
        for (int i = 0; i < snakeBody.size(); i++) {
            SAVECORCOMP.add((Point) snakeBody.get(i).clone());
        }
        List<Point> SAVECORPLAYER = new ArrayList();
        for (int i = 0; i < Snakes.get(0).snakeBody.size(); i++) {
            SAVECORPLAYER.add((Point) Snakes.get(0).snakeBody.get(i).clone());
        }
        Game.gameOver(Snakes, Walls);
        for (int i = 0; i < Snakes.size(); i++) {
            if (Snakes.get(i).gameOver == true) {
                if (i == 0) {
                    Snakes.get(i).gameOver = false;
                    return (Integer.MAX_VALUE - glem);
                } else {
                    Snakes.get(i).gameOver = false;
                    return (Integer.MIN_VALUE + glem);
                }

            }
        }

        if (glem < MAXGLEMP) {
            if (toKomp == true) {
                najWynik = Integer.MIN_VALUE;
                for (int a = 3; a >= 0 && loop; a--) {

                    MoveSnake(a);

                    wynik = SnakeAI(Snakes, Foods, Frogs, Walls, glem + 1, MAXGLEMP, alpha, beta, false);
                    snakeBody.clear();
                    for (int i = 0; i < SAVECORCOMP.size(); i++) {
                        snakeBody.add((Point) SAVECORCOMP.get(i).clone());
                    }
                    snakeHead = snakeBody.get(0);
                    if (wynik > najWynik) {
                        najWynik = wynik;
                    }
                    if (wynik > alpha) {
                        alpha = wynik;
                    }
                    if (beta <= alpha) {
                        loop = false;
                    }
                }
                return najWynik;
            } else {
                najWynik = Integer.MAX_VALUE;
                for (int a = 3; a >= 0 && loop; a--) {
                    Snakes.get(0).MoveSnake(a);
                    wynik = SnakeAI(Snakes, Foods, Frogs, Walls, glem + 1, MAXGLEMP, alpha, beta, true);
                    Snakes.get(0).snakeBody.clear();
                    for (int i = 0; i < SAVECORPLAYER.size(); i++) {
                        Snakes.get(0).snakeBody.add((Point) SAVECORPLAYER.get(i).clone());
                    }
                    Snakes.get(0).snakeHead = Snakes.get(0).snakeBody.get(0);
                    Snakes.get(0).Direction=PlayerDir;
                    if (wynik < najWynik) {
                        najWynik = wynik;
                    }
                    if (wynik < beta) {
                        beta = wynik;
                    }
                    if (beta <= alpha) {
                        loop = false;
                    }
                }
                return najWynik;
            }
        } else {
            return 0;
        }

    }


}
