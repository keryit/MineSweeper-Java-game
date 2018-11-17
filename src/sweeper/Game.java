package sweeper;

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState gameState;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coordinates(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void start() {
        bomb.start();
        flag.start();
        gameState = GameState.PLAYED;
    }

    public Box getBox(Coordinates coord) {
        if (flag.get(coord) == Box.OPENED) {
            return bomb.get(coord);
        } else {
            return flag.get(coord);
        }
    }

    public void pressLeftButton(Coordinates coord) {
        if (gameOver()) {
            return;
        }
        openBox(coord);
        checkWinner();
    }

    private void checkWinner() {
        if (gameState == GameState.PLAYED) {
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) {
                gameState = GameState.WINNER;
            }
        }
    }

    private void openBox(Coordinates coord) {
        switch (flag.get(coord)) {
            case OPENED:
                setOpenedToClosedBoxesAroundNumber(coord);
                return;
            case FLAGED:
                return;
            case CLOSED:
                switch (bomb.get(coord)) {
                    case ZERO:
                        openBoxesAround(coord);
                        return;
                    case BOMB:
                        openBombs(coord);
                        return;
                    default:
                        flag.setOpenedToBox(coord);
                        return;
                }
        }
    }

    void setOpenedToClosedBoxesAroundNumber(Coordinates coord) {
        if (bomb.get(coord) != Box.BOMB) {
            if (flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord).getNumber()) {
                for (Coordinates around : Ranges.getCoordAround(coord)) {
                    if (flag.get(around) == Box.CLOSED) {
                        openBox(around);
                    }
                }
            }
        }

    }

    private void openBombs(Coordinates bombed) {
        gameState = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coordinates coord : Ranges.getAllCoords()) {
            if (bomb.get(coord) == Box.BOMB) {
                flag.setOpenedToClosedBombBox(coord);
            } else {
                flag.setNoBombToFlagedSafeBox(coord);
            }
        }
    }

    private void openBoxesAround(Coordinates coord) {
        flag.setOpenedToBox(coord);
        for (Coordinates around : Ranges.getCoordAround(coord)) {
            openBox(around);
        }
    }

    public void pressRightButton(Coordinates coord) {
        if (gameOver()) {
            return;
        }
        flag.toggleFlagedToBox(coord);
    }

    private boolean gameOver() {
        if (gameState == GameState.PLAYED) {
            return false;
        }
        start();
        return true;
    }
}
