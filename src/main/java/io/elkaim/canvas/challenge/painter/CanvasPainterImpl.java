package io.elkaim.canvas.challenge.painter;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.PointTable;

import java.util.Objects;

public class CanvasPainterImpl implements CanvasPainter {

    private static final String BOTTOM_BORDER_UNIT = "¯";
    private static final String TOP_BORDER_UNIT = "_";
    private static final String SIDE_BORDER_UNIT = "║";

    @Override
    public void draw(Canvas canvas) {
        Integer height = canvas.getHeight();
        Integer width = canvas.getWidth();

        this.printVerticalBorder(width, TOP_BORDER_UNIT);
        this.printCanvasBody(canvas,height,width);
        this.printVerticalBorder(width, BOTTOM_BORDER_UNIT);
    }

    private void printVerticalBorder(Integer width, String borderUnit) {
        String verticalBorder = new String(new char[width+2]).replace("\0", borderUnit);
        System.out.println(verticalBorder);
    }

    private void printCanvasBody(PointTable drawablePoints, Integer height, Integer width) {
        for(int y=1; y <= height; y++){
            System.out.print(SIDE_BORDER_UNIT);
            for(int x=1; x <= width; x++){
                System.out.print(Objects.requireNonNullElse(
                        drawablePoints.getPointValue(x,y),' '));
            }
            System.out.println(SIDE_BORDER_UNIT);
        }
    }
}
