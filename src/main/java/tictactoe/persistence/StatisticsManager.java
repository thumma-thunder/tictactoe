package tictactoe.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class StatisticsManager {
    private final Path filePath;

    public StatisticsManager(Path filePath) {
        this.filePath = filePath;
    }

    public void saveStats(int xWins, int oWins, int ties) throws IOException {
        Properties props = new Properties();
        props.setProperty("xWins", String.valueOf(xWins));
        props.setProperty("oWins", String.valueOf(oWins));
        props.setProperty("ties", String.valueOf(ties));

        try (var out = Files.newOutputStream(filePath)) {
            props.store(out, "Tic Tac Toe Statistics");
        }
    }

    public int[] loadStats() throws IOException {
        if (!Files.exists(filePath)) {
            return new int[]{0, 0, 0};
        }

        Properties props = new Properties();
        try (var in = Files.newInputStream(filePath)) {
            props.load(in);
        }

        int xWins = Integer.parseInt(props.getProperty("xWins", "0"));
        int oWins = Integer.parseInt(props.getProperty("oWins", "0"));
        int ties = Integer.parseInt(props.getProperty("ties", "0"));

        return new int[]{xWins, oWins, ties};
    }
}