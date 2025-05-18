package ticTacToe;

public class Board {

    private Marker[][] boardGrid;

    public Board() {
        boardGrid = new Marker[3][3];
        initializeBoardToEmpty(boardGrid);
    }

    public void initializeBoardToEmpty(Marker[][] boardGrid) {
        for (int row = 0; row < boardGrid.length; row++) {
            for (int column = 0; column < boardGrid[row].length; column++) {
                boardGrid[row][column] = Marker.EMPTY;
            }
        }
    }

    public Marker[][] getBoardGrid() {
        return boardGrid;
    }

    public boolean placeMark(String gridRow, String gridColumn, Marker marker) {
        int row = parseRowAndColumn(gridRow);
        int column = parseRowAndColumn(gridColumn);
        if (boardGrid[row][column] == Marker.EMPTY) {
            boardGrid[row][column] = marker;
            return true;
        }
        return false;
    }

    public boolean isMarkEmpty(int row, int column) {
        return boardGrid[row][column] == Marker.EMPTY;
    }


    public boolean checkWinner() {
        for (Marker[] markers : boardGrid) {
            if (markers[0] != Marker.EMPTY &&
                    markers[0] == markers[1] &&
                    markers[1] == markers[2]) {
                return true;
            }
        }

        for (int column = 0; column < boardGrid[0].length; column++) {
            if (boardGrid[0][column] != Marker.EMPTY &&
                    boardGrid[0][column] == boardGrid[1][column] &&
                    boardGrid[1][column] == boardGrid[2][column]) {
                return true;
            }
        }

        if (boardGrid[0][0] != Marker.EMPTY &&
                boardGrid[0][0] == boardGrid[1][1] &&
                boardGrid[1][1] == boardGrid[2][2]) {
            return true;
        }

        return boardGrid[0][2] != Marker.EMPTY &&
                boardGrid[0][2] == boardGrid[1][1] &&
                boardGrid[1][1] == boardGrid[2][0];
    }


    private static int parseRowAndColumn(String value) {
        try {
            int parsedValue = Integer.parseInt(value);
            boolean isNotValidValue = parsedValue < 0 || parsedValue > 2;
            if (isNotValidValue) throw new IllegalArgumentException("Invalid");
            return parsedValue;

        } catch (IllegalArgumentException message) {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}
