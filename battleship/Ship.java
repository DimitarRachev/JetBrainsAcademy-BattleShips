package battleship;

import java.util.Set;

public class  Ship {
    Set<int[]> shipLength;

    public Ship(Set<int[]> shipLength) {
        this.shipLength = shipLength;
    }

    public boolean isSunk(int[] coordinates) {
        shipLength.removeIf(ints -> ints[0] == coordinates[0] && ints[1] == coordinates[1]);
        return this.shipLength.isEmpty();
    }
}
