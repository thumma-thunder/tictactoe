package tictactoe.player;

import org.junit.jupiter.api.Test;
import tictactoe.model.Mark;
import tictactoe.player.strategy.RandomMoveStrategy;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerFactoryTest {

    @Test
    void createsHumanPlayer() {
        PlayerFactory factory = new PlayerFactory();

        Player player = factory.createHumanPlayer("Player X", Mark.X);

        assertNotNull(player);
        assertTrue(player.isHuman());
        assertEquals("Player X", player.getName());
        assertEquals(Mark.X, player.getMark());
    }

    @Test
    void createsComputerPlayer() {
        PlayerFactory factory = new PlayerFactory();

        Player player = factory.createComputerPlayer(
                "Computer",
                Mark.O,
                new RandomMoveStrategy(new Random(1))
        );

        assertNotNull(player);
        assertFalse(player.isHuman());
        assertEquals("Computer", player.getName());
        assertEquals(Mark.O, player.getMark());
    }
}