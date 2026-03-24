package tictactoe.player;

import tictactoe.model.Mark;
import tictactoe.player.strategy.MoveStrategy;

public class PlayerFactory {
    public Player createHumanPlayer(String name, Mark mark) {
        return new HumanPlayer(name, mark);
    }

    public Player createComputerPlayer(String name, Mark mark, MoveStrategy strategy) {
        return new ComputerPlayer(name, mark, strategy);
    }
}