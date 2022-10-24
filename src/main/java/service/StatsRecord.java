package service;

import dto.Computer;
import dto.Player;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatsRecord {

    protected static final String PATH = "C:\\Users\\ilmln\\IdeaProjects\\rockPaperScissors\\" +
            "src\\main\\java\\logfiles\\";
    protected static final String FILEPATH = "\\logFileResult_";
    private static final int DASHES = 40;

    protected static boolean recordStats(Player player, Computer computer) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        File resultFile = new File(PATH + FILEPATH + player.getName());

        String result = "Player: " + player.getName() + "\t".repeat(3) + "Computer" + "\n" +
                    "Wins: " + player.getWin() + "\t".repeat(5) + "Wins: " + computer.getWin() + "\n" +
                    "Loses: " + player.getLose() + "\t".repeat(4) + "Loses: " + computer.getLose() + "\n" +
                    "Ties: " + player.getTie() + "\t".repeat(5) + "Ties: " + computer.getTie() + "\n" +
                    Beautify.lineDelimiter(DASHES + DASHES + 19) + "\n" +
                    "Count Games: " + (player.getWin() + player.getLose() + player.getTie()) + "\n" +
                    "Win Rate: " + player.getWinRate() + "%" + "\t".repeat(2) +
                    "Win Rate: " + computer.getWinRate() + "%" + "\n".repeat(2);

        try (FileWriter fileWriter = new FileWriter(resultFile, true)) {
            fileWriter.write(Beautify.lineDelimiter(DASHES) + formatter.format(date) +
                    Beautify.lineDelimiter(DASHES) + "\n");
            fileWriter.write(result);
        } catch (IOException e) {
            System.err.println("It is not possible to write information!");
            return false;
        }
            return true;
        }
    }
