package tictactoe.controller.command;

import tictactoe.controller.GameController;

import java.io.IOException;

public class MakeMoveCommand implements GameCommand {
    private final GameController controller;
    private final int row;
    private final int col;

    public MakeMoveCommand(GameController controller, int row, int col) {
        this.controller = controller;
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute() {
        try {
            controller.playHumanTurn(row, col);
        } catch (IOException e) {
            throw new RuntimeException("Failed to play move.", e);
        }
    }
}