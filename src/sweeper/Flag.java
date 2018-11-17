package sweeper;

class Flag {

    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coordinates coord) {
        return flagMap.getMatrixArr(coord);
    }

    void setOpenedToBox(Coordinates coord) {
        flagMap.setMatrixArr(coord, Box.OPENED);
        countOfClosedBoxes--;
    }

    void setFlagedToBox(Coordinates coord) {
        flagMap.setMatrixArr(coord, Box.FLAGED);
    }

    void toggleFlagedToBox(Coordinates coord) {
        switch (flagMap.getMatrixArr(coord)) {
            case FLAGED:
                setClosedToBox(coord);
                break;
            case CLOSED:
                setFlagedToBox(coord);
                break;
        }
    }

    void setClosedToBox(Coordinates coord) {
        flagMap.setMatrixArr(coord, Box.CLOSED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coordinates coord) {
        flagMap.setMatrixArr(coord, Box.BOMBED);
    }

    void setOpenedToClosedBombBox(Coordinates coord) {
        if (flagMap.getMatrixArr(coord) == Box.CLOSED) {
            flagMap.setMatrixArr(coord, Box.OPENED);
        }
    }

    void setNoBombToFlagedSafeBox(Coordinates coord) {
        if (flagMap.getMatrixArr(coord) == Box.FLAGED) {
            flagMap.setMatrixArr(coord, Box.NOBOMB);
        }
    }

    int getCountOfFlagedBoxesAround(Coordinates coord) {
        int count = 0;
        for (Coordinates around : Ranges.getCoordAround(coord)) {
            if (flagMap.getMatrixArr(around) == Box.FLAGED) {
                count++;
            }
        }
        return count;
    }
}
