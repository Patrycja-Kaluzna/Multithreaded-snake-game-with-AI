package application.GamePack;

import application.SnakePack.PlayerSnakeThread;
import application.SnakePack.ComSnakeThread;
import javafx.scene.canvas.GraphicsContext;
import application.Scenes.GameOverControl;
import java.util.concurrent.CyclicBarrier;
import application.FruitPack.FruitThread;
import application.FrogPack.FrogThread;
import java.util.concurrent.TimeUnit;
import application.FruitPack.Fruit;
import application.SnakePack.Snake;
import javafx.scene.input.KeyEvent;
import javafx.animation.Animation;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import application.FrogPack.Frog;
import application.WallPack.Wall;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;
import java.awt.*;

/**
 * Klasa gry, ktora obsluguje rysowanie planszy i
 * jej elementow - tla, scian, owocow, wezy, zaby
 * i punktow. Ponadto obsluguje start i koniec
 * gry oraz podliczanie punktow.
 */
public class Game implements Game_Interface {

    /**
     * Szerokosc planszy
     */
    public static double WIDTH = 800;
    /**
     * Wysokosc planszy
     */
    public static double HEIGHT = WIDTH;
    /**
     * Liczba wierszy planszy
     */
    private static final int ROWS = 21;
    /**
     * Liczba kolumn planszy
     */
    private static final int COLUMNS = ROWS;
    /**
     * Rozmiar pola planszy
     */
    private static double SQUARE_SIZE = WIDTH / ROWS;
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
     * Kontekst graficzny
     */
    private GraphicsContext gc;
    /**
     * Kierunek ruchu gracza (nie AI)
     */
    private int PlayerDirection;
    /**
     *
     */
    private boolean KeyLock = false;
    /**
     * Lista wezy na planszy
     */
    private List<Snake> Snakes = new ArrayList();
    /**
     * Lista owocow na planszy
     */
    private List<Fruit> Foods = new ArrayList<>();
    /**
     * Lista zab na planszy
     */
    private List<Frog> Frogs = new ArrayList<>();
    /**
     * Lista scian na planszy
     */
    private List<Wall> Walls = new ArrayList<>();
    /**
     * Lista zajetych pol planszy
     */
    private List<Point> AllPoints = new ArrayList<>();
    /**
     *
     */
    private Stage PrimStage;
    /**
     *
     */
    private Timeline timeline;
    /**
     * Bariera synchronizujaca watki
     */
    CyclicBarrier barrier = new CyclicBarrier(3);


    /**
     * Rozpoczyna gre o podanym rozmiarze planszy.
     * Inicjalizuje scene, generauje mape, a w
     * tym sciany, generuje owoce, weze i zabe
     * oraz obsluguje sterowanie wezem przy
     * pomocy klawiatury.
     *
     * @param primaryStage  Scena
     * @param size Rozmiar planszy
     */
    public void Game_Start(Stage primaryStage, double size) {
        // Inicjalizacja sceny
        WIDTH = (int) size;
        HEIGHT = WIDTH;
        SQUARE_SIZE = WIDTH / ROWS;
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        PrimStage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();

        // Obsluga klawiatury
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (KeyLock == false) {
                    if (code == KeyCode.RIGHT || code == KeyCode.D) {
                        if (PlayerDirection != LEFT) {
                            PlayerDirection = RIGHT;
                            KeyLock = true;
                        }
                    } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                        if (PlayerDirection != RIGHT) {
                            PlayerDirection = LEFT;
                            KeyLock = true;
                        }
                    } else if (code == KeyCode.UP || code == KeyCode.W) {
                        if (PlayerDirection != DOWN) {
                            PlayerDirection = UP;
                            KeyLock = true;
                        }
                    } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                        if (PlayerDirection != UP) {
                            PlayerDirection = DOWN;
                            KeyLock = true;
                        }
                    }
                }
            }
        });

        Snakes.add(new Snake(3, 0, 0, "/img/Snake_Head.png", "00b0ff", DOWN));
        Snakes.add(new Snake(3, ROWS - 1, ROWS - 1, "/img/Snake_Head_2.png", "ffcc00", UP));
        PlayerDirection = Snakes.get(0).Direction;
        MapGenerator.Generate(Walls, 2, ROWS);
        Foods.add(new Fruit(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS));
        Foods.add(new Fruit(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS));
        Foods.add(new Fruit(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS));
        Foods.add(new Fruit(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS));
        Frogs.add(new Frog(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS));
        timeline = new Timeline(new KeyFrame(Duration.millis(150), e -> {
            try {
                run(gc);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Uruchamia watki oraz rysuje tlo,
     * sciany, punkty, owoce, weze i
     * zabe, a takze koniec gry.
     *
     * @param gc Kontekst graficzny
     */
    private void run(GraphicsContext gc) throws InterruptedException, IOException {
        Thread FrogThread = null;
        Thread PlayerThread = null;
        Thread ComputerThread = null;
        if (Snakes.get(0).gameOver) {
            drawGameOver();
            return;
        }
        for (int i = 1; i < Snakes.size(); i++) {
            if (Snakes.get(i).gameOver == true) {
                Snakes.remove(i);
            }
        }
        drawBackground(gc);
        drawFood(gc);
        drawWall(gc);
        for (int i = 0; i < Frogs.size(); i++) {
            drawFrog(gc, Frogs.get(i));
        }
        for (int i = 1; i < Snakes.size(); i++) {
            drawSnake(gc, Snakes.get(i));
        }
        drawSnake(gc, Snakes.get(0));
        drawScore();
        for (int i = 0; i < Frogs.size(); i++) {
            SumOfOTakenPoints(Frogs.get(i));
            FrogThread objFrog = new FrogThread(Frogs.get(i), Snakes, AllPoints, ROWS, COLUMNS, barrier);
            FrogThread = new Thread(objFrog);
            FrogThread.start();
        }
        PlayerSnakeThread objPlayerSnake = new PlayerSnakeThread(Snakes.get(0), PlayerDirection, barrier);
        PlayerThread = new Thread(objPlayerSnake);
        PlayerThread.start();
        KeyLock = false;
        for (int i = 1; i < Snakes.size(); i++) {
            ComSnakeThread objComSnake = new ComSnakeThread(Snakes.get(i), Snakes, Foods, Frogs, Walls, ROWS, barrier);
            ComputerThread = new Thread(objComSnake);
            ComputerThread.start();
        }
        FrogThread.join();
        PlayerThread.join();
        ComputerThread.join();
        Scoring(Snakes, Foods, Frogs, Walls);
        gameOver(Snakes, Walls);
    }

    /**
     * Rysuje tlo planszy.
     *
     * @param gc Kontekst graficzny
     */
    public void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    /**
     * Rysuje owoce na planszy.
     *
     * @param gc Kontekst graficzny
     */
    public void drawFood(GraphicsContext gc) {
        for (int i = 0; i < Foods.size(); i++) {
            gc.drawImage(Foods.get(i).fruitImage, Foods.get(i).Coordinates.getX() * SQUARE_SIZE, Foods.get(i).Coordinates.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    /**
     * Rysuje sciany na planszy.
     *
     * @param gc Kontekst graficzny
     */
    public void drawWall(GraphicsContext gc) {
        for (int i = 0; i < Walls.size(); i++) {
            for (int a = 0; a < Walls.get(i).segments.size(); a++) {
                gc.drawImage(new Image(Walls.get(i).Wall_Image), Walls.get(i).segments.get(a).getX() * SQUARE_SIZE, Walls.get(i).segments.get(a).getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    /**
     * Rysuje zabe na planszy.
     *
     * @param gc Kontekst graficzny
     * @param frog Rysowana zaba
     */
    public static void drawFrog(GraphicsContext gc, Frog frog) {
        Image FrogImage = frog.frogImage;
        gc.save();
        gc.translate((frog.coordinates.getX() * SQUARE_SIZE) + SQUARE_SIZE / 2, (frog.coordinates.getY() * SQUARE_SIZE) + SQUARE_SIZE / 2);
        switch (frog.Direction) {
            case RIGHT:
                gc.rotate(-90);
                break;
            case LEFT:
                gc.rotate(-270);
                break;
            case UP:
                gc.rotate(-180);
                break;
        }
        gc.translate(-1 * ((frog.coordinates.getX() * SQUARE_SIZE) + SQUARE_SIZE / 2), -1 * ((frog.coordinates.getY() * SQUARE_SIZE) + SQUARE_SIZE / 2));
        gc.drawImage(FrogImage, frog.coordinates.getX() * SQUARE_SIZE, frog.coordinates.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        gc.restore();
    }

    /**
     * Rysuje weza na planszy.
     *
     * @param gc Kontekst graficzny
     * @param snake Rysowany waz
     */
    public static void drawSnake(GraphicsContext gc, Snake snake) {
        Image SnakeHeadImage = new Image(snake.Head);
        gc.save();
        gc.translate((snake.snakeHead.getX() * SQUARE_SIZE) + SQUARE_SIZE / 2, (snake.snakeHead.getY() * SQUARE_SIZE) + SQUARE_SIZE / 2);
        switch (snake.Direction) {
            case RIGHT:
                gc.rotate(-90);
                break;
            case LEFT:
                gc.rotate(-270);
                break;
            case UP:
                gc.rotate(-180);
                break;
        }
        gc.translate(-1 * ((snake.snakeHead.getX() * SQUARE_SIZE) + SQUARE_SIZE / 2), -1 * ((snake.snakeHead.getY() * SQUARE_SIZE) + SQUARE_SIZE / 2));
        gc.drawImage(SnakeHeadImage, snake.snakeHead.getX() * SQUARE_SIZE, snake.snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        gc.restore();
        gc.setFill(Color.web(snake.Color)); // 4674E9
        for (int i = 1; i < snake.snakeBody.size(); i++) {
            gc.fillRoundRect((snake.snakeBody.get(i).getX() * SQUARE_SIZE), (snake.snakeBody.get(i).getY() * SQUARE_SIZE), SQUARE_SIZE,
                    SQUARE_SIZE, 100, 100);
        }
    }

    /**
     * Wypisuje aktualna liczbe punktow na planszy.
     */
    public void drawScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + Snakes.get(0).score, 10, 35);
    }

    /**
     * Rysuje koniec gry.
     */
    public void drawGameOver() throws InterruptedException, IOException {
        TimeUnit.MILLISECONDS.sleep(500);
        timeline.stop();
        String score = Integer.toString(Snakes.get(0).score);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Scenes/GameOver.fxml"));
        Parent root = loader.load();
        GameOverControl controller = loader.getController();
        controller.setScore(score);
        Scene scene = new Scene(root);
        PrimStage.setScene(scene);
        PrimStage.show();
    }

    /**
     * Konczy gre. Sprawdza wystapienia wszystkich
     * sytuacji konczacych gre, czyli wypelzniecia
     * weza poza plansze, zjedzenia weza przez
     * siebie samego i przez innego weza, a
     * takze uderzenie weza w sciane.
     *
     * @param Snakes Lista wezy na planszy
     * @param Walls Lista scian na planszy
     */
    public static void gameOver(List<Snake> Snakes, List<Wall> Walls) {
        // Waz wypelzl poza plansze
        for (int a = 0; a < Snakes.size(); a++) {
            if (Snakes.get(a).snakeHead.x < 0 || Snakes.get(a).snakeHead.y < 0 || Snakes.get(a).snakeHead.x * SQUARE_SIZE >= WIDTH || Snakes.get(a).snakeHead.y * SQUARE_SIZE >= HEIGHT) {
                Snakes.get(a).gameOver = true;
            }

            // Waz zjadl samego siebie
            for (int i = 1; i < Snakes.get(a).snakeBody.size(); i++) {
                if (Snakes.get(a).snakeHead.x == Snakes.get(a).snakeBody.get(i).getX() && Snakes.get(a).snakeHead.getY() == Snakes.get(a).snakeBody.get(i).getY()) {
                    Snakes.get(a).gameOver = true;
                    break;
                }
            }

            // Waz zostal zjedzony przez weza
            for (int b = 0; b < Snakes.size(); b++) {
                // Ale nie przez samego siebie
                if (a != b) {
                    for (int c = 0; c < Snakes.get(b).snakeBody.size(); c++) {
                        if (Snakes.get(a).snakeHead.x == Snakes.get(b).snakeBody.get(c).getX() && Snakes.get(a).snakeHead.getY() == Snakes.get(b).snakeBody.get(c).getY()) {
                            Snakes.get(a).gameOver = true;
                            break;
                        }
                    }
                }
            }

            // Waz uderzyl w sciane
            for (int d = 0; d < Walls.size(); d++) {
                for (int e = 0; e < Walls.get(d).segments.size(); e++) {
                    if (Snakes.get(a).snakeHead.x == Walls.get(d).segments.get(e).getX() && Snakes.get(a).snakeHead.y == Walls.get(d).segments.get(e).getY()) {
                        Snakes.get(a).gameOver = true;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Dodaje punkty za zjedzenie owocu lub zaby.
     * W przypadku zjedzenia owocu uruchamia
     * watek, ktory je generuje, a w przypadku
     * zjedzenia zaby dodaje nowa zabe.
     *
     * @param Snakes Lista wezy na planszy
     * @param Foods Lista owocow na planszy
     * @param Frogs Lista zab na planszy
     * @param Walls Lista scian na planszy
     */
    public static void Scoring(List<Snake> Snakes, List<Fruit> Foods, List<Frog> Frogs, List<Wall> Walls) throws InterruptedException {
        Thread ThreadF = null;
        for (int i = 0; i < Snakes.size(); i++) {
            for (int f = 0; f < Foods.size(); f++) {
                if (Snakes.get(i).snakeHead.getX() == Foods.get(f).Coordinates.getX() && Snakes.get(i).snakeHead.getY() == Foods.get(f).Coordinates.getY()) {
                    Snakes.get(i).Eat();
                    Snakes.get(i).score += 5;
                    if (ThreadF != null) {
                        ThreadF.join();
                    }
                    FruitThread obj = new FruitThread(Foods.get(f), Snakes, Foods, Walls, Frogs, ROWS, COLUMNS);
                    ThreadF = new Thread(obj);
                    ThreadF.start();
                }
            }
            if (ThreadF != null) {
                ThreadF.join();
            }
            for (int f = 0; f < Frogs.size(); f++) {
                if (Snakes.get(i).snakeHead.getX() == Frogs.get(f).coordinates.getX() && Snakes.get(i).snakeHead.getY() == Frogs.get(f).coordinates.getY()) {
                    Snakes.get(i).Eat();
                    Snakes.get(i).Eat();
                    Frogs.remove(f);
                    Frogs.add(new Frog(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS));
                    Snakes.get(i).score += 10;
                }
            }
        }
    }

    /**
     *
     *
     * @param Snakes Lista wezy na planszy
     * @param Foods Lista owocow na planszy
     * @param Frogs Lista zab na planszy
     */
    public static void ScoringForAI(List<Snake> Snakes, List<Fruit> Foods, List<Frog> Frogs) {
        for (int i = 0; i < Snakes.size(); i++) {
            for (int f = 0; f < Foods.size(); f++) {
                if (Snakes.get(i).snakeHead.getX() == Foods.get(f).Coordinates.getX() && Snakes.get(i).snakeHead.getY() == Foods.get(f).Coordinates.getY()) {
                    Snakes.get(i).Eat();
                    Foods.remove(f);
                    Snakes.get(i).score += 5;
                }
            }
            for (int f = 0; f < Frogs.size(); f++) {
                if (Snakes.get(i).snakeHead.getX() == Frogs.get(f).coordinates.getX() && Snakes.get(i).snakeHead.getY() == Frogs.get(f).coordinates.getY()) {
                    Snakes.get(i).Eat();
                    Snakes.get(i).Eat();
                    Frogs.remove(f);
                    Snakes.get(i).score += 10;
                }
            }
        }
    }

    /**
     * Dodaje wszystkie zajete pola planszy do listy.
     */
    public void SumOfOTakenPoints() {
        AllPoints.clear();
        for (Fruit food : Foods) {
            AllPoints.add(food.Coordinates);
        }
        for (Frog frog : Frogs) {
            AllPoints.add(frog.coordinates);
        }
        for (Snake snake : Snakes) {
            AllPoints.addAll(snake.snakeBody);
        }
        for (Wall wall : Walls) {
            AllPoints.addAll(wall.segments);
        }
    }

    /**
     * Dodaje wszystkie zajete pola planszy do listy
     * z wyjatkiem pola zajetego przez podana zabe.
     *
     * @param WithOutThis Zaba, ktorej zajmowane pole jest wykluczone
     */
    public void SumOfOTakenPoints(Frog WithOutThis) {
        AllPoints.clear();
        for (Fruit food : Foods) {
            AllPoints.add(food.Coordinates);
        }
        for (Frog frog : Frogs) {
            if (frog != WithOutThis) {
                AllPoints.add(frog.coordinates);
            }
        }
        for (Snake snake : Snakes) {
            AllPoints.addAll(snake.snakeBody);
        }
        for (Wall wall : Walls) {
            AllPoints.addAll(wall.segments);
        }
    }

}
