package application.GamePack;

import javafx.scene.canvas.GraphicsContext;
import application.SnakePack.Snake;
import application.FrogPack.Frog;
import java.io.IOException;
import javafx.stage.Stage;

/**
 * Interfejs gry, ktory obsluguje rysowanie planszy i
 * jej elementow - tla, scian, owocow, wezy, zaby i
 * punktow. Ponadto obsluguje start i koniec gry
 * oraz podliczanie punktow.
 */
public interface Game_Interface {

    /**
     * Rozpoczyna gre o podanym rozmiarze planszy.
     * Inicjalizuje scene, generauje mape, a w
     * tym sciany, generuje owoce, weze i zabe
     * oraz obsluguje sterowanie wezem przy
     * pomocy klawiatury.
     *
     * @param primaryStage  Scena
     * @param size Rozmiar planszy
     */
    void Game_Start(Stage primaryStage, double size);
    /**
     * Rysuje tlo planszy.
     *
     * @param gc Kontekst graficzny
     */
    void drawBackground(GraphicsContext gc);
    /**
     * Rysuje owoce na planszy.
     *
     * @param gc Kontekst graficzny
     */
    void drawFood(GraphicsContext gc);
    /**
     * Rysuje sciany na planszy.
     *
     * @param gc Kontekst graficzny
     */
    void drawWall(GraphicsContext gc);
    /**
     * Rysuje zabe na planszy.
     *
     * @param gc Kontekst graficzny
     * @param frog Rysowana zaba
     */
    static void drawFrog(GraphicsContext gc, Frog frog) {}
    /**
     * Rysuje weza na planszy.
     *
     * @param gc Kontekst graficzny
     * @param snake Rysowany waz
     */
    static void drawSnake(GraphicsContext gc, Snake snake) {}
    /**
     * Wypisuje aktualna liczbe punktow na planszy.
     */
    void drawScore();
    /**
     * Rysuje koniec gry.
     */
    void drawGameOver() throws InterruptedException, IOException;
    /**
     * Dodaje wszystkie zajete pola planszy do listy.
     */
    void SumOfOTakenPoints();
    /**
     * Dodaje wszystkie zajete pola planszy do listy
     * z wyjatkiem pola zajetego przez podana zabe.
     *
     * @param WithOutThis Zaba, ktorej zajmowane pole jest wykluczone
     */
    void SumOfOTakenPoints(Frog WithOutThis);

}
