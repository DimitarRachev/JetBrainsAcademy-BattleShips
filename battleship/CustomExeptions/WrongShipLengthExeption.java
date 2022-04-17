package battleship.CustomExeptions;

public class WrongShipLengthExeption extends Exception{
    public WrongShipLengthExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongShipLengthExeption(String message) {
        super(message);
    }
}
