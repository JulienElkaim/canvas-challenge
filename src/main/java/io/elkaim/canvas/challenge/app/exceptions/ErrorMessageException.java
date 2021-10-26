package io.elkaim.canvas.challenge.app.exceptions;

/**
 * Exceptions for expected errors about user's inputs.
 * Will be displayed differently than the other exceptions, as it is
 * "part of the application's features" to provide these "help message"
 */
public abstract class ErrorMessageException extends RuntimeException {
    public ErrorMessageException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return String.format("Error occurred: %s", super.getMessage());
    }
}
