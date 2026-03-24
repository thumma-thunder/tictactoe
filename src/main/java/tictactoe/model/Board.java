package tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int SIZE = 3;
    private final Mark[][] grid;

    public Board() {
        grid = new Mark[SIZE][SIZE];
        reset();
    }

    public void reset() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = Mark.EMPTY;
            }
        }
    }

    public Mark getMark(int row, int col) {
        validateBounds(row, col);
        return grid[row][col];
    }

    public boolean placeMark(Move move, Mark mark) {
        validateBounds(move.row(), move.col());
        if (mark == Mark.EMPTY) {
            throw new IllegalArgumentException("Cannot place EMPTY mark.");
        }
        if (grid[move.row()][move.col()] != Mark.EMPTY) {
            return false;
        }
        grid[move.row()][move.col()] = mark;
        return true;
    }

    public boolean isFull() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c] == Mark.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Move> getAvailableMoves() {
        List<Move> moves = new ArrayList<>();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c] == Mark.EMPTY) {
                    moves.add(new Move(r, c));
                }
            }
        }
        return moves;
    }

    public GameResult evaluate() {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][0] != Mark.EMPTY && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                return grid[i][0] == Mark.X ? GameResult.X_WINS : GameResult.O_WINS;
            }
            if (grid[0][i] != Mark.EMPTY && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                return grid[0][i] == Mark.X ? GameResult.X_WINS : GameResult.O_WINS;
            }
        }

        if (grid[0][0] != Mark.EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            return grid[0][0] == Mark.X ? GameResult.X_WINS : GameResult.O_WINS;
        }

        if (grid[0][2] != Mark.EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return grid[0][2] == Mark.X ? GameResult.X_WINS : GameResult.O_WINS;
        }

        return isFull() ? GameResult.TIE : GameResult.IN_PROGRESS;
    }

    private void validateBounds(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Move out of bounds.");
        }
    }
}