package application;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.List;

public class Frog {
    public int Direction = 2;
    public Image frogImage = new Image("/img/frog.png");
    public Point coordinates= new Point(0,0);

    Frog(List<Snake> Snakes, List<Food> Foods, List<Wall> Walls, List<Frog> Frogs,int ROWS,int  COLUMNS) {
    GenerateFrog(Snakes , Foods, Walls ,Frogs,ROWS,COLUMNS);
    }

    public void GenerateFrog(List<Snake> Snakes, List<Food> Foods, List<Wall> Walls, List<Frog> Frogs,int ROWS,int  COLUMNS) {
        start:
        while (true) {
            coordinates.x = (int) (Math.random() * ROWS);
            coordinates.y = (int) (Math.random() * COLUMNS);

            for (int i=0; i<Snakes.size();i++){
                for (int a=0; a<Snakes.get(i).snakeBody.size();a++) {
                    if ((Snakes.get(i).snakeBody.get(a).getX() == coordinates.x && Snakes.get(i).snakeBody.get(a).getY() == coordinates.y )) {
                        continue start;
                    }
                }
            }
            for (int i=0; i<Foods.size();i++){
                if ((Foods.get(i).coordinates.getX() == coordinates.x && Foods.get(i).coordinates.getY() == coordinates.y )) {
                    continue start;
                }
            }
            for (int i=0; i<Walls.size();i++){
                for (int a=0; a<Walls.get(i).segments.size();a++) {
                    if ((Walls.get(i).segments.get(a).getX() == coordinates.x && Walls.get(i).segments.get(a).getY() == coordinates.y )) {
                        continue start;
                    }
                }
            }
            for (int i=0; i<Foods.size();i++){
                if ((Foods.get(i).coordinates.getX() == coordinates.x && Foods.get(i).coordinates.getY() == coordinates.y )) {
                    continue start;
                }
            }
            for (int i=0; i<Frogs.size();i++){
                if ((Frogs.get(i).coordinates.getX() == coordinates.x && Frogs.get(i).coordinates.getY() == coordinates.y )) {
                    continue start;
                }
            }

            break;
        }
    }

}
