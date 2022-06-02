package application.SnakePack;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import application.FruitPack.Fruit;
import application.FrogPack.Frog;
import application.WallPack.Wall;
import java.util.List;

/**
 * Interfejs weza, ktory jest sterowany przez nas
 * lub przez komputer. Waz zjada owoce i zabe
 * oraz unika uderzenia w sciany, zjedzenia
 * samego siebie lub przez innego weza, a
 * takze wypelzniecia poza plansze.
 */
public interface Snake_Interface {

    /**
     * Kopiuje aktualny kierunek ruchu, liczbe
     * punktow, glowe i cialo podanego weza.
     *
     * @param snake Waz, ktorego wlasnosci zostana skopiowane
     */
    void CloneSnake(Snake snake);
    /**
     * Wykonuje ruch w zadanym kierunku.
     *
     * @param chosenDirection Kierunek ruchu
     */
    void MoveSnake(int chosenDirection);
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
     * Zjada, a wiec zwieksza dlugosc weza.
     */
    void Eat();
    /**
     * Wykonuje ruch korzystajac z algorytmu
     * sztucznej inteligencji MinMax.
     *
     * @param Snakes Lista wezy na planszy
     * @param Foods Lista owocow na planszy
     * @param Frogs Lista zab na planszy
     * @param Walls Lista scian na planszy
     * @param GameSize Rozmiar planszy
     * @param barrier Bariera synchronizujaca watki
     */
    void SnakeBestMove(List<Snake> Snakes, List<Fruit> Foods, List<Frog> Frogs, List<Wall> Walls, int GameSize, CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException;
    /**
     * Implementacja algorytmu sztucznej inteligencji
     * MinMax z alfa-beta cieciami sterujacego wezem.
     *
     * @param Snakes Lista wezy na planszy
     * @param Foods Lista owocow na planszy
     * @param Frogs Lista zab na planszy
     * @param Walls Lista scian na planszy
     * @param glem Aktualna glebokosc rekurencji
     * @param MAXGLEMP Maksymalna glebokosc rekurencji
     * @param alpha Reprezentuje minimalny wynik gracza Max
     * @param beta Reprezentuje maksymalny wynik gracza Min
     * @param toKomp Informacja czy gracz jest Min czy Max
     */
    int SnakeAI(List<Snake> Snakes, List<Fruit> Foods, List<Frog> Frogs, List<Wall> Walls, int glem, int MAXGLEMP, int alpha, int beta, boolean toKomp);

}
