package tictactoe.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void placeMarkSucceedsOnEmptySquare() {
        Board board = new Board();
        assertTrue(board.placeMark(new Move(0, 0), Mark.X));
        assertEquals(Mark.X, board.getMark(0, 0));
    }

    @Test
    void placeMarkFailsOnOccupiedSquare() {
        Board board = new Board();
        board.placeMark(new Move(0, 0), Mark.X);
        assertFalse(board.placeMark(new Move(0, 0), Mark.O));
    }

    @Test
    void detectsRowWin() {
        Board board = new Board();
        board.placeMark(new Move(0, 0), Mark.X);
        board.placeMark(new Move(0, 1), Mark.X);
        board.placeMark(new Move(0, 2), Mark.X);

        assertEquals(GameResult.X_WINS, board.evaluate());
    }

    @Test
    void detectsTie() {
        Board board = new Board();
        board.placeMark(new Move(0, 0), Mark.X);
        board.placeMark(new Move(0, 1), Mark.O);
        board.placeMark(new Move(0, 2), Mark.X);
        board.placeMark(new Move(1, 0), Mark.X);
        board.placeMark(new Move(1, 1), Mark.O);
        board.placeMark(new Move(1, 2), Mark.O);
        board.placeMark(new Move(2, 0), Mark.O);
        board.placeMark(new Move(2, 1), Mark.X);
        board.placeMark(new Move(2, 2), Mark.X);

        assertEquals(GameResult.TIE, board.evaluate());
    }
}