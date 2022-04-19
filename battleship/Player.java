package battleship;


public class Player {
    BattleField battleField;

    public Player() {
        this.battleField = new BattleField();
    }

    public BattleField getBattleField() {
        return battleField;
    }

    void printBattleField(String[][] battleField) {
        this.battleField.printBattleField(battleField);
    }
}
