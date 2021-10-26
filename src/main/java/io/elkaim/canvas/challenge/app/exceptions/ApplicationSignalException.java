package io.elkaim.canvas.challenge.app.exceptions;

/**
 * Exception handled by application not as an error, but as a signal to perform
 * some "Application level" operations (i.e, Quit the application).
 */
public abstract class ApplicationSignalException extends RuntimeException {
    public ApplicationSignalException() {
    }
}
