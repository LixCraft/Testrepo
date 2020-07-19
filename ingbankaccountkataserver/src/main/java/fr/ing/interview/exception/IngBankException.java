package fr.ing.interview.exception;

public class IngBankException extends RuntimeException {
    public IngBankException(String message) {
        super(message);
    }

    public IngBankException(String message, Throwable cause) {
        super(message, cause);
    }
}
