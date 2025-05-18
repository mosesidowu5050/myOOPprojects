package ticTacToe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        player = new Player("Moses", Marker.X);
    }

    @Test
    public void testPlayerCreation() {
        assertEquals("Moses", player.getName());
        assertEquals(Marker.X, player.getMarker());
    }

    @Test
    public void testPlayerMakesAMoveInTheBoard() {
        boolean success = player.makeMove(board, "0", "1");
        assertTrue(success);
        assertEquals(Marker.X, board.getBoardGrid()[0][1]);

        boolean success2 = player.makeMove(board, "0", "2");
        assertTrue(success2);
        assertEquals(Marker.X, board.getBoardGrid()[0][2]);
    }

    @Test
    public void testPlayerFailsToMoveToOccupiedTheBoard() {
        player.makeMove(board, "0", "1");
        boolean secondMove = player.makeMove(board, "0", "1");
        assertFalse(secondMove);
    }

    @Test
    public void testDifferentPlayersHaveDifferentMarkers() {
        Player player2 = new Player("Bob", Marker.O);
        assertNotEquals(player.getMarker(), player2.getMarker());
        assertEquals(Marker.O, player2.getMarker());
    }
}
