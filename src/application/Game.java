package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
//test test test test


public class Game {
    private static int WIDTH = 800;
    private static int HEIGHT = WIDTH;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static int SQUARE_SIZE = WIDTH / ROWS;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private GraphicsContext gc;
    private int PlayerDirection;

    private List<Snake> Snakes = new ArrayList();
    private List<Food> Foods = new ArrayList<>();
    private List<Wall> Walls = new ArrayList<>();


    public void Game_Start(Stage primaryStage, double size) {
        //Inicjalizacja Sceny
        WIDTH = (int) size;
        HEIGHT = WIDTH;
        SQUARE_SIZE = WIDTH / ROWS;
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        ////////////////////////////////////////////
        // Obsluga kalwiatury
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (PlayerDirection != LEFT) {
                        PlayerDirection = RIGHT;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (PlayerDirection != RIGHT) {
                        PlayerDirection = LEFT;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (PlayerDirection != DOWN) {
                        PlayerDirection = UP;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (PlayerDirection != UP) {
                        PlayerDirection = DOWN;
                    }
                }
            }
        });
        //Dodawanie elementow do weza, wszystkie elementy ustwione sa na jednej pozycji,
        // lacznie z glowa

        Snakes.add(new Snake(3, ROWS / 2, ROWS / 2, "/img/Snake_Head.png", "00b0ff"));


        Foods.add(new Food(Snakes, Foods, ROWS, COLUMNS));
        Foods.add(new Food(Snakes, Foods, ROWS, COLUMNS));
        Point pom = new Point(2,2);
        Walls.add(new Wall(pom));


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    private void run(GraphicsContext gc) {
        if (Snakes.get(0).gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", WIDTH / 3.5, HEIGHT / 2);
            return;
        }
        drawBackground(gc);
        drawFood(gc);
        drawWall(gc);


        /// Snake number 0 is player snake
        Snakes.get(0).Direction = PlayerDirection;

        for (int i = 0; i < Snakes.size(); i++) {
            drawSnake(gc, Snakes.get(i));
            Snakes.get(i).MoveSnake();
        }
        drawScore();
        gameOver();
        Scoring();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void drawBackground(GraphicsContext gc) {
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


    private void drawFood(GraphicsContext gc) {
        for (int i = 0; i < Foods.size(); i++) {
            gc.drawImage(Foods.get(i).foodImage, Foods.get(i).coordinates.getX() * SQUARE_SIZE, Foods.get(i).coordinates.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    private void drawWall(GraphicsContext gc) {
        System.out.println(Walls.size());
        for (int i = 0; i < Walls.size(); i++) {
            for (int a = 0; a < Walls.get(i).segments.size(); a++) {
                gc.drawImage(new Image(Walls.get(i).Wall_Image), Walls.get(i).segments.get(a).getX() * SQUARE_SIZE, Walls.get(i).segments.get(a).getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void drawSnake(GraphicsContext gc, Snake snake) {
        System.out.println(snake.snakeHead.getX());
        System.out.println(snake.snakeHead.getY());
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

        gc.setFill(Color.web(snake.Color)); //4674E9
        for (int i = 1; i < snake.snakeBody.size(); i++) {
            gc.fillRoundRect((snake.snakeBody.get(i).getX() * SQUARE_SIZE), (snake.snakeBody.get(i).getY() * SQUARE_SIZE), SQUARE_SIZE,
                    SQUARE_SIZE, 100, 100);
        }
    }


    public void gameOver() {

        //Snake is out of the bound
        for (int a = 0; a < Snakes.size(); a++) {
            if (Snakes.get(a).snakeHead.x < 0 || Snakes.get(a).snakeHead.y < 0 || Snakes.get(a).snakeHead.x * SQUARE_SIZE >= WIDTH || Snakes.get(a).snakeHead.y * SQUARE_SIZE >= HEIGHT) {
                Snakes.get(a).gameOver = true;
            }

            //destroy itself o
            for (int i = 1; i < Snakes.get(a).snakeBody.size(); i++) {
                if (Snakes.get(a).snakeHead.x == Snakes.get(a).snakeBody.get(i).getX() && Snakes.get(a).snakeHead.getY() == Snakes.get(a).snakeBody.get(i).getY()) {
                    Snakes.get(a).gameOver = true;
                    break;
                }
            }
            //Kill by other snake
            for (int b = 0; b < Snakes.size(); b++) {
                //It is not the same snake
                if (a != b) {
                    for (int c = 0; c < Snakes.get(b).snakeBody.size(); c++) {
                        if (Snakes.get(a).snakeHead.x == Snakes.get(b).snakeBody.get(c).getX() && Snakes.get(a).snakeHead.getY() == Snakes.get(b).snakeBody.get(c).getY()) {
                            Snakes.get(a).gameOver = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void Scoring() {
        for (int i = 0; i < Snakes.size(); i++) {
            for (int f = 0; f < Foods.size(); f++) {
                if (Snakes.get(i).snakeHead.getX() == Foods.get(f).coordinates.getX() && Snakes.get(i).snakeHead.getY() == Foods.get(f).coordinates.getY()) {
                    Snakes.get(i).Eat();
                    Foods.remove(f);
                    Foods.add(new Food(Snakes, Foods, ROWS, COLUMNS));
                    Snakes.get(i).score += 5;
                }
            }
        }
    }

    private void drawScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + Snakes.get(0).score, 10, 35);
    }


}
