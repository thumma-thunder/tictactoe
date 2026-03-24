package tictactoe.player;

import tictactoe.model.Board;
import tictactoe.model.Mark;
import tictactoe.model.Move;

public class HumanPlayer implements Player {
    private final String name;
    private final Mark mark;
    private Move pendingMove;

    public HumanPlayer(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }

    public void setPendingMove(Move move) {
        this.pendingMove = move;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    @Override
    public Move chooseMove(Board board) {
        if (pendingMove == null) {
            throw new IllegalStateException("No pending move set for human player.");
        }
        Move move = pendingMove;
        pendingMove = null;
        return move;
    }

    @Override
    public boolean isHuman() {
        return true;
    }
}