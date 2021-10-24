package io.elkaim.canvas.challenge.painter;

public class MessagePainterImpl implements MessagePainter {
    @Override
    public void draw(Exception err) {
        err.printStackTrace();
        System.out.println("Unexpected error occurred: " +err.getMessage());
        System.out.flush();
    }

    @Override
    public void draw(String msg) {
        System.out.println(msg);
    }
}
