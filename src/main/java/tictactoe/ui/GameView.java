package tictactoe.ui;

import tictactoe.controller.GameController;
import tictactoe.controller.command.MakeMoveCommand;
import tictactoe.controller.command.RestartGameCommand;
import tictactoe.model.Board;
import tictactoe.model.Game;
import tictactoe.model.GameResult;
import tictactoe.model.Mark;
import tictactoe.model.observer.GameObserver;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame implements GameObserver {
    private final GameController controller;
    private final JButton[][] buttons = new JButton[3][3];
    private final JLabel statusLabel = new JLabel("Welcome to Tic Tac Toe");
    private final JLabel statsLabel = new JLabel();

    public GameView(GameController controller) {
        this.controller = controller;

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 520);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        Game game = controller.getGame();
        game.addObserver(this);

        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.BOLD, 48));
                button.setFocusPainted(false);
                button.setBackground(Color.WHITE);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                int row = r;
                int col = c;

                button.addActionListener(e -> {
                    if (controller.getGame().getBoard().getMark(row, col) != Mark.EMPTY) {
                        return;
                    }
                    new MakeMoveCommand(controller, row, col).execute();
                });

                buttons[r][c] = button;
                boardPanel.add(button);
            }
        }

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            new RestartGameCommand(controller).execute();
        });

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        topPanel.setBackground(new Color(245, 245, 245));

        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        topPanel.add(statusLabel);
        topPanel.add(statsLabel);
        add(topPanel, BorderLayout.NORTH);       add(boardPanel, BorderLayout.CENTER);
        add(restartButton, BorderLayout.SOUTH);

        refresh();
        setLocationRelativeTo(null);
    }

    @Override
    public void onGameChanged(Game game) {
        refresh();
    }

    private void refresh() {
        Game game = controller.getGame();

        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                Mark mark = game.getBoard().getMark(r, c);
                if (mark == Mark.X) {
                    buttons[r][c].setText("X");
                    buttons[r][c].setForeground(new Color(0, 102, 204)); // blue
                } else if (mark == Mark.O) {
                    buttons[r][c].setText("O");
                    buttons[r][c].setForeground(new Color(204, 0, 0)); // red
                } else {
                    buttons[r][c].setText("");
                }
                boolean gameOver = game.getResult() != GameResult.IN_PROGRESS;
                buttons[r][c].setEnabled(!gameOver);
            }
        }

        GameResult result = game.getResult();

        if (result == GameResult.IN_PROGRESS) {
            statusLabel.setText("Current turn: " + game.getCurrentPlayer().getName());
            statusLabel.setForeground(Color.BLACK);
        } else {
            statusLabel.setText("Result: " + result);
            statusLabel.setForeground(new Color(0, 153, 0)); // green for win/tie
            }
        statsLabel.setText(
                "X Wins: " + controller.getXWins()
                        + " | O Wins: " + controller.getOWins()
                        + " | Ties: " + controller.getTies()
        );
    }
}