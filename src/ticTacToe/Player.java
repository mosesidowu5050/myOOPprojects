package ticTacToe;

public class Player {
    private String name;
    private Marker marker;

    public Player(String name, Marker marker) {
        this.name = name;
        this.marker = marker;
    }

    public String getName() {
        return name;
    }

    public Marker getMarker() {
        return marker;
    }

    public boolean makeMove(Board board, String row, String column) {
        return board.placeMark(row, column, marker);
    }
}
