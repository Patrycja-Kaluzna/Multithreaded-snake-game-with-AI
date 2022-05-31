package application.WallPack;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

/**
 * Klasa przeszkody w postaci sciany. Sciana moze byc
 * punktowa lub podluzna pozioma lub pionowa.
 * Jest losowo generowane na poczatku gry.
 */
public class Wall implements Wall_Interface {

    /**
     * Sciezka do pliku z grafika sciany
     */
    public static final String Wall_Image = "/img/wall.png";
    /**
     * Lista punktow, z ktorych sklada sie sciana
     */
    public List<Point> segments = new ArrayList<>();

    /**
     * Generuje sciane podanego typu o podanych punktach poczatkowym i koncowym.
     *
     * @param TypeOfWall Typ sciany (0 - pozioma, 1 - pionowa)
     * @param Start Poczatkowy punkt sciany
     * @param End Koncowy punkt sciany
     */
    public Wall(int TypeOfWall, Point Start, Point End) {
        GenerateWall(TypeOfWall, Start , End);
    }

    /**
     * Generuje sciane podanego typu o podanych punktach poczatkowym i koncowym.
     *
     * @param TypeOfWall Typ sciany (0 - pozioma, 1 - pionowa)
     * @param Start Poczatkowy punkt sciany
     * @param End Koncowy punkt sciany
     */
    public void GenerateWall(int TypeOfWall, Point Start, Point End) {
        Point Builder = Start;

        if (TypeOfWall == 0 && Start.getX() <= End.getX() && Start.getY() == End.getY()) {
            segments.clear();
            while (Builder.x != End.x) {
                segments.add(new Point(Builder));
                Builder.x += 1;
            }
            segments.add(new Point(Builder));
        }
        if (TypeOfWall == 1 && Start.getY() <= End.getY() && Start.getX() == End.getX()) {
            segments.clear();
            while (Builder.y != End.y) {
                segments.add(new Point((int)(Builder.getX()), (int)(Builder.getY())));
                Builder.y += 1;
            }
            segments.add(new Point(Builder));
        }

    }

    /**
     * Generuje punktowa sciane.
     *
     * @param point Punkt, w ktorym zostanie wygenerowana sciana.
     */
    public void GenerateWall(Point point) {
        segments.clear();
        segments.add(point);
    }

}
