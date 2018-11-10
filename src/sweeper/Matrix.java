package sweeper;

class Matrix {

    private Box[][] matrixArr;

    Matrix(Box defaultBox) {
        matrixArr = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coordinates coord : Ranges.getAllCoords()) {
            matrixArr[coord.x][coord.y] = defaultBox;
        }
    }

    Box getMatrixArr(Coordinates coord) {
        if (Ranges.inRange(coord)) {
            return matrixArr[coord.x][coord.y];
        }
        return null;
    }

    void setMatrixArr(Coordinates coord, Box box) {
        if (Ranges.inRange(coord)) {
            matrixArr[coord.x][coord.y] = box;
        }
    }
}
