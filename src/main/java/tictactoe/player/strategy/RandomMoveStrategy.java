package tictactoe.player.strategy;

import tictactoe.model.Board;
import tictactoe.model.Mark;
import tictactoe.model.Move;

import java.util.List;
import java.util.Random;

public class RandomMoveStrategy implements MoveStrategy {
    private final Random random;

    public RandomMoveStrategy(Random random) {
        this.random = random;
    }

    @Override
    public Move chooseMove(Board board, Mark mark) {
        List<Move> moves = board.getAvailableMoves();
        if (moves.isEmpty()) {
            throw new IllegalStateException("No valid moves available.");
        }
        return moves.get(random.nextInt(moves.size()));
    }
}