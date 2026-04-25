package tictactoe;

import tictactoe.controller.GameController;
import tictactoe.model.Game;
import tictactoe.model.Mark;
import tictactoe.persistence.StatisticsManager;
import tictactoe.player.Player;
import tictactoe.player.PlayerFactory;
import tictactoe.player.strategy.RandomMoveStrategy;
import tictactoe.ui.GameView;

import javax.swing.*;
import java.nio.file.Path;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        PlayerFactory factory = new PlayerFactory();

        String[] options = {"Human vs Computer", "Human vs Human"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose game mode:",
                "Tic Tac Toe",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        Player playerX = factory.createHumanPlayer("Player X", Mark.X);
        Player playerO;

        if (choice == 1) {
            playerO = factory.createHumanPlayer("Player O", Mark.O);
        } else {
            playerO = factory.createComputerPlayer(
                    "Computer",
                    Mark.O,
                    new RandomMoveStrategy(new Random())
            );
        }

        Game game = new Game(playerX, playerO);
        StatisticsManager stats = new StatisticsManager(Path.of("stats.properties"));
        GameController controller = new GameController(game, stats);

        SwingUtilities.invokeLater(() -> {
            GameView frame = new GameView(controller);
            frame.setVisible(true);
        });
    }
}