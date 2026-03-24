package tictactoe.ui;

import tictactoe.controller.GameController;
import tictactoe.model.Board;
import tictactoe.model.Game;
import tictactoe.model.GameResult;
import tictactoe.model.Mark;
import tictactoe.model.observer.GameObserver;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame implements GameObserver {
    private final GameController controller;
    private final JButton[][] buttons = new JButton[3][3];
    private final JLabel statusLabel = new JLabel("Welcome to Tic Tac Toe");

    public GameFrame(GameController controller) {
        this.controller = controller;

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        Game game = controller.getGame();
        game.addObserver(this);

        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                JButton button = new JButton("");
                int row = r;
                int col = c;
                button.addActionListener(e -> {
                    try {
                        controller.playHumanTurn(row, col);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error saving statistics.");
                    }
                });
                buttons[r][c] = button;
                boardPanel.add(button);
            }
        }

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> controller.restartGame());

        add(statusLabel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(restartButton, BorderLayout.SOUTH);

        refresh();
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
                buttons[r][c].setText(mark == Mark.EMPTY ? "" : mark.name());
            }
        }

        GameResult result = game.getResult();
        if (result == GameResult.IN_PROGRESS) {
            statusLabel.setText("Current turn: " + game.getCurrentPlayer().getName());
        } else {
            statusLabel.setText("Result: " + result);
        }
    }
}