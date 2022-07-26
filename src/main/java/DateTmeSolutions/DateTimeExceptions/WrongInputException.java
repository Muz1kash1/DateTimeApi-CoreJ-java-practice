package DateTmeSolutions.DateTimeExceptions;

public class WrongInputException extends RuntimeException {
    public WrongInputException(String message){
        super(message);
    }
}
