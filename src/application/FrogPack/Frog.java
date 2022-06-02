package application.FrogPack;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import application.SnakePack.Snake;
import application.FruitPack.Fruit;
import application.WallPack.Wall;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

/**
 * Klasa zaby, ktora jest zbierana przez graczy -
 * weze. Zaba jest ruchomym obiektem do zebrania.
 */
public class Frog implements Frog_Interface {

    /**
     * Ruch w prawo
     */
    private static final int RIGHT = 0;
    /**
     * Ruch w lewo
     */
    private static final int LEFT = 1;
    /**
     * Ruch w gore
     */
    private static final int UP = 2;
    /**
     * Ruch w dol
     */
    private static final int DOWN = 3;
    /**
     * Brak ruchu
     */
    private static final int STOP = 4;
    /**
     * Aktualny kierunek ruchu zaby
     */
    public int Direction = 2;
    /**
     * Grafika zaby
     */
    public Image frogImage = new Image("/img/frog.png");
    /**
     * Wspolrzedne polozenia zaby na planszy
     */
    public Point coordinates = new Point(0, 0);
    /**
     * Tymczasowa lista wezy
     */
    private List<Snake> SnakesTEMP = new ArrayList<>();

    /**
     * Generuje zabe biorac pod uwage polozenia
     * wezy, owocow, scian i innych zab ma planszy.
     *
     * @param Snakes Lista wezy na planszy
     * @param Foods Lista owocow na planszy
     * @param Walls Lista scian na planszy
     * @param Frogs Lista zab ma plamszy
     * @param ROWS Liczba wierszy planszy
     * @param COLUMNS Liczba kolumn planszy
     */
    public Frog(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS) {
        GenerateFrog(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS);
    }

    /**
     * Kopiuje wspolrzedne polozenia i aktualny kierunek ruchu podanej zaby.
     *
     * @param frog Zaba, ktorej wlasnosci zostana skopiowane
     */
    public void FrogClone(Frog frog) {
        this.coordinates = (Point) frog.coordinates.clone();
        this.Direction = frog.Direction;
    }

    /**
     * Kopiuje wspolrzedne polozenia i aktualny kierunek ruchu podanej zaby.
     *
     * @param frog Zaba, ktorej wlasnosci zostana skopiowane
     */
    public Frog(Frog frog) {
        this.coordinates = (Point) frog.coordinates.clone();
        this.Direction = frog.Direction;
    }

    /**
     * Inicjalizuje wspolrzedne polozenia i aktualny kierunek
     * ruchu zadanymi wartosciami przy tworzeniu zaby.
     *
     * @param Coordinates Zadane wspolrzedne polozenia
     * @param direction Zadany kierunek ruchu
     */
    public Frog(Point Coordinates, int direction) {
        this.coordinates = (Point) Coordinates.clone();
        this.Direction = direction;
    }

    /**
     * Generuje zabe biorac pod uwage polozenia wezy,
     * owocow, scian i innych zab na planszy.
     *
     * @param Snakes Lista wezy na planszy
     * @param Foods Lista owocow na planszy
     * @param Walls Lista scian na planszy
     * @param Frogs Lista zab ma plamszy
     * @param ROWS Liczba wierszy planszy
     * @param COLUMNS Liczba kolumn planszy
     */
    public void GenerateFrog(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS) {
        start:
        while (true) {
            coordinates.x = (int) (Math.random() * ROWS);
            coordinates.y = (int) (Math.random() * COLUMNS);

            for (Snake snake : Snakes) {
                for (int a = 0; a < snake.snakeBody.size(); a++) {
                    if (snake.snakeBody.get(a).getX() == coordinates.x && snake.snakeBody.get(a).getY() == coordinates.y) {
                        continue start;
                    }
                }
            }
            for (Fruit food : Foods) {
                if (food.Coordinates.getX() == coordinates.x && food.Coordinates.getY() == coordinates.y) {
                    continue start;
                }
            }
            for (Wall wall : Walls) {
                for (int a = 0; a < wall.segments.size(); a++) {
                    if (wall.segments.get(a).getX() == coordinates.x && wall.segments.get(a).getY() == coordinates.y) {
                        continue start;
                    }
                }
            }
            for (Frog frog : Frogs) {
                if (frog.coordinates.getX() == coordinates.x && frog.coordinates.getY() == coordinates.y) {
                    continue start;
                }
            }
            break;
        }
    }

    /**
     * Wykonuje ruch w zadanym kierunku. Jesli
     * zadany kierunek ruchu nie jest zadnym
     * ze zdefiniowanych w klasie, to zaba
     * nie rusza sie.
     *
     * @param chosenDirection Kierunek ruchu
     */
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

    /**
     * Wykonuje ruch w prawo.
     */
    public void moveRight() {
        this.coordinates.x++;
    }

    /**
     * Wykonuje ruch w lewo.
     */
    public void moveLeft() {
        this.coordinates.x--;
    }

    /**
     * Wykonuje ruch w gore.
     */
    public void moveUp() {
        this.coordinates.y--;
    }

    /**
     * Wykonuje ruch w dol.
     */
    public void moveDown() {
        this.coordinates.y++;
    }

    /**
     * Wykonuje ruch korzystajac z algorytmu
     * sztucznej inteligencji MinMax.
     *
     * @param Snakes Lista wezy na planszy
     * @param OccupiedFields Lista zajetych pol planszy
     * @param ROWS Liczba wierszy planszy
     * @param COLUMNS Liczba kolumn planszy
     * @param barrier Bariera synchronizaujaca watki
     */
    public void FrogBestMove(List<Snake> Snakes, List<Point> OccupiedFields, int ROWS, int COLUMNS, CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException {
        double wynik = 0, najWynik = -10000;
        boolean FREE;
        int BestMove = 4;
        Point SAVECOR;
        int SAVEDIR = Direction;

        SnakesTEMP.clear();
        for (int i = 0; i < Snakes.size(); i++) {
            SnakesTEMP.add(new Snake(Snakes.get(i)));
        }
        Frog SaveFrog = new Frog(this.coordinates,this.Direction);
        barrier.await();
        for (int a = 4; a >=0; a--) {
            FREE = true;
            SAVECOR = (Point) SaveFrog.coordinates.clone();
            SaveFrog.MoveFrog(a);
            for (Point occupiedField : OccupiedFields) {
                if (occupiedField.getX() == SaveFrog.coordinates.getX() && occupiedField.getY() == SaveFrog.coordinates.getY()) {
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
        if (BestMove == 4) {
            Direction = SAVEDIR;
        }
    }

    /**
     * Implementacja algorytmu sztucznej
     * inteligencji MinMax sterujacego zaba.
     *
     * @param SaveFrog Zaba
     * @param OccupiedFields Lista zajetych pol planszy
     * @param ROWS Liczba wieszy planszy
     * @param COLUMNS Liczba kolumn planszy
     * @param glem Aktualna glebokosc rekurencji
     * @param MAXGLEMP Maksymalna glebokosc rekurencji
     */
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
        int TotalDistance = 0;
        int minDistance = 1000000000;
        int pom;
        for (int i = 0; i < SnakesTEMP.size(); i++) {
            pom = (int) Math.round(Math.hypot(SnakesTEMP.get(i).snakeHead.getX() - SaveFrog.coordinates.getX(), SnakesTEMP.get(i).snakeHead.getY() - SaveFrog.coordinates.getY()));
            if (minDistance > pom) {
                minDistance = pom;
            }
            TotalDistance += pom;
        }
        TotalDistance = TotalDistance * (minDistance + 1);
        return TotalDistance;
    }
}
