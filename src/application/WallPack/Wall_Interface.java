package application.WallPack;

import java.awt.*;

/**
 * Interfejs przeszkody w postaci sciany. Sciana moze byc
 * punktowa lub podluzna pozioma lub pionowa.
 * Jest losowo generowane na poczatku gry.
 */
public interface Wall_Interface {

    /**
     * Generuje sciane podanego typu o podanych punktach poczatkowym i koncowym.
     *
     * @param TypeOfWall Typ sciany (0 - pozioma, 1 - pionowa)
     * @param Start Poczatkowy punkt sciany
     * @param End Koncowy punkt sciany
     */
    void GenerateWall(int TypeOfWall, Point Start, Point End);

}
