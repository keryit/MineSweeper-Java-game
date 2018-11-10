package sweeper;

import java.util.ArrayList;

public class Ranges {

    private static Coordinates size;
    private static ArrayList<Coordinates> allCoords;

    public static void setSize(Coordinates _size){
        size = _size;
        allCoords = new ArrayList<Coordinates>();
        for (int y = 0; y < size.y; y++){
            for (int x =0; x < size.x; x++){
                allCoords.add(new Coordinates(x, y));
            }
        }

    }

    public static Coordinates getSize() {
        return size;
    }

    public static ArrayList<Coordinates> getAllCoords(){
        return allCoords;
    }

    static boolean inRange(Coordinates coord){
        return coord.x >= 0 && coord.x < size.x &&
                coord.y >= 0 && coord.y < size.y;
    }
}
