package application.FruitPack;

import application.SnakePack.Snake;
import application.FrogPack.Frog;
import application.WallPack.Wall;
import java.util.List;

/**
 * Klasa watku generujacego owoce.
 */
public class FruitThread implements Runnable {

    /**
     * Owoc
     */
    private Fruit fruit;
    /**
     * Lista wezy na planszy
     */
    private List<Snake> Snakes;
    /**
     * Lista owocow na planszy
     */
    private List<Fruit> Foods;
    /**
     * Lista scian na planszy
     */
    private List<Wall> Walls;
    /**
     * Lista zab na planszy
     */
    private List<Frog> Frogs;
    /**
     * Liczba wierszy planszy
     */
    int ROWS;
    /**
     * Liczba kolumn planszy
     */
    int COLUMNS;

    /**
     * Inicjalizuje caly obiekt podanymi
     * wartosciami przy tworzeniu.
     *
     * @param fru Owoc
     * @param snakes Lista wezy na planszy
     * @param foods Lista owocow na planszy
     * @param walls Lista scian na planszy
     * @param frogs Lista zab na planszy
     * @param rows Liczba wieszy planszy
     * @param columns Liczba kolumn planszy
     */
    public FruitThread(Fruit fru, List <Snake> snakes, List<Fruit> foods, List<Wall> walls, List<Frog> frogs, int rows, int columns) {
       this.fruit = fru;
       this.Snakes = snakes;
       this.Foods = foods;
       this.Walls = walls;
       this.Frogs = frogs;
       this.ROWS = rows;
       this.COLUMNS = columns;
    }

    /**
     * Uruchamia watek generujacy owoce.
     */
    public void run() {
        fruit.Generate(Snakes, Foods, Walls, Frogs, ROWS, COLUMNS);
    }

}
