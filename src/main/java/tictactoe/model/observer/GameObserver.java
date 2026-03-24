package tictactoe.model.observer;

import tictactoe.model.Game;

public interface GameObserver {
    void onGameChanged(Game game);
}