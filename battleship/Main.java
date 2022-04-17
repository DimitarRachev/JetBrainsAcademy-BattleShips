package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BattleField battleField = new BattleField();

        battleField.printBattleField(battleField.getBattleField());

        placeShips(scanner, battleField);
        System.out.println("The game starts!");
        battleField.printBattleField(battleField.getFogOfWar());
        System.out.println("Take a shot!");
       while (!battleField.isGameOver()) {
          battleField.takeAShot(scanner);
       }
//        System.out.println("You sank the last ship. You won. Congratulations!");
//       battleField.printBattleField(battleField.getBattleField());

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

        private static String getShip ( int i, boolean isSubmarinePlaces){
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
    }

