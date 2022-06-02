package application.FrogPack;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import application.SnakePack.Snake;
import application.FruitPack.Fruit;
import application.WallPack.Wall;
import java.util.List;
import java.awt.*;

/**
 * Interfejs zaby, ktora jest zbierana przez graczy -
 * weze. Zaba jest ruchomum obiektem do zebrania.
 */
public interface Frog_Interface {

    /**
     * Kopiuje wspolrzedne polozenia i aktualny kierunek ruchu podanej zaby.
     *
     * @param frog Zaba, ktorej wlasnosci zostana skopiowane
     */
    void FrogClone(Frog frog);
    /**
     * Generuje zabe biorac pod uwage polozenia wezy,
     * owocow, scian i innych zab na planszy.
     *
     * @param Snakes Lista wezy na planszy
     * @param Foods Lista owocow na planszy
     * @param Walls Lista scian na planszy
     * @param Frogs Lista zab ma plamszy
     * @param ROWS Liczba wierszy planszy
     * @param COLUMNS Liczba kolumn planszy
     */
    void GenerateFrog(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS);
    /**
     * Wykonuje ruch w zadanym kierunku. Jesli
     * zadany kierunek ruchu nie jest zadnym
     * ze zdefiniowanych w klasie, to zaba
     * nie rusza sie.
     *
     * @param chosenDirection Kierunek ruchu
     */
    void MoveFrog(int chosenDirection);
    /**
     * Wykonuje ruch w prawo.
     */
    void moveRight();
    /**
     * Wykonuje ruch w lewo.
     */
    void moveLeft();
    /**
     * Wykonuje ruch w gore.
     */
    void moveUp();
    /**
     * Wykonuje ruch w dol.
     */
    void moveDown();
    /**
     * Wykonuje ruch korzystajac z algorytmu
     * sztucznej inteligencji MinMax.
     *
     * @param Snakes Lista wezy na planszy
     * @param OccupiedFields Lista zajetych pol planszy
     * @param ROWS Liczba wierszy planszy
     * @param COLUMNS Liczba kolumn planszy
     * @param barrier Bariera synchronizaujaca watki
     */
    void FrogBestMove(List<Snake> Snakes, List<Point> OccupiedFields, int ROWS, int COLUMNS, CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException;
    /**
     * Implementacja algorytmu sztucznej
     * inteligencji sterujacego zaba.
     *
     * @param SaveFrog Zaba
     * @param OccupiedFields Lista zajetych pol planszy
     * @param ROWS Liczba wieszy planszy
     * @param COLUMNS Liczba kolumn planszy
     * @param glem Aktualna glebokosc rekurencji
     * @param MAXGLEMP Maksymalna glebokosc rekurencji
     */
    double FrogAI(Frog SaveFrog, List<Point> OccupiedFields, int ROWS, int COLUMNS, int glem, int MAXGLEMP);

}
