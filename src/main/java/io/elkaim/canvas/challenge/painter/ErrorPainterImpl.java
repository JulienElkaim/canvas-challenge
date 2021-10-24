package io.elkaim.canvas.challenge.painter;

public class ErrorPainterImpl implements ErrorPainter {
    @Override
    public void draw(Exception err) {
        System.out.println("Error occurred: " +err.getMessage());

    }
}
