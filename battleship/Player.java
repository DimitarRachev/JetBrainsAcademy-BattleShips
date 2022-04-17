package battleship;



public class Player {
    BattleField battleField;

    public Player() {
        this.battleField = new BattleField();
    }

    public BattleField getBattleField() {
        return battleField;
    }

    boolean isGameOver(){
        return battleField.isGameOver();
    }

    void printBattleField(String[][] battleField) {
        this.battleField.printBattleField(battleField);
    }

//    public void takeAShot(int[] shotCoordinates) throws GameOverException {
//        int r = shotCoordinates[0];
//        int c = shotCoordinates[1];
//        if (coordinatesAreValid(r, c)) {
//            if (battleField.getBattleField()[r][c].equals("O")) {
//                //TODO uncomment these line, after completion of the project,
//                // to unlock new functionality
////                battleField[r][c] = "X";
//                battleField.getFogOfWar()[r][c] = "X";
////                this.hitsLeft--;
////                printBattleField(battleField.getFogOfWar());
//                if (battleField.fleet.isShipSunk(new int[]{r, c})) {
//                    if (battleField.getFleet().isSunk()) {
//                        System.out.println("You sank the last ship. You won. Congratulations!");
//                        throw new GameOverException("you sank the last ship. You won. Congratulations!");
//                    } else {
//                        System.out.println("You sank a ship!");
//                    }
//                } else {
//                    System.out.println("You hit a ship!");
//
//                }
//            } else if (battleField.getBattleField()[r][c].equals("~")) {
//                //TODO uncomment these line, after completion of the project,
//                // to unlock new functionality
////                battleField[r][c] = "M";
//                battleField.getFogOfWar()[r][c] = "M";
////                printBattleField(battleField.getFogOfWar());
//                System.out.println("You missed!");
//                //TODO uncomment these line, after completion of the project,
//                // to unlock new functionality
//
////                } else {
////                System.out.println("You already shot at these coordinates!");
////                return false;
//            }
////        } else {
////            System.out.println(("Error! You entered the wrong coordinates! Try again:"));
////            takeAShot(scanner);
//        }
//    }

    private int[] parseCoordinates(String[] carrier) {
        int startRow = carrier[0].charAt(0) - 64;
        int startCol = Integer.parseInt(carrier[0].substring(1));
        int endRow = carrier[1].charAt(0) - 64;
        int endCol = Integer.parseInt(carrier[1].substring(1));
        return new int[]{startRow, startCol, endRow, endCol};
    }



//    private boolean coordinatesAreValid(int r, int c) {
//        return r >= 1 && r < battleField.getBattleField().length && c >= 1 && c < battleField.getBattleField()[0].length;
//    }
}
