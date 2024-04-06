package eu.fondy.task.exception;

public class InsufficientChangeException extends RuntimeException {

    public InsufficientChangeException(String message) {
        super(message);
    }
}
