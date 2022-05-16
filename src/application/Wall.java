package application;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wall {
    public static final String Wall_Image = "/img/wall.png";
    List<Point> segments = new ArrayList<>();

    Wall(int TypeOfWall, Point Start, Point End) {

        GenerateWall(TypeOfWall, Start , End);
    }
    Wall(Point point){
        GenerateWall(point);
    }
    public void GenerateWall(int TypeOfWall, Point Start, Point End) {

        //Horizontal
        Point Builder = Start;

        if (TypeOfWall == 0 &&Start.getX()<=End.getX()&&Start.getY()==End.getY()) {
            segments.clear();
            while ( Builder.x != End.x) {
                segments.add(new Point(Builder));
                Builder.x+=1;
            }
            segments.add(new Point(Builder));
        }

        //Vertical
        if (TypeOfWall == 1&&Start.getY()<=End.getY()&&Start.getX()==End.getX()) {
            segments.clear();
            while (Builder.y != End.y) {
                segments.add(new Point((int)(Builder.getX()), (int)(Builder.getY())));

                Builder.y += 1;

            }
            segments.add(new Point(Builder));
        }

        //Rising slope
        if (TypeOfWall == 2&&Start.getX()<=End.getX()&&Start.getY()>=End.getY()) {
            segments.clear();

            while (Builder.x != End.x && Builder.y != End.y) {
                segments.add(new Point(Builder));
                Builder.x += 1;
                Builder.y -= 1;
            }
            segments.add(new Point(Builder));
        }
        //Decrasing slope
        if (TypeOfWall == 3&&Start.getX()<=End.getX()&&Start.getY()<=End.getY()) {
            segments.clear();
            while (Builder.getY() != End.getY() && Builder.getX() != End.getX()) {
                segments.add(new Point(Builder));
                Builder.x += 1;
                Builder.y += 1;
            }
            segments.add(new Point(Builder));
        }

    }
    public void GenerateWall(Point point){
        segments.clear();
        segments.add(point);
    }

}
