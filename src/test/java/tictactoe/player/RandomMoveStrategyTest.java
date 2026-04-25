package tictactoe.player;

import org.junit.jupiter.api.Test;
import tictactoe.model.Board;
import tictactoe.model.Mark;
import tictactoe.model.Move;
import tictactoe.player.strategy.RandomMoveStrategy;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RandomMoveStrategyTest {

    @Test
    void computerChoosesValidMove() {
        Board board = new Board();
        RandomMoveStrategy strategy = new RandomMoveStrategy(new Random(1));

        Move move = strategy.chooseMove(board, Mark.X);

        assertNotNull(move);
        assertEquals(Mark.EMPTY, board.getMark(move.row(), move.col()));
    }
}