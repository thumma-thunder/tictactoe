package tictactoe.player;

import tictactoe.model.Board;
import tictactoe.model.Mark;
import tictactoe.model.Move;
import tictactoe.player.strategy.MoveStrategy;

public class ComputerPlayer implements Player {
    private final String name;
    private final Mark mark;
    private final MoveStrategy strategy;

    public ComputerPlayer(String name, Mark mark, MoveStrategy strategy) {
        this.name = name;
        this.mark = mark;
        this.strategy = strategy;
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
        return strategy.chooseMove(board, mark);
    }

    @Override
    public boolean isHuman() {
        return false;
    }
}