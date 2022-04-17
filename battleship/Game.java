package battleship;

import battleship.CustomExeptions.GameOverException;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Game {
    Player player1;
    Player player2;

    public Game() {
        player1 = new Player();
        player2 = new Player();
    }

    void turn(Scanner scanner) throws GameOverException {
        player1.printBattleField(player2.getBattleField().getFogOfWar());
        System.out.println("---------------------");
        player2.printBattleField(player1.getBattleField().getToDisplay());
        System.out.println("Player 1, it's your turn:");
        String input = scanner.nextLine().toUpperCase(Locale.ROOT);
        try {
            takeAShotAt(parseCoordinates(input), player2.battleField);
        } catch (GameOverException e) {
            throw new GameOverException("Congrats Player 1 " + e.getMessage());
        }
        promptEnterKey(scanner);
        clearScreen();
        player1.printBattleField(player1.getBattleField().getFogOfWar());
        System.out.println("---------------------");
        player2.printBattleField(player2.getBattleField().getToDisplay());
        System.out.println("Player 2, it's your turn:");
        input = scanner.nextLine().toUpperCase(Locale.ROOT);
        try {
            takeAShotAt(parseCoordinates(input), player1.getBattleField());
        } catch (GameOverException e) {
            throw new GameOverException("Congrats Player 2 " + e.getMessage());
        }
        promptEnterKey(scanner);
        clearScreen();
    }

    void inputShips(Scanner scanner) {
        System.out.println("Player 1, place your ships on the game field");
        player1.printBattleField(player1.getBattleField().getBattleField());
        placeShips(scanner, player1.getBattleField());
        promptEnterKey(scanner);
        clearScreen();
        System.out.println("Player 2, place your ships on the game field");
        player2.printBattleField(player2.getBattleField().getBattleField());
        placeShips(scanner, player2.getBattleField());
        promptEnterKey(scanner);
        clearScreen();
    }


    private static void placeShips(Scanner scanner, BattleField battleField) {
        boolean isSubmarinePlaces = false;
        for (int i = 5; i >= 2; i--) {
            System.out.println();
            String ship = getShip(i, isSubmarinePlaces);
            System.out.printf("Enter the coordinates of the %s (%d cells):%n", ship, i);
            battleField.inputShip(scanner, i);
            if (i == 3) {
                if (!isSubmarinePlaces) {
                    i++;
                }
                isSubmarinePlaces = true;
            }
            battleField.printBattleField(battleField.getBattleField());
        }
    }

    private static String getShip(int i, boolean isSubmarinePlaces) {
        switch (i) {
            case 2:
                return "Destroyer";
            case 3:
                if (isSubmarinePlaces) {
                    return "Cruiser";
                } else {
                    return "Submarine";
                }
            case 4:
                return "Battleship";
            case 5:
                return "Aircraft Carrier";
            default:
                return "Can't find ship to place";
        }
    }

    private void promptEnterKey(Scanner scanner) {
        System.out.println("Press Enter and pass the move to another player");
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String not = scanner.nextLine();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    boolean isOver() {
        return player1.isGameOver() || player2.isGameOver();
    }

    private int[] parseCoordinates(String input) {
        int r = input.charAt(0) - 64;
        int c = Integer.parseInt(input.substring(1));
        return new int[]{r, c};
    }

    public void takeAShotAt(int[] shotCoordinates, BattleField battleField) throws GameOverException {
        int r = shotCoordinates[0];
        int c = shotCoordinates[1];
        if (coordinatesAreValid(r, c, battleField)) {
            if (battleField.getBattleField()[r][c].equals("O")) {
                //TODO uncomment these line, after completion of the project,
                // to unlock new functionality


//                battleField[r][c] = "X";
                battleField.getFogOfWar()[r][c] = "X";
                battleField.getToDisplay()[r][c] = "X";
//                this.hitsLeft--;
//                printBattleField(battleField.getFogOfWar());
                if (battleField.fleet.isShipSunk(new int[]{r, c})) {
                    if (battleField.getFleet().isSunk()) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        throw new GameOverException("you sank the last ship. You won. Congratulations!");
                    } else {
                        System.out.println("You sank a ship!");
                    }
                } else {
                    System.out.println("You hit a ship!");

                }
            } else if (battleField.getBattleField()[r][c].equals("~")) {
                //TODO uncomment these line, after completion of the project,
                // to unlock new functionality
//                battleField[r][c] = "M";
                battleField.getFogOfWar()[r][c] = "M";
                battleField.getToDisplay()[r][c] = "M";
//                printBattleField(battleField.getFogOfWar());
                System.out.println("You missed!");
                //TODO uncomment these line, after completion of the project,
                // to unlock new functionality

//                } else {
//                System.out.println("You already shot at these coordinates!");
//                return false;
            }
//        } else {
//            System.out.println(("Error! You entered the wrong coordinates! Try again:"));
//            takeAShot(scanner);
        }
    }

    private boolean coordinatesAreValid(int r, int c, BattleField battleField) {
        return r >= 1 && r < battleField.getBattleField().length && c >= 1 && c < battleField.getBattleField()[0].length;
    }
}
