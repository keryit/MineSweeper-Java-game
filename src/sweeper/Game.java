package sweeper;

public class Game {

    Matrix bombMatrix;

    public Game (int cols, int rows){
        Ranges.setSize(new Coordinates(cols, rows));
    }

    public void start(){
        bombMatrix = new Matrix(Box.ZERO);
    }

    public Box getBox(Coordinates coord){
        return bombMatrix.getMatrixArr(coord);
    }
}
