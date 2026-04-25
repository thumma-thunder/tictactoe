package tictactoe.player.strategy;

import tictactoe.model.Board;
import tictactoe.model.Mark;
import tictactoe.model.Move;

public interface MoveStrategy {
    Move chooseMove(Board board, Mark mark);
}