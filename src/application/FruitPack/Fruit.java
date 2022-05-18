package application.FruitPack;

import application.FrogPack.Frog;
import application.SnakePack.Snake;
import application.WallPack.Wall;
import javafx.scene.image.Image;

import java.util.List;
import java.awt.*;

public class Fruit implements Fruit_Interface {
    private static final String[] FOODS_IMAGE = new String[]{"/img/ic_orange.png", "/img/ic_apple.png", "/img/ic_cherry.png",
            "/img/ic_berry.png", "/img/ic_coconut_.png", "/img/ic_peach.png", "/img/ic_watermelon.png", "/img/ic_orange.png",
            "/img/ic_pomegranate.png"};
    public Image fruitImage;
    public Point Coordinates = new Point(0, 0);

    public Fruit(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS) {
        Generate(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS);
    }

    public Fruit(Fruit fruit) {
        this.Coordinates=(Point) fruit.Coordinates.clone();
        this.fruitImage=fruit.fruitImage;
    }
    @Override
    public void Generate(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS) {
        Point pom = new Point();
        start:
        while (true) {
            pom.x = (int) (Math.random() * ROWS);
            pom.y = (int) (Math.random() * COLUMNS);

            for (int i = 0; i < Snakes.size(); i++) {
                for (int a = 0; a < Snakes.get(i).snakeBody.size(); a++) {
                    if (Snakes.get(i).snakeBody.get(a).getX() == pom.x && Snakes.get(i).snakeBody.get(a).getY() == pom.y) {
                        continue start;
                    }
                }
            }
            for (int i = 0; i < Foods.size(); i++) {
                if (Foods.get(i).Coordinates.getX() == pom.x && Foods.get(i).Coordinates.getY() == pom.y) {
                    continue start;
                }
            }
            for (int i = 0; i < Walls.size(); i++) {
                for (int a = 0; a < Walls.get(i).segments.size(); a++) {
                    if (Walls.get(i).segments.get(a).getX() == pom.x && Walls.get(i).segments.get(a).getY() == pom.y) {
                        continue start;
                    }
                }

            }
            for (Frog frog : Frogs) {
                if ((frog.coordinates.getX() == pom.x && frog.coordinates.getY() == pom.y)) {
                    continue start;
                }
            }
            Coordinates.x= pom.x;
            Coordinates.y= pom.y;
            fruitImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }
}