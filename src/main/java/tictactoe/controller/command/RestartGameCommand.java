package tictactoe.controller.command;

import tictactoe.controller.GameController;

public class RestartGameCommand implements GameCommand {
    private final GameController controller;

    public RestartGameCommand(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.restartGame();
    }
}