package io.elkaim.canvas.challenge.command.exceptions;

import io.elkaim.canvas.challenge.ErrorMessageException;

public class NoExecutorFoundException extends ErrorMessageException {
    public NoExecutorFoundException(String msg){ super(msg);}
}
