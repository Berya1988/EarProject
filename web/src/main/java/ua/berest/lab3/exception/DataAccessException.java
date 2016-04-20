package ua.berest.lab3.exception;

/**
 * Created by Oleg on 15.02.2016.
 */
public class DataAccessException extends Exception {
    private String message;

    public DataAccessException(String message, Exception e){
        super(message,e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + message;
    }
}