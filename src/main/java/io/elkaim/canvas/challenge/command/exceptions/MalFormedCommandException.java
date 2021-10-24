package io.elkaim.canvas.challenge.command.exceptions;

import io.elkaim.canvas.challenge.ErrorMessageException;

public class MalFormedCommandException extends ErrorMessageException {

    public static final MalFormedCommandException NO_CANVAS = new MalFormedCommandException("Please create a canvas before drawing anything.");
    public MalFormedCommandException(String msg){ super(msg);}
}
