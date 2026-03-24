package tictactoe;

import tictactoe.controller.GameController;
import tictactoe.model.Game;
import tictactoe.model.Mark;
import tictactoe.persistence.StatisticsManager;
import tictactoe.player.Player;
import tictactoe.player.PlayerFactory;
import tictactoe.player.strategy.RandomMoveStrategy;
import tictactoe.ui.GameFrame;

import javax.swing.*;
import java.nio.file.Path;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        PlayerFactory factory = new PlayerFactory();

        Player playerX = factory.createHumanPlayer("Player X", Mark.X);
        Player playerO = factory.createComputerPlayer("Computer", Mark.O, new RandomMoveStrategy(new Random()));

        Game game = new Game(playerX, playerO);
        StatisticsManager stats = new StatisticsManager(Path.of("stats.properties"));
        GameController controller = new GameController(game, stats);

        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame(controller);
            frame.setVisible(true);
        });
    }
}