package io.elkaim.canvas.challenge.command.exceptions;

import io.elkaim.canvas.challenge.app.exceptions.ApplicationSignalException;

/**
 * Signal to trigger application exit.
 */
public class QuitApplicationSignalException extends ApplicationSignalException {

    public QuitApplicationSignalException() {
        super();
    }

}
