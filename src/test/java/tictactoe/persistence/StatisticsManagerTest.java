package tictactoe.persistence;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class StatisticsManagerTest {

    @Test
    void savesAndLoadsStats() throws Exception {
        Path tempFile = Files.createTempFile("ttt-stats", ".properties");

        StatisticsManager manager = new StatisticsManager(tempFile);
        manager.saveStats(3, 2, 1);

        int[] loaded = manager.loadStats();

        assertArrayEquals(new int[]{3, 2, 1}, loaded);

        Files.deleteIfExists(tempFile);
    }
}