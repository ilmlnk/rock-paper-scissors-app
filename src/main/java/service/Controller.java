package service;

import dto.Computer;
import dto.DependencyTable;
import dto.Player;
import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import static service.Beautify.lineDelimiter;
import static service.LanguageDefinition.chooseLanguage;
import static service.PrintStatistics.printStats;
import static service.ToolDefinition.defineTool;
import static service.WinDefinition.defineWin;

public class Controller extends DependencyTable {
    private static final int DASHES = 70;
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getSimpleName());
    private Player player;
    private Computer computer;
    private int countGames;

    public void runGame() throws NumberFormatException {
        ResourceBundle resourceBundle;
        Locale locale;
        Scanner scanner = new Scanner(System.in);
        Locale.setDefault(new Locale("uk", "UA"));                      /* SETTING DEFAULT LOCALE */

        try {
            computer = new Computer();
            boolean nextGame = true;

            String welcomeMessage = """
                    Яку мову бажаєш обрати?
                    [1] - Українська
                    [2] - English
                    [3] - Deutsch
                    Ввести:\040""";

            System.out.print(new String(welcomeMessage.getBytes(), StandardCharsets.UTF_8));
            int language = Integer.parseInt(scanner.nextLine());
            locale = chooseLanguage(language);
            resourceBundle = ResourceBundle.getBundle("l10n", locale);

            System.out.print(resourceBundle.getString("greeting") + "\n-> ");                       /* GREETING */
            String name = scanner.nextLine();

            player = new Player(name);

            LOGGER.info(player.getName() + " authorized and started the game.");

            System.out.println(resourceBundle.getString("dialog_1") +
                    " " + player.getName() + " :)");                                                    /* DIALOG #1 */
            System.out.print(resourceBundle.getString("dialog_2") + "\n-> ");                      /* DIALOG #2 */
            int plays = Integer.parseInt(scanner.nextLine());

            /* LOGGER LINE */
            LOGGER.info("Amount of plays : " + plays);
            /* END OF LOGGER LINE */

            System.out.println(resourceBundle.getString("intro"));                                  /* INTRO */
            do {
                System.out.println(lineDelimiter(DASHES));

                System.out.println(resourceBundle.getString("tools"));
                System.out.println(resourceBundle.getString("rock"));
                System.out.println(resourceBundle.getString("paper"));
                System.out.println(resourceBundle.getString("scissors"));                           /* TOOLS */
                System.out.print("-> ");

                /* defining the tools for Player and Computer */
                String toolChoice = scanner.nextLine();
                String computerChoice = computer.getComputerDecision();

                /* setting tools for player and computer */
                player.setTool(toolChoice);
                computer.setTool(computerChoice);

                LOGGER.info("Player choice : " + player.getTool());
                LOGGER.info("Computer choice : " + computer.getTool());

                System.out.println(lineDelimiter(DASHES));

                /* define the winner */
                System.out.println(resourceBundle.getString("result") + ": ");                     /* RESULT */
                String resultChoice = defineWin(player, computer,
                        defineTool(toolChoice), defineTool(computerChoice));

                /*                        LOGGER BLOCK                              */
                LOGGER.debug(LocalDate.now() + " " + lineDelimiter(3) + " P: " +
                        toolChoice + " C: " + computerChoice);
                /*                     END OF LOGGER BLOCK                          */

                System.out.println(resultChoice);
                System.out.println(lineDelimiter(DASHES));

                /* save number of victories, loses and ties */
                countGames = player.getWin() + player.getLose() + player.getTie();
                if (countGames == plays) break;

                /*                  LOGGER BLOCK                            */
                LOGGER.info("Player victories : " + player.getWin());
                LOGGER.info("Player loses : " + player.getLose());
                LOGGER.info("Player ties : " + player.getTie());

                LOGGER.info("Computer victories : " + computer.getWin());
                LOGGER.info("Computer loses : " + computer.getLose());
                LOGGER.info("Computer ties : " + computer.getTie());

                LOGGER.info("Amount of plays left : " + (plays - countGames));
                /*              END OF LOGGER BLOCK                         */

                System.out.println(resourceBundle.getString("question"));                           /* QUESTION */

                String buffer = scanner.nextLine();
                if (buffer.equalsIgnoreCase("N")) nextGame = false;

            } while (nextGame);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Array Index Out Of Bounds Exception!");
            LOGGER.error(e);
        } catch (NumberFormatException e) {
            System.err.println("Number Format Exception!");
            LOGGER.error(e);
        }

        String statsLanguage = """
                    Обери мову для відображення статистики:
                    [1] - Українська
                    [2] - English
                    [3] - Deutsch
                    Ввести:\040""";

        System.out.print(new String(statsLanguage.getBytes(), StandardCharsets.UTF_8));

        int choosingLocale = Integer.parseInt(scanner.nextLine());
        locale = chooseLanguage(choosingLocale);
        resourceBundle = ResourceBundle.getBundle("l10n", locale);

        System.out.println();
        printStats(player, computer, resourceBundle);
        LOGGER.info("Statistics were displayed successfully. Player " +
                player.getName() + " has left the game.");

    }
}
