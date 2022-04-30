package application;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.List;

public class Frog {
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private static final int STOP = 4;
    public int Direction = 2;
    public Image frogImage = new Image("/img/frog.png");
    public Point coordinates = new Point(0, 0);

    Frog(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS) {
        GenerateFrog(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS);
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

    private void moveRight() {
        this.coordinates.x++;
    }

    private void moveLeft() {
        this.coordinates.x--;
    }

    private void moveUp() {
        this.coordinates.y--;
    }

    private void moveDown() {
        this.coordinates.y++;
    }


    void FrogBestMove(List<Snake> Snakes, List<Point> OccupiedFields, int ROWS, int COLUMNS) {
        double wynik = 0, najWynik = -10000;
        boolean FREE;
        int BestMove = 4;
        Point SAVECOR;
        int SAVEDIR = Direction;


        for (int a = 4; a >=0; a--) {
            FREE = true;
            SAVECOR = (Point) coordinates.clone();
            MoveFrog(a);
            for (Point occupiedField : OccupiedFields) {
                if (occupiedField.getX() == coordinates.getX() && occupiedField.getY() == coordinates.getY()) {
                    FREE = false;
                }
            }

            if (FREE && coordinates.getX() >= 0 && coordinates.getX() < COLUMNS && coordinates.getY() >= 0 && coordinates.getY() < ROWS) {
                wynik = FrogAI(Snakes, OccupiedFields, ROWS, COLUMNS, 0, 4);
                if (wynik > najWynik) {
                    najWynik = wynik;
                    BestMove = a;
                }
            }
            coordinates = SAVECOR;
        }
        System.out.println(BestMove);
        MoveFrog(BestMove);
        if (BestMove == 4) {
            Direction = SAVEDIR;
        }
    }

    private double FrogAI(List<Snake> Snakes, List<Point> OccupiedFields, int ROWS, int COLUMNS, int glem, int MAXGLEMP) {
        double wynik = 0;
        double najWynik = -10000;
        boolean FREE;
        Point SAVECOR;
        int SAVEDIR = Direction;
        if (glem < MAXGLEMP) {
            najWynik = -10000;
            for (int a = 4; a >= 0; a--) {
                FREE = true;
                SAVECOR = (Point) coordinates.clone();
                MoveFrog(a);
                for (Point occupiedField : OccupiedFields) {
                    if (occupiedField.getX() == coordinates.getX() && occupiedField.getY() == coordinates.getY()) {
                        FREE = false;
                    }
                }

                if (FREE && coordinates.getX() >= 0 && coordinates.getX() < COLUMNS && coordinates.getY() >= 0 && coordinates.getY() < ROWS) {
                    wynik = FrogAI(Snakes, OccupiedFields, ROWS, COLUMNS, (glem+1), MAXGLEMP);
                    if (wynik > najWynik) {
                        najWynik = wynik;
                    }
                }
                coordinates = SAVECOR;
            }
            return najWynik;
        }

        return Math.hypot(Snakes.get(0).snakeHead.getX() - coordinates.getX(), Snakes.get(0).snakeHead.getY() - coordinates.getY());
    }
}
