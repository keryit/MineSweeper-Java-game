package sweeper;

class Bomb {

    private Matrix bombMatrix;
    private int totalBomb;

    Bomb(int totalBomb) {
        this.totalBomb = totalBomb;
        fixBombCount();
    }

    void start() {
        bombMatrix = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBomb; i++) {
            placeBomb();
        }
    }

    Box get(Coordinates coord) {
        return bombMatrix.getMatrixArr(coord);
    }

    private void fixBombCount(){
        int maxBomb = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBomb > maxBomb){
            totalBomb = maxBomb;
        }
    }

    private void placeBomb() {
        while (true) {
            Coordinates coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMatrix.getMatrixArr(coord)) {
                continue;
            }
            bombMatrix.setMatrixArr(coord, Box.BOMB);
            incNumberAroundBomb(coord);
            break;
        }
    }

    private void incNumberAroundBomb(Coordinates coord) {
        for (Coordinates around : Ranges.getCoordAround(coord)) {
            if (Box.BOMB != bombMatrix.getMatrixArr(around)) {
                bombMatrix.setMatrixArr(around, bombMatrix.getMatrixArr(around).getNextNumberBox());
            }
        }
    }
}
