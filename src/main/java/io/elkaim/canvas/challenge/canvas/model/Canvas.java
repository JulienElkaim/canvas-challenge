package io.elkaim.canvas.challenge.canvas.model;

import io.elkaim.canvas.challenge.canvas.exceptions.CanvasSizeLimitException;
import io.elkaim.canvas.challenge.utils.CoordinatesUtils;
import lombok.Getter;


/**
 * Canvas object to represent the canvas created by the user.
 * DrawTable with limited height and width.
 * Points outside the Canvas are just meant to be not represented but still exist.
 * It is inspired by the behavior of svg-like tools (i.e, InkScape)
 */
@Getter
public class Canvas extends DrawTable {
    public static final int MAX_WIDTH = 80;
    public static final int MAX_HEIGHT = 20;
    private Integer width;
    private Integer height;

    public Canvas(Integer width, Integer height) {
        super();
        this.setWidth(width);
        this.setHeight(height);
    }

    public void setWidth(Integer width) {
        if (width > MAX_WIDTH) {
            throw CanvasSizeLimitException.width(width);
        }
        if (width <= 0) {
            throw CanvasSizeLimitException.LOWER_LIMIT_EXCEPTION;
        }
        this.width = width;
    }

    public void setHeight(Integer height) {
        if (height > MAX_HEIGHT) {
            throw CanvasSizeLimitException.height(height);
        }
        if (height <= 0) {
            throw CanvasSizeLimitException.LOWER_LIMIT_EXCEPTION;
        }
        this.height = height;
    }

    public void addPointIfAbsent(Point point) {
        if (!this.points.contains(point.getX(), point.getY())) {
            this.addPoint(point);
        }
    }


}
