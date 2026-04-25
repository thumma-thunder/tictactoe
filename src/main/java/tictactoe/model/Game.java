package tictactoe.model;

import tictactoe.model.observer.GameObserver;
import tictactoe.model.observer.GameSubject;
import tictactoe.player.HumanPlayer;
import tictactoe.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game implements GameSubject {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private final List<GameObserver> observers = new ArrayList<>();

    private Player currentPlayer;
    private GameResult result;

    public Game(Player playerX, Player playerO) {
        this.board = new Board();
        this.playerX = playerX;
        this.playerO = playerO;
        this.currentPlayer = playerX;
        this.result = GameResult.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameResult getResult() {
        return result;
    }

    public boolean playTurn() {
        if (result != GameResult.IN_PROGRESS) {
            return false;
        }

        Move move = currentPlayer.chooseMove(board);

        if (move == null) {
            return false;
        }

        boolean placed = board.placeMark(move, currentPlayer.getMark());
        if (!placed) {
            return false;
        }

        result = board.evaluate();
        if (result == GameResult.IN_PROGRESS) {
            currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        }

        notifyObservers();
        return true;
    }

    public void submitHumanMove(int row, int col) {
        if (!(currentPlayer instanceof HumanPlayer human)) {
            throw new IllegalStateException("Current player is not human.");
        }
        human.setPendingMove(new Move(row, col));
    }

    public void reset() {
        board.reset();
        currentPlayer = playerX;
        result = GameResult.IN_PROGRESS;
        notifyObservers();
    }

    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.onGameChanged(this);
        }
    }
}