package io.elkaim.canvas.challenge.painter;

import io.elkaim.canvas.challenge.ErrorMessageException;
import io.elkaim.canvas.challenge.canvas.model.Canvas;

public interface MessagePainter {

    void draw(Exception e);
    void draw(String msg);

}
