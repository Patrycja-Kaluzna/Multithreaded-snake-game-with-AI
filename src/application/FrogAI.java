package application;

import java.awt.*;
import java.util.List;

public class FrogAI {
    public static void FrogBestMove(Frog frog,List<Snake> Snakes, List<Point> OccupiedFields, int ROWS, int COLUMNS) {
        double wynik = 0, najWynik = -10000;
        boolean FREE;
        int BestMove = 4;
        Point SAVECOR;
        int SAVEDIR = frog.Direction;


        for (int a = 4; a >=0; a--) {
            FREE = true;
            SAVECOR = (Point) frog.coordinates.clone();
            frog.MoveFrog(a);
            for (Point occupiedField : OccupiedFields) {
                if (occupiedField.getX() == frog.coordinates.getX() && occupiedField.getY() == frog.coordinates.getY()) {
                    FREE = false;
                }
            }

            if (FREE && frog.coordinates.getX() >= 0 && frog.coordinates.getX() < COLUMNS && frog.coordinates.getY() >= 0 && frog.coordinates.getY() < ROWS) {
                wynik = FrogAI(frog, Snakes, OccupiedFields, ROWS, COLUMNS, 0, 4);
                if (wynik > najWynik) {
                    najWynik = wynik;
                    BestMove = a;
                }
            }
            frog.coordinates = SAVECOR;
        }
        frog.MoveFrog(BestMove);
        if (BestMove == 4) {
            frog.Direction = SAVEDIR;
        }
    }

    private static double FrogAI(Frog frog, List<Snake> Snakes, List<Point> OccupiedFields, int ROWS, int COLUMNS, int glem, int MAXGLEMP) {
        double wynik = 0;
        double najWynik = -10000;
        boolean FREE;
        Point SAVECOR;
        int SAVEDIR = frog.Direction;
        if (glem < MAXGLEMP) {
            najWynik = -10000;
            for (int a = 4; a >= 0; a--) {
                FREE = true;
                SAVECOR = (Point) frog.coordinates.clone();
                frog.MoveFrog(a);
                for (Point occupiedField : OccupiedFields) {
                    if (occupiedField.getX() == frog.coordinates.getX() && occupiedField.getY() == frog.coordinates.getY()) {
                        FREE = false;
                    }
                }

                if (FREE && frog.coordinates.getX() >= 0 && frog.coordinates.getX() < COLUMNS && frog.coordinates.getY() >= 0 && frog.coordinates.getY() < ROWS) {
                    wynik = FrogAI(frog, Snakes, OccupiedFields, ROWS, COLUMNS, (glem+1), MAXGLEMP);
                    if (wynik > najWynik) {
                        najWynik = wynik;
                    }
                }
                frog.coordinates = SAVECOR;
            }
            return najWynik;
        }
        int TotalDistance=0;
        for(int i=0; i<Snakes.size();i++){
            TotalDistance+=  Math.hypot(Snakes.get(i).snakeHead.getX() - frog.coordinates.getX(), Snakes.get(i).snakeHead.getY() - frog.coordinates.getY());
        }

        //return Math.hypot(Snakes.get(0).snakeHead.getX() - coordinates.getX(), Snakes.get(0).snakeHead.getY() - coordinates.getY());
        return  TotalDistance;
    }
}
