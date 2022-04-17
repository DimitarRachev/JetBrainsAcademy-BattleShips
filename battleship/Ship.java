package battleship;

import java.util.Set;

public class  Ship {
    Set<int[]> shipLegth;

    public Ship(Set<int[]> shipLegth) {
        this.shipLegth = shipLegth;
    }

    public boolean isSunk(int[] coordinates) {
        shipLegth.removeIf(ints -> ints[0] == coordinates[0] && ints[1] == coordinates[1]);
        return this.shipLegth.isEmpty();
    }
}
