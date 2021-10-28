package io.elkaim.canvas.challenge.canvas.exceptions;

import io.elkaim.canvas.challenge.app.exceptions.ErrorMessageException;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;

/**
 * Errors about Canvas' size limits.
 * Provide default instances as there is not so many scenarios.
 */
public class NoCanvasExistsException extends ErrorMessageException {

    public NoCanvasExistsException() {
        super("Please create a canvas before drawing anything.");
    }

}
