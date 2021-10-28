package io.elkaim.canvas.challenge.command.exceptions;

import io.elkaim.canvas.challenge.app.exceptions.ErrorMessageException;

/**
 * Notify the user that its command has a problem in conception.
 */
public class MalFormedCommandException extends ErrorMessageException {

    public MalFormedCommandException(String msg) {
        super(msg);
    }
}
