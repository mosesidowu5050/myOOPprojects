package ticTacToe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void initialized_rowAndColumn_toEmpty_Test() {
        Marker[][] boardGrid = board.getBoardGrid();

        for (Marker[] markers : boardGrid) {
            for (Marker marker : markers) {
                assertEquals(Marker.EMPTY, marker);
            }
        }
    }

    @Test
    public void testPlaceMarkOnEmptyRowAndColumnWithPlayerX() {
        boolean markWithX = board.placeMark("1", "1", Marker.X);
        assertTrue(markWithX);
        assertEquals(Marker.X, board.getBoardGrid()[1][1]);
    }

    @Test
    public void testPlaceMarkOnEmptyRowAndColumnWithPlayerO() {
        boolean markWithO = board.placeMark("0", "0", Marker.O);
        assertTrue(markWithO);
        assertEquals(Marker.O, board.getBoardGrid()[0][0]);
    }

    @Test
    public void testPlaceMarkOnTwoEmptyRowAndColumnWithPlayerXAndPlayerO() {
        boolean markWithX = board.placeMark("1", "1", Marker.X);
        assertTrue(markWithX);
        assertEquals(Marker.X, board.getBoardGrid()[1][1]);
        boolean markWithO = board.placeMark("0", "0", Marker.O);
        assertTrue(markWithO);
        assertEquals(Marker.O, board.getBoardGrid()[0][0]);
    }

    @Test
    public void testPlaceMarkOnDifferentEmptyRowAndColumnWithPlayerXAndO() {
        boolean markWithX = board.placeMark("1", "0", Marker.X);
        assertTrue(markWithX);
        assertEquals(Marker.X, board.getBoardGrid()[1][0]);
        boolean markWithO = board.placeMark("2", "0", Marker.O);
        assertTrue(markWithO);
        assertEquals(Marker.O, board.getBoardGrid()[2][0]);
    }

    @Test
    public void testRowAndColumnIsEmpty_whenThereIsNoPlayerXAndPlayerO() {
        boolean markWithX = board.isMarkEmpty(1, 1);
        assertEquals(board.getBoardGrid()[1][1], Marker.EMPTY);
        assertTrue(markWithX);
    }

    @Test
    public void testStraightRowAndColumn_forTheWinnerWhenGameEnd(){
        boolean markWithFirstO = board.placeMark("0", "0", Marker.O);
        assertTrue(markWithFirstO);
        assertEquals(Marker.O, board.getBoardGrid()[0][0]);

        boolean markWithSecondO = board.placeMark("1", "0", Marker.O);
        assertTrue(markWithSecondO);
        assertEquals(Marker.O, board.getBoardGrid()[1][0]);

        boolean markWithThirdO = board.placeMark("2", "0", Marker.O);
        assertTrue(markWithThirdO);
        assertEquals(Marker.O, board.getBoardGrid()[2][0]);

        assertTrue(board.checkWinner());
    }

    @Test
    public void testDiagonalRowAndColumn_forTheWinnerWhenGameEnd() {
            boolean markWithFirstO = board.placeMark("0", "0", Marker.O);
            assertTrue(markWithFirstO);
            assertEquals(Marker.O, board.getBoardGrid()[0][0]);

            boolean markWithSecondO = board.placeMark("1", "1", Marker.O);
            assertTrue(markWithSecondO);
            assertEquals(Marker.O, board.getBoardGrid()[1][1]);

            boolean markWithThirdO = board.placeMark("2", "2", Marker.O);
            assertTrue(markWithThirdO);
            assertEquals(Marker.O, board.getBoardGrid()[2][2]);

            assertTrue(board.checkWinner());
    }


}
