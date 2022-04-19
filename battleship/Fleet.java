package battleship;

import java.util.HashSet;
import java.util.Set;

public class Fleet {
    Set<Ship> ships;

    public Fleet() {
        this.ships = new HashSet<>();
    }

    void addShip(Ship ship) {
        ships.add(ship);
    }

    boolean isShipSunk(int[] coordinates) {
        for (Ship ship : ships) {
            if (ship.isSunk(coordinates)) {
                ships.remove(ship);
                return true;
            }
        }
        return false;
    }

    boolean isSunk() {
        return ships.isEmpty();
    }
}
