package application.SnakePack;

import application.FrogPack.Frog;
import application.FruitPack.Fruit;
import application.GamePack.Game;
import application.WallPack.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Snake implements Snake_Interface {
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

    public void CloneSnake(Snake snake){
        this.Direction=snake.Direction;
//        this.gameOver= snake.gameOver;
//        this.Color=snake.Color;
//        this.Head= snake.Head;
        this.score=snake.score;
        this.snakeBody.clear();
        for (int i = 0; i < snake.snakeBody.size(); i++) {
            this.snakeBody.add((Point) snake.snakeBody.get(i).clone());
        }
        this.snakeHead=this.snakeBody.get(0);

    }

    public Snake(int LenghtOfSnake, int StartXpos, int StartYpos, String head, String color, int direction) {
        for (int i = 0; i < LenghtOfSnake; i++) {
            if (i == 0) {
                snakeBody.add(new Point(StartXpos, StartYpos));
            } else {
                snakeBody.add(new Point(-1, -1));
            }
        }
        snakeHead = snakeBody.get(0);
        Head = head;
        Color = color;
        Direction = direction;
    }

    public Snake(Snake snake){
        this.Direction=snake.Direction;
        this.gameOver= snake.gameOver;
        this.Color=snake.Color;
        this.Head= snake.Head;
        this.score=snake.score;
        this.snakeBody.clear();
        for (int i = 0; i < snake.snakeBody.size(); i++) {
            this.snakeBody.add((Point) snake.snakeBody.get(i).clone());
        }
        this.snakeHead=this.snakeBody.get(0);
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

    public void moveRight() {
        snakeHead.x++;
    }

    public void moveLeft() {
        snakeHead.x--;
    }

    public void moveUp() {
        snakeHead.y--;
    }

    public void moveDown() {
        snakeHead.y++;
    }

    public void Eat() {
        snakeBody.add(new Point(-1, -1));
    }

    public void SnakeBestMove(List<Snake> Snakes, List<Fruit> Foods, List<Frog> Frogs, List<Wall> Walls, int GameSize, CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException {
        int wynik = 0, najWynik = -10000;
        int BestMove = 3;
        List<Fruit> FoodsSave=new ArrayList();
        List<Fruit> FoodsSaveTEMP=new ArrayList();

        List<Frog> FrogsSave=new ArrayList<>();
        List<Frog> FrogsSaveTEMP=new ArrayList<>();

        List<Snake> SnakesTEMP=new ArrayList<>();
        Snake ComSaveSnake = new Snake(Snakes.get(1));
        Snake PlayerSaveSnake = new Snake(Snakes.get(0));



        for (int i = 0; i < Foods.size(); i++) {
            FoodsSave.add(new Fruit(Foods.get(i)));
        }
        for (int i = 0; i < Foods.size(); i++) {
            FoodsSaveTEMP.add(new Fruit(Foods.get(i)));
        }
        for (int i = 0; i < Frogs.size(); i++) {
            FrogsSave.add(new Frog(Frogs.get(i)));
        }
        for (int i = 0; i < Frogs.size(); i++) {
            FrogsSaveTEMP.add(new Frog(Frogs.get(i)));
        }

        for (int i = 0; i < Snakes.size(); i++) {
            SnakesTEMP.add( new Snake(Snakes.get(i)));
        }
        barrier.await();
        for (int a = 3; a >= 0; a--) {

            SnakesTEMP.get(1).MoveSnake(a);
            Game.ScoringForAI(SnakesTEMP,FoodsSaveTEMP,FrogsSaveTEMP);
            wynik = SnakeAI(SnakesTEMP, FoodsSaveTEMP, FrogsSaveTEMP, Walls, 0, 10, GameSize, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
           // System.out.println(wynik);
            if (wynik > najWynik) {
                najWynik = wynik;
                BestMove = a;
            }

            SnakesTEMP.get(1).CloneSnake(ComSaveSnake);
            SnakesTEMP.get(0).CloneSnake(PlayerSaveSnake);
            FoodsSaveTEMP.clear();
            for (int i = 0; i < FoodsSave.size(); i++) {
                FoodsSaveTEMP.add(new Fruit(FoodsSave.get(i)));
            }
            FrogsSaveTEMP.clear();
            for (int i = 0; i < FrogsSave.size(); i++) {
                FrogsSaveTEMP.add(new Frog(FrogsSave.get(i)));
            }
        }
       //System.out.println(BestMove+ " TO BEST");
        MoveSnake(BestMove);
    }

    public int SnakeAI(List<Snake> Snakes, List<Fruit> Foods, List<Frog> Frogs, List<Wall> Walls, int glem, int MAXGLEMP,int GameSize, int alpha, int beta, boolean toKomp) {
        boolean loop = true;
        int wynik = 0;
        int najWynik = -10000;

        List<Fruit> FoodsSave=new ArrayList();

        Snake PlayerSaveSnake = new Snake(Snakes.get(0));
        Snake ComSaveSnake = new Snake(Snakes.get(1));


        for (int i = 0; i < Foods.size(); i++) {
            FoodsSave.add(new Fruit(Foods.get(i)));
        }

//        for (int i = 0; i < Frogs.size(); i++) {
//            FrogsSave.add(new Frog(Frogs.get(i)));
//        }


        Game.gameOver(Snakes, Walls);
        for (int i = Snakes.size()-1; i >=0; i--) {
            if (Snakes.get(i).gameOver == true) {
                if(i!=0){
                    for (int a = Snakes.size()-1; a >=0; a--){
                    Snakes.get(a).gameOver = false;
                    }
                    return (Integer.MIN_VALUE );
                }
                if (i == 0) {
                    for (int a = Snakes.size()-1; a >=0; a--){
                        Snakes.get(a).gameOver = false;
                    }
                    return (Integer.MAX_VALUE );
                }
            }
        }

        if (glem < MAXGLEMP) {
            if (toKomp == true) {
                najWynik = Integer.MIN_VALUE;
                for (int a = 3; a >= 0 && loop; a--) {

                    Snakes.get(1).MoveSnake(a);
                    Game.ScoringForAI(Snakes,Foods,Frogs);
                    wynik = SnakeAI(Snakes, Foods, Frogs, Walls, glem + 1, MAXGLEMP,GameSize, alpha, beta, false);
                    Snakes.get(1).CloneSnake(ComSaveSnake);
                    Foods.clear();
                    for (int i = 0; i < FoodsSave.size(); i++) {
                        Foods.add(new Fruit(FoodsSave.get(i)));
                    }
//                    Frogs.clear();
//                    for (int i = 0; i < FrogsSave.size(); i++) {
//                        Frogs.add(new Frog(FrogsSave.get(i)));
//                    }

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
                    Game.ScoringForAI(Snakes,Foods,Frogs);
                    wynik = SnakeAI(Snakes, Foods, Frogs, Walls, glem + 1, MAXGLEMP,GameSize, alpha, beta, true);
                    Snakes.get(0).CloneSnake(PlayerSaveSnake);
                    Foods.clear();
                    for (int i = 0; i < FoodsSave.size(); i++) {
                        Foods.add(new Fruit(FoodsSave.get(i)));
                    }
//                    Frogs.clear();
//                    for (int i = 0; i < FrogsSave.size(); i++) {
//                        Frogs.add(new Frog(FrogsSave.get(i)));
//                    }
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
            int Total_distance=0;
            for (int i = 0; i < Foods.size(); i++) {
                Total_distance+=(int)Math.ceil(Math.hypot( Snakes.get(1).snakeHead.getX() - Foods.get(i).Coordinates.getX(),  Snakes.get(1).snakeHead.getY() - Foods.get(i).Coordinates.getY()));
            }
            return ((Snakes.get(1).score)*10000)-Total_distance;
        }
    }


}
