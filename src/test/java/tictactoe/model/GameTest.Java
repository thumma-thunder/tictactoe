package tictactoe.model;

import org.junit.jupiter.api.Test;
import tictactoe.player.Player;
import tictactoe.player.strategy.FirstOpenMoveStrategy;
import tictactoe.player.ComputerPlayer;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    @Test
    void gameProgressesAndAlternatesTurns() {
        Player x = new ComputerPlayer("X", Mark.X, new FirstOpenMoveStrategy());
        Player o = new ComputerPlayer("O", Mark.O, new FirstOpenMoveStrategy());

        Game game = new Game(x, o);

        game.playTurn();
        assertEquals("O", game.getCurrentPlayer().getName());

        game.playTurn();
        assertEquals("X", game.getCurrentPlayer().getName());
    }
    @Test
    void resetRestartsGame() {
        Player x = new ComputerPlayer("X", Mark.X, new FirstOpenMoveStrategy());
        Player o = new ComputerPlayer("O", Mark.O, new FirstOpenMoveStrategy());

        Game game = new Game(x, o);
        game.playTurn();

        game.reset();

        assertEquals(GameResult.IN_PROGRESS, game.getResult());
        assertEquals("X", game.getCurrentPlayer().getName());
        assertEquals(Mark.EMPTY, game.getBoard().getMark(0, 0));
    }

    @Test
    void observerIsNotifiedAfterTurn() {
        Player x = new ComputerPlayer("X", Mark.X, new FirstOpenMoveStrategy());
        Player o = new ComputerPlayer("O", Mark.O, new FirstOpenMoveStrategy());

        Game game = new Game(x, o);

        final boolean[] notified = {false};

        game.addObserver(changedGame -> notified[0] = true);

        game.playTurn();

        assertTrue(notified[0]);
    }
}