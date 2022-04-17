package battleship.CustomExeptions;

public class ShipPlacedTooCloseExeption extends Exception{
    public ShipPlacedTooCloseExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ShipPlacedTooCloseExeption(String message) {
        super(message);
    }

}
