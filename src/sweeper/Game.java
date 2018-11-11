package sweeper;

public class Game {

    private Bomb bomb;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coordinates(cols, rows));
        bomb = new Bomb(bombs);
    }

    public void start() {
        bomb.start();
    }

    public Box getBox(Coordinates coord) {
        return bomb.get(coord);
    }
}
