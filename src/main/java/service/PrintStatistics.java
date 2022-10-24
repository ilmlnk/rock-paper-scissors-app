package service;

import dto.Computer;
import dto.Player;

import java.util.Locale;
import java.util.ResourceBundle;

import static service.Beautify.lineDelimiter;

public class PrintStatistics {
    private static final int DASHES = 70;
    protected static void printStats(Player player, Computer computer, ResourceBundle resourceBundle) {
        int winsPlayer = player.getWin();
        int losesPlayer = player.getLose();
        int tiesPlayer = player.getTie();

        int winsComputer = computer.getWin();
        int losesComputer = computer.getLose();
        int tiesComputer = computer.getTie();

        int countGames = player.getWin() + player.getLose() + player.getTie();

        System.out.println(resourceBundle.getString("result").toUpperCase());                     /* RESULT */

        System.out.println(lineDelimiter(DASHES));

        System.out.println(resourceBundle.getString("player").toUpperCase());                     /* PLAYER */

        System.out.println(lineDelimiter(DASHES));

        System.out.println(resourceBundle.getString("wins") + ": " + winsPlayer);                  /* WINS */
        System.out.println(resourceBundle.getString("loses") + ": " + losesPlayer);                /* LOSES */
        System.out.println(resourceBundle.getString("ties") + ": " + tiesPlayer);                  /* TIES */

        System.out.println(lineDelimiter(DASHES));
        System.out.println();

        System.out.println(resourceBundle.getString("computer").toUpperCase());                  /* COMPUTER */

        System.out.println(lineDelimiter(DASHES));

        System.out.println(resourceBundle.getString("wins") + ": " + winsComputer);                /* WINS */
        System.out.println(resourceBundle.getString("loses") + ": " + losesComputer);              /* LOSES */
        System.out.println(resourceBundle.getString("ties") + ": " + tiesComputer);                /* TIES */

        try {
            player.setWinRate((double) winsPlayer / (winsPlayer + losesPlayer) * 100);
            System.out.println(lineDelimiter(DASHES));
            System.out.println(resourceBundle.getString("player") + " " + player.getName() + " " +
                    resourceBundle.getString("winrate").toLowerCase() + ": "
                    + String.format("%.2f", player.getWinRate()) + "%");                              /* WINRATE */

            computer.setWinRate((double) winsComputer / (winsComputer + losesComputer) * 100);
            System.out.println(resourceBundle.getString("computer") + " "
                    + resourceBundle.getString("winrate").toLowerCase() + " " +
                    String.format("%.2f", computer.getWinRate()) + "%");     /* WINRATE */

        } catch (ArithmeticException e) {
            System.err.println(resourceBundle.getString("stats_exception"));                  /* STATS_EXCEPTION */
        } finally {
            System.out.println(resourceBundle.getString("games_number") + ": " + countGames);   /* GAMES_NUMBER */
            StatsRecord.recordStats(player, computer);
            System.out.println(lineDelimiter(DASHES));
        }
    }
}
