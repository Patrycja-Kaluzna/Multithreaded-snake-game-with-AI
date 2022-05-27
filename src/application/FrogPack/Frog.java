package application.FrogPack;

import application.FruitPack.Fruit;
import application.SnakePack.Snake;
import application.WallPack.Wall;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
public class Frog implements Frog_Interface{
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private static final int STOP = 4;
    public int Direction = 2;
    public Image frogImage = new Image("/img/frog.png");
    public Point coordinates = new Point(0, 0);
    private List<Snake> SnakesTEMP=new ArrayList<>();

    public Frog(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS) {
        GenerateFrog(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS);
    }

    public void FrogClone (Frog frog){
        this.coordinates=(Point) frog.coordinates.clone();
        this.Direction= frog.Direction;
    }
    public Frog(Frog frog) {
        this.coordinates=(Point) frog.coordinates.clone();
        this.Direction= frog.Direction;
    }
    public Frog(Point Coordinates, int direction) {
        this.coordinates= (Point) Coordinates.clone();
        this.Direction= direction;
    }

    public void GenerateFrog(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS) {
        start:
        while (true) {
            coordinates.x = (int) (Math.random() * ROWS);
            coordinates.y = (int) (Math.random() * COLUMNS);

            for (Snake snake : Snakes) {
                for (int a = 0; a < snake.snakeBody.size(); a++) {
                    if ((snake.snakeBody.get(a).getX() == coordinates.x && snake.snakeBody.get(a).getY() == coordinates.y)) {
                        continue start;
                    }
                }
            }
            for (Fruit food : Foods) {
                if ((food.Coordinates.getX() == coordinates.x && food.Coordinates.getY() == coordinates.y)) {
                    continue start;
                }
            }
            for (Wall wall : Walls) {
                for (int a = 0; a < wall.segments.size(); a++) {
                    if ((wall.segments.get(a).getX() == coordinates.x && wall.segments.get(a).getY() == coordinates.y)) {
                        continue start;
                    }
                }
            }
            for (Frog frog : Frogs) {
                if ((frog.coordinates.getX() == coordinates.x && frog.coordinates.getY() == coordinates.y)) {
                    continue start;
                }
            }
            break;
        }
    }

    public void MoveFrog(int chosenDirection) {
        if (chosenDirection == RIGHT || chosenDirection == LEFT || chosenDirection == UP || chosenDirection == DOWN || chosenDirection == STOP) {
            this.Direction = chosenDirection;
        } else {
            this.Direction = STOP;
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
            case STOP:
                break;
        }
    }

    public void moveRight() {
        this.coordinates.x++;
    }

    public void moveLeft() {
        this.coordinates.x--;
    }

    public void moveUp() {
        this.coordinates.y--;
    }

    public void moveDown() {
        this.coordinates.y++;
    }


    public void FrogBestMove(List<Snake> Snakes, List<Point> OccupiedFields, int ROWS, int COLUMNS,CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException {
        double wynik = 0, najWynik = -10000;
        boolean FREE;
        int BestMove = 4;
        Point SAVECOR;
        int SAVEDIR = Direction;
        SnakesTEMP.clear();
        for (int i = 0; i < Snakes.size(); i++) {
            SnakesTEMP.add( new Snake(Snakes.get(i)));
        }
        Frog SaveFrog = new Frog(this.coordinates,this.Direction);
        barrier.await();
        for (int a = 4; a >=0; a--) {
            FREE = true;
            SAVECOR = (Point) SaveFrog.coordinates.clone();
            SaveFrog.MoveFrog(a);
            for (Point occupiedField : OccupiedFields) {
                if (occupiedField.getX() ==SaveFrog.coordinates.getX() && occupiedField.getY() == SaveFrog.coordinates.getY()) {
                    FREE = false;
                }
            }

            if (FREE && SaveFrog.coordinates.getX() >= 0 && SaveFrog.coordinates.getX() < COLUMNS && SaveFrog.coordinates.getY() >= 0 && SaveFrog.coordinates.getY() < ROWS) {
                wynik = FrogAI(SaveFrog, OccupiedFields, ROWS, COLUMNS, 0, 4);
                if (wynik > najWynik) {
                    najWynik = wynik;
                    BestMove = a;
                }
            }
            SaveFrog.coordinates = SAVECOR;
        }
        MoveFrog(BestMove);
        System.out.println(SaveFrog.coordinates);
        if (BestMove == 4) {
            Direction = SAVEDIR;
        }
    }

    public double FrogAI(Frog SaveFrog, List<Point> OccupiedFields, int ROWS, int COLUMNS, int glem, int MAXGLEMP) {
        double wynik = 0;
        double najWynik = -10000;
        boolean FREE;
        Point SAVECOR;
        if (glem < MAXGLEMP) {
            najWynik = -10000;
            for (int a = 4; a >= 0; a--) {
                FREE = true;
                SAVECOR = (Point) SaveFrog.coordinates.clone();
                SaveFrog.MoveFrog(a);
                for (Point occupiedField : OccupiedFields) {
                    if (occupiedField.getX() == SaveFrog.coordinates.getX() && occupiedField.getY() == SaveFrog.coordinates.getY()) {
                        FREE = false;
                    }
                }

                if (FREE && SaveFrog.coordinates.getX() >= 0 && SaveFrog.coordinates.getX() < COLUMNS && SaveFrog.coordinates.getY() >= 0 && SaveFrog.coordinates.getY() < ROWS) {
                    wynik = FrogAI(SaveFrog, OccupiedFields, ROWS, COLUMNS, (glem+1), MAXGLEMP);
                    if (wynik > najWynik) {
                        najWynik = wynik;
                    }
                }
                SaveFrog.coordinates = SAVECOR;
            }
            return najWynik;
        }
        int TotalDistance=0;
        int minDistance=1000000000;
        int pom;
        for(int i=0; i<SnakesTEMP.size();i++){
            pom=(int)Math.round( Math.hypot(SnakesTEMP.get(i).snakeHead.getX() - SaveFrog.coordinates.getX(), SnakesTEMP.get(i).snakeHead.getY() - SaveFrog.coordinates.getY()));
            if(minDistance>pom){
                minDistance=pom;
            }
            TotalDistance+= pom;
        }
        TotalDistance=TotalDistance*(minDistance+1);
        //return Math.hypot(Snakes.get(0).snakeHead.getX() - coordinates.getX(), Snakes.get(0).snakeHead.getY() - coordinates.getY());
        return  TotalDistance;
    }
}
