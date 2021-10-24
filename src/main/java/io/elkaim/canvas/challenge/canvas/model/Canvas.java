package io.elkaim.canvas.challenge.canvas.model;

import io.elkaim.canvas.challenge.canvas.CanvasLimitSizeException;
import lombok.Getter;


@Getter
public class Canvas extends PointTable {
    public static final int MAX_WIDTH = 80;
    public static final int MAX_HEIGHT = 20;
    private Integer width;
    private Integer height;

    public Canvas(Integer width, Integer height) {
        super();
        this.setWidth(width);
        this.setHeight(height);
    }

    public void setWidth(Integer width){
        if(width > MAX_WIDTH){
            throw CanvasLimitSizeException.width(width);
        }
        if(width <= 0){
            throw CanvasLimitSizeException.LOWER_LIMIT_EXCEPTION;
        }
        this.width = width;
    }

    public void setHeight(Integer height){
        if(height > MAX_HEIGHT){
            throw CanvasLimitSizeException.height(height);
        }
        if(height <= 0){
            throw CanvasLimitSizeException.LOWER_LIMIT_EXCEPTION;
        }
        this.height = height;
    }

    public void addPointIfAbsent(Integer x, Integer y, Character value) {
        if (!this.points.contains(x, y)) {
            this.addPoint(x, y, value);
        }
    }


}
