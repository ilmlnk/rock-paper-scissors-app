package service;

import dto.Computer;
import dto.Player;

import java.util.NoSuchElementException;

import static dto.DependencyTable.dependencyTable;

public class WinDefinition {
    protected static String defineWin(Player player, Computer computer, int playerChoice, int computerChoice) {
        if (playerChoice < 0 && computerChoice < 0) throw new NoSuchElementException();

        if (playerChoice == 0 && computerChoice == 1 ||
                playerChoice == 2 && computerChoice == 0 || playerChoice == 1 && computerChoice == 2) {
            player.setWin(player.getWin() + 1);
            computer.setLose(computer.getLose() + 1);
            return player.getName() + " is a winner! Combination: " +
                    dependencyTable[playerChoice][computerChoice];
        }
        else if (playerChoice == 2 && computerChoice == 1 ||
                playerChoice == 0 && computerChoice == 2 || playerChoice == 1 && computerChoice == 0) {
            player.setLose(player.getLose() + 1);
            computer.setWin(computer.getWin() + 1);
            return "Computer is a winner! Combination: " +
                    dependencyTable[playerChoice][computerChoice];
        }
        else {
            player.setTie(player.getTie() + 1);
            computer.setTie(computer.getTie() + 1);
            return "Tie! Combination: " +
                    dependencyTable[playerChoice][computerChoice];
        }
    }
}
