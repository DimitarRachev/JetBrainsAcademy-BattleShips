package battleship.CustomExeptions;

public class WhrongShipLocationExeption extends Exception{
    public WhrongShipLocationExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public WhrongShipLocationExeption(String message) {
        super(message);
    }
}
