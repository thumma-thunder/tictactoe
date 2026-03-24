package tictactoe.controller;

import tictactoe.model.Game;
import tictactoe.model.GameResult;
import tictactoe.persistence.StatisticsManager;

import java.io.IOException;

public class GameController {
    private final Game game;
    private final StatisticsManager statisticsManager;

    private int xWins;
    private int oWins;
    private int ties;

    public GameController(Game game, StatisticsManager statisticsManager) throws IOException {
        this.game = game;
        this.statisticsManager = statisticsManager;

        int[] stats = statisticsManager.loadStats();
        xWins = stats[0];
        oWins = stats[1];
        ties = stats[2];
    }

    public Game getGame() {
        return game;
    }

    public void playHumanTurn(int row, int col) throws IOException {
        game.submitHumanMove(row, col);
        game.playTurn();
        updateStatsIfFinished();

        if (game.getResult() == GameResult.IN_PROGRESS && !game.getCurrentPlayer().isHuman()) {
            game.playTurn();
            updateStatsIfFinished();
        }
    }

    public void restartGame() {
        game.reset();
    }

    public int getXWins() {
        return xWins;
    }

    public int getOWins() {
        return oWins;
    }

    public int getTies() {
        return ties;
    }

    private void updateStatsIfFinished() throws IOException {
        switch (game.getResult()) {
            case X_WINS -> xWins++;
            case O_WINS -> oWins++;
            case TIE -> ties++;
            default -> {
                return;
            }
        }
        statisticsManager.saveStats(xWins, oWins, ties);
    }
}