package application.FruitPack;

import application.SnakePack.Snake;
import application.FrogPack.Frog;
import application.WallPack.Wall;
import java.util.List;

/**
 * Interfejs owocu, ktory jest zbierany przez graczy -
 * weze. Owoc jest nieruchomym obiektem do zebrania.
 */
public interface Fruit_Interface {

    /**
     * Generuje owoc biorac pod uwage polozenia
     * wezy, innych owocow, scian i zaby na planszy.
     *
     * @param Snakes Lista wezy na planszy
     * @param Foods Lista owocow na planszy
     * @param Walls Lista scian na planszy
     * @param Frogs Lista zab ma plamszy
     * @param ROWS Liczba wierszy planszy
     * @param COLUMNS Liczba kolumn planszy
     */
    void Generate(List<Snake> Snakes, List<Fruit> Foods, List<Wall> Walls, List<Frog> Frogs, int ROWS, int COLUMNS);

}
