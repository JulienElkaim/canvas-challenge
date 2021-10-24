package io.elkaim.canvas.challenge.command.exceptions;

public class MalFormedCommandException extends RuntimeException {

    public static final MalFormedCommandException NO_CANVAS = new MalFormedCommandException("Please create a canvas before drawing anything.");
    public MalFormedCommandException(String msg){ super(msg);}
}
