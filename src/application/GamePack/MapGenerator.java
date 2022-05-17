package application.GamePack;

import application.WallPack.Wall;

import java.awt.*;
import java.util.List;

public class MapGenerator {

    static void Generate(List<Wall> Walls, int number_of_walls, int map_size) {
        Walls.clear();
        int StartX = 2;
        int StartY = 2;
        boolean FLAG;
        while (number_of_walls != 0) {
            int TypeOfWall = (int) Math.round(Math.random() * 1);
            FLAG = false;
            while (FLAG == false) {
                StartX = (int) Math.round(Math.random() * (map_size - 1));
                StartY = (int) Math.round(Math.random() * (map_size - 1));
                if ((StartX != 0 && StartY != 0) && (StartX != 1 && StartY != 0) && (StartX != 0 && StartY != 1) && (StartX != 1 && StartY != 1) && (StartX != map_size - 1 && StartY != map_size - 1) && (StartX != map_size - 2 && StartY != map_size - 1) && (StartX != map_size - 1 && StartY != map_size - 2) && (StartX != map_size - 2 && StartY != map_size - 2)) {
                    FLAG = true;
                }
            }
            if (TypeOfWall == 0) {
                FLAG = false;
                int EndX = 2;
                int EndY=StartY;
                while (FLAG == false) {
                    EndX = (int) Math.round(Math.random() * (map_size - 1));
                    if(EndX>=StartX){
                        FLAG=true;
                    }
                }
                Walls.add(new Wall(TypeOfWall,new Point(StartX,StartY),new Point(EndX,EndY)));
                Walls.add(new Wall(TypeOfWall,new Point(map_size-1-EndX,map_size-1-EndY),new Point(map_size-1-StartX,map_size-1-StartY)));
                number_of_walls=number_of_walls-1;
            }

            if (TypeOfWall == 1) {
                FLAG = false;
                int EndX = StartX;
                int EndY=2;
                while (FLAG == false) {
                    EndY = (int) Math.round(Math.random() * (map_size - 1));
                    if(EndY>=StartY){
                        FLAG=true;
                    }
                }
                Walls.add(new Wall(TypeOfWall,new Point(StartX,StartY),new Point(EndX,EndY)));
                Walls.add(new Wall(TypeOfWall,new Point(map_size-1-EndX,map_size-1-EndY),new Point(map_size-1-StartX,map_size-1-StartY)));
                number_of_walls=number_of_walls-1;
            }

//            if (TypeOfWall == 2) {
//                FLAG = false;
//                int EndX = 2;
//                int EndY=2;
//                while (FLAG == false) {
//                    EndX = (int) Math.round(Math.random() * (map_size - 1));
//                    if(EndX>=StartX){
//                        FLAG=true;
//                    }
//                }
//                Walls.add(new Wall(TypeOfWall,new Point(StartX,StartY),new Point(EndX,StartY+(EndY-EndX))));
//                //Walls.add(new Wall(TypeOfWall,new Point(map_size-1-EndX,map_size-1-EndY),new Point(map_size-1-StartX,map_size-1-StartY)));
//                Walls.add(new Wall(TypeOfWall,new Point(map_size-1-EndX,map_size-1-StartY+(EndY-EndX)),new Point(map_size-1-StartX,map_size-1-StartY)));
//                System.out.println(map_size-1-StartX);
//                System.out.println(map_size-1-StartY);
//                System.out.println(map_size-1-EndX);
//                System.out.println(map_size-1-EndY);
//                number_of_walls=number_of_walls-1;
//            }

        }




    }
}
