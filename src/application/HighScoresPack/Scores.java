package application.HighScoresPack;

/**
 * Klasa wyniku zawierajaca informacje dotyczace
 * nazwy gracza i jego liczby zdobytych punktow.
 */
public class Scores implements Comparable<Scores> {

    /**
     * Nazwa gracza
     */
    public String name = "";
    /**
     * Zdobyte punkty gracza
     */
    public int score = 0;

    /**
     * Inicjalizuje obiekt podana nazwa gracza
     * i jego liczba zdobytych punktow przy
     * tworzeniu.
     *
     * @param Name Nazwa gracza
     * @param Score Zdobyte puntky gracza
     */
    public Scores(String Name, int Score) {
        name = Name;
        score = Score;
    }

    /**
     * Porownuje liczbe punktow obiektu,
     * dla ktorego wywolano te metode z
     * liczba punktow podanego obiektu.
     *
     * @param s Obiekt, ktorego liczba puntkow zostanie porownana
     */
    @Override
    public int compareTo(Scores s){
        return s.score - this.score;
    }

}
