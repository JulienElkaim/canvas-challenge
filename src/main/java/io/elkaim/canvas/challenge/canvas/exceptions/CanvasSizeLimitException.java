package io.elkaim.canvas.challenge.canvas.exceptions;

import io.elkaim.canvas.challenge.app.exceptions.ErrorMessageException;
import io.elkaim.canvas.challenge.canvas.model.Canvas;

/**
 * Errors about Canvas' size limits.
 * Provide default instances as there is not so many scenarios.
 */
public class CanvasSizeLimitException extends ErrorMessageException {

    public static CanvasSizeLimitException LOWER_LIMIT_EXCEPTION = new CanvasSizeLimitException("Canvas' width & height have to be strictly higher than 0.");

    public CanvasSizeLimitException(String msg) {
        super(msg);
    }

    /**
     * Default Height limit error message.
     *
     * @param height requested by the user.
     * @return standard Height error message.
     */
    public static CanvasSizeLimitException height(Integer height) {
        return new CanvasSizeLimitException(String
                .format("You try to set height %s, but canvas limit height is %s ",
                        height, Canvas.MAX_HEIGHT));
    }

    /**
     * Default Width limit error message.
     *
     * @param width requested by the user.
     * @return standard Width error message.
     */
    public static CanvasSizeLimitException width(Integer width) {
        return new CanvasSizeLimitException(String
                .format("You try to set width %s, but canvas limit width is %s ",
                        width, Canvas.MAX_WIDTH));
    }
}
