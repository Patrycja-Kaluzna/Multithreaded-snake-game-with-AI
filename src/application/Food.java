package application;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.List;

public class Food {

    private static final String[] FOODS_IMAGE = new String[]{"/img/ic_orange.png", "/img/ic_apple.png", "/img/ic_cherry.png",
            "/img/ic_berry.png", "/img/ic_coconut_.png", "/img/ic_peach.png", "/img/ic_watermelon.png", "/img/ic_orange.png",
            "/img/ic_pomegranate.png"};
    public Image foodImage;
    public Point coordinates= new Point(0,0);

    Food(List <Snake> Snakes ,List<Food> Foods,int ROWS,int  COLUMNS){
        generateFood( Snakes , Foods, ROWS,  COLUMNS);
    }

    public void generateFood(List <Snake> Snakes ,List<Food> Foods,int ROWS,int  COLUMNS) {
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

            foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }
}
