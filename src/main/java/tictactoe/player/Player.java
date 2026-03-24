package tictactoe.player;

import tictactoe.model.Board;
import tictactoe.model.Mark;
import tictactoe.model.Move;

public interface Player {
    String getName();
    Mark getMark();
    Move chooseMove(Board board);
    boolean isHuman();
}