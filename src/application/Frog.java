package application;

import javafx.scene.image.Image;
import java.util.List;
import java.awt.*;

public class Frog implements Food, Player {
    public int Direction = 2;
    public Image frogImage = new Image("/img/frog.png");
    public Point Coordinates = new Point(0,0);

    Frog(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS) {
        Generate(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS);
    }

    public void Generate(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS) {
        start:
        while (true) {
            Coordinates.x = (int) (Math.random() * ROWS);
            Coordinates.y = (int) (Math.random() * COLUMNS);

            for (int i = 0; i < Snakes.size(); i++) {
                for (int a = 0; a < Snakes.get(i).Body.size(); a++) {
                    if (Snakes.get(i).Body.get(a).getX() == Coordinates.x && Snakes.get(i).Body.get(a).getY() == Coordinates.y) {
                        continue start;
                    }
                }
            }
            for (int i = 0; i < Foods.size(); i++) {
                if (Foods.get(i).Coordinates.getX() == Coordinates.x && Foods.get(i).Coordinates.getY() == Coordinates.y) {
                    continue start;
                }
            }
            for (int i = 0; i < Walls.size(); i++) {
                for (int a = 0; a < Walls.get(i).segments.size(); a++) {
                    if (Walls.get(i).segments.get(a).getX() == Coordinates.x && Walls.get(i).segments.get(a).getY() == Coordinates.y ) {
                        continue start;
                    }
                }
            }
            for (int i = 0; i < Foods.size(); i++) {
                if (Foods.get(i).Coordinates.getX() == Coordinates.x && Foods.get(i).Coordinates.getY() == Coordinates.y) {
                    continue start;
                }
            }
            for (int i = 0; i < Frogs.size(); i++) {
                if (Frogs.get(i).Coordinates.getX() == Coordinates.x && Frogs.get(i).Coordinates.getY() == Coordinates.y) {
                    continue start;
                }
            }

            break;
        }
    }

    public void Move() {

    }

    public void Eat() {

    }
}