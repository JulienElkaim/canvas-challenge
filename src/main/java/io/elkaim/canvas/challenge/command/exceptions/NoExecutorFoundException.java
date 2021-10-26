package io.elkaim.canvas.challenge.command.exceptions;

import io.elkaim.canvas.challenge.app.exceptions.ErrorMessageException;

/**
 * Notify the user that the command is recognized but the application just does not handle (yet)
 * this command.
 * Technically, it means we miss a matching {@see CommandExecutor}.
 */
public class NoExecutorFoundException extends ErrorMessageException {
    public NoExecutorFoundException(String msg) {
        super(msg);
    }
}
