package battleship;

import battleship.CustomExeptions.GameOverException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        game.inputShips(scanner);
;

        while (true) {
            try {
                game.turn(scanner);
            } catch (GameOverException e) {
                System.out.println(e.getMessage());
                return;
            }
        }






    }


}

