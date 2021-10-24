package io.elkaim.canvas.challenge;

public abstract class ErrorMessageException extends RuntimeException{
    public ErrorMessageException(String msg){ super(msg);}

    @Override
    public String getMessage(){
        return String.format("Error occured: %s", super.getMessage());
    }
}
