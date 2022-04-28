package application;

import java.awt.*;
import java.util.List;

public class Wall {
    private static final String Wall_Image = "/img/wall.png";
    List<Point> segments = null;

    Wall(int TypeOfWall, Point Start, Point End) {
        GenerateWall(TypeOfWall, Start , End);
    }

    public void GenerateWall(int TypeOfWall, Point Start, Point End) {

        //Horizontal
        Point Builder = Start;
        if (TypeOfWall == 0 &&Start.getX()==End.getX()&&Start.getY()<=End.getY()) {
            while (Builder.getX() != End.getX()) {
                segments.add(Builder);
                Builder.x += 1;
            }
        }
        //Vertical
        if (TypeOfWall == 1&&Start.getY()==End.getY()&&Start.getX()<=End.getX()) {
            while (Builder.getY() != End.getY()) {
                segments.add(Builder);
                Builder.x += 1;
            }
        }

        //Rising slope
        if (TypeOfWall == 2&&Start.getX()<=End.getX()&&Start.getY()>=End.getY()) {
            while (Builder.getY() != End.getY() && Builder.getX() != End.getX()) {
                segments.add(Builder);
                Builder.x += 1;
                Builder.y += 1;
            }
        }
        //Decrasing slope
        if (TypeOfWall == 3&&Start.getX()<=End.getX()&&Start.getY()<=End.getY()) {
            while (Builder.getY() != End.getY() && Builder.getX() != End.getX()) {
                segments.add(Builder);
                Builder.x -= 1;
                Builder.y -= 1;
            }
        }

    }

}
