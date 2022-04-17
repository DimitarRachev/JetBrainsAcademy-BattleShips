package battleship;

import battleship.CustomExeptions.ShipPlacedTooCloseExeption;
import battleship.CustomExeptions.WhrongShipLocationExeption;
import battleship.CustomExeptions.WrongShipLengthExeption;

import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class BattleField {
    private String[][] battleField;
    private String[][] fogOfWar;
    private int hitsLeft;
    Ships fleet;

    public BattleField() {

        this.battleField = fillBattleField(this.battleField);
        this.fogOfWar = fillBattleField(this.fogOfWar);
        this.hitsLeft = 5 + 4 + 3 + 3 + 2;
        this.fleet = new Ships();
    }

    public String[][] getBattleField() {
        return battleField;
    }

    public String[][] getFogOfWar() {
        return fogOfWar;
    }

    private String[][] fillBattleField(String[][] battleField) {
        battleField = new String[11][11];
        battleField[0] = new String[]{" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String A = "A";
        for (int i = 1; i < battleField.length; i++) {
            battleField[i] = new String[]{A, "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};
            A = String.valueOf((char) (A.charAt(0) + 1));
        }
        return battleField;
    }

    public void inputShip(Scanner scanner, int shipLength) {
        try {
            placeShip(scanner, shipLength);
        } catch (WhrongShipLocationExeption e) {
            System.out.println("Error! Wrong ship location! Try again:");
            inputShip(scanner, shipLength);
        } catch (ShipPlacedTooCloseExeption e) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            inputShip(scanner, shipLength);
        } catch (WrongShipLengthExeption e) {
            System.out.println("Error! Wrong length of the ship! Try again:");
            inputShip(scanner, shipLength);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            inputShip(scanner, shipLength);
        }
    }

    private void placeShip(Scanner scanner, int shipLength) throws WrongShipLengthExeption, WhrongShipLocationExeption, ShipPlacedTooCloseExeption {
        String[] ship = scanner.nextLine().toUpperCase(Locale.ROOT).split("\\s+");

        ship = sortShip(ship);
        int[] coordinates = parseCoordinates(ship);
        if (!coordinatesAreValid(coordinates)) {
            throw new ArrayIndexOutOfBoundsException("Error! Ship indexes should be inside the battlefield! Try again:");
        }
        if (shipIsDiagonalPlaced(coordinates)) {
            throw new WhrongShipLocationExeption("Error! Wrong ship location! Try again:");
        }
        if (!coordinatesLengthIsValid(coordinates, shipLength)) {
            throw new WrongShipLengthExeption("Error! Wrong length of the ship! Try again:");
        }
        if (shipIsNextToAnother(coordinates)) {
            throw new ShipPlacedTooCloseExeption("Error! You placed it too close to another one. Try again:");
        }
        int startRow = coordinates[0];
        int startCol = coordinates[1];
        int endRow = coordinates[2];
        int endCol = coordinates[3];
        Set<int[]> shipCoordinates = new HashSet<>();
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                battleField[i][j] = "O";
                shipCoordinates.add(new int[]{i, j});
            }
        }
        fleet.addShip(new Ship(shipCoordinates));
    }

    private String[] sortShip(String[] carrier) {
        int[] coordinates = parseCoordinates(carrier);
        int startRow = coordinates[0];
        int startCol = coordinates[1];
        int endRow = coordinates[2];
        int endCol = coordinates[3];
        if (endCol < startCol || endRow < startRow) {
            String temp = carrier[0];
            carrier[0] = carrier[1];
            carrier[1] = temp;
        }
        return carrier;
    }

    private boolean shipIsNextToAnother(int[] coordinates) {
        int startRow = coordinates[0];
        int startCol = coordinates[1];
        int endRow = coordinates[2];
        int endCol = coordinates[3];
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (isCloseToAnother(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCloseToAnother(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (coordinatesAreValid(i, j)) {
                    if (this.battleField[i][j].equals("O")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean coordinatesAreValid(int r, int c) {
        return r >= 1 && r < battleField.length && c >= 1 && c < battleField[0].length;
    }

    private boolean coordinatesAreValid(int[] coordinates) {
        int startRow = coordinates[0];
        int startCol = coordinates[1];
        int endRow = coordinates[2];
        int endCol = coordinates[3];
        return startRow > 0 && startRow < battleField.length && startCol > 0 && startCol < battleField[0].length && endRow > 0 && endRow < battleField.length && endCol > 0 && endCol < battleField[0].length;
    }

    private boolean shipIsDiagonalPlaced(int[] coordinates) {
        int startRow = coordinates[0];
        int startCol = coordinates[1];
        int endRow = coordinates[2];
        int endCol = coordinates[3];
        return startRow != endRow && startCol != endCol;
    }

    private boolean coordinatesLengthIsValid(int[] coordinates, int shipLength) {
        int startRow = coordinates[0];
        int startCol = coordinates[1];
        int endRow = coordinates[2];
        int endCol = coordinates[3];
        return (startRow == endRow && endCol - startCol == shipLength - 1) || (startCol == endCol && endRow - startRow == shipLength - 1);
    }

    private int[] parseCoordinates(String input) {
        int r = input.charAt(0) - 64;
        int c = Integer.parseInt(input.substring(1));
        return new int[]{r, c};
    }

    private int[] parseCoordinates(String[] carrier) {
        int startRow = carrier[0].charAt(0) - 64;
        int startCol = Integer.parseInt(carrier[0].substring(1));
        int endRow = carrier[1].charAt(0) - 64;
        int endCol = Integer.parseInt(carrier[1].substring(1));
        return new int[]{startRow, startCol, endRow, endCol};
    }

    public void printBattleField(String[][] battleField) {
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField[i].length; j++) {
                System.out.print(battleField[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void takeAShot(Scanner scanner) {

        String input = scanner.nextLine().toUpperCase(Locale.ROOT);
        int[] shotCoordinates = parseCoordinates(input);
        int r = shotCoordinates[0];
        int c = shotCoordinates[1];
        if (coordinatesAreValid(r, c)) {
            if (battleField[r][c].equals("O")) {
//                battleField[r][c] = "X";
                fogOfWar[r][c] = "X";
                this.hitsLeft--;
                printBattleField(getFogOfWar());
                if (fleet.isShipSunk(new int[] {r, c})) {
                    if (fleet.isSunk()) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                    } else {
                        System.out.println("You sank a ship! Specify a new target:");
                    }
                } else {
                    System.out.println("You hit a ship! Try again:");

                }
            } else if (battleField[r][c].equals("~")) {
                battleField[r][c] = "M";
                fogOfWar[r][c] = "M";
                printBattleField(getFogOfWar());
                System.out.println("You missed!");
                //TODO uncomment these line, after completion of the project,
                // to unlock new functionality
                //            } else {
//                System.out.println("You already shot at these coordinates!");
//                return false;
            }
        } else {
            System.out.println(("Error! You entered the wrong coordinates! Try again:"));
            takeAShot(scanner);
        }
    }

    public boolean isGameOver() {
        return this.hitsLeft <= 0;
    }

}
