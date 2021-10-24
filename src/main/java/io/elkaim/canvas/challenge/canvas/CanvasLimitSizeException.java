package io.elkaim.canvas.challenge.canvas;

import io.elkaim.canvas.challenge.canvas.model.Canvas;

public class CanvasLimitSizeException extends RuntimeException {

    public static CanvasLimitSizeException LOWER_LIMIT_EXCEPTION = new CanvasLimitSizeException("Canvas' width & height have to be strictly higher than 0.");

    public CanvasLimitSizeException(String msg){ super(msg);}

    public static CanvasLimitSizeException height(Integer height){
        return new CanvasLimitSizeException(String
                .format(" You try to set height %s, but canvas limit height is %s ",
                        height, Canvas.MAX_HEIGHT));
    }

    public static CanvasLimitSizeException width(Integer width){
        return new CanvasLimitSizeException(String
                .format(" You try to set width %s, but canvas limit width is %s ",
                        width, Canvas.MAX_WIDTH));
    }
}
