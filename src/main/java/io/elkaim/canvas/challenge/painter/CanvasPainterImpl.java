package io.elkaim.canvas.challenge.painter;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.PointTable;

import java.util.Objects;

public class CanvasPainterImpl implements CanvasPainter {

    private static final String VERTICAL_BORDER_UNIT = "-";

    @Override
    public void draw(Canvas canvas) {
        Integer height = canvas.getHeight();
        Integer width = canvas.getWidth();

        this.printVerticalBorder(width);
        this.printCanvasBody(canvas,height,width);
        this.printVerticalBorder(width);
    }

    private void printVerticalBorder(Integer width) {
        String verticalBorder = new String(new char[width+2]).replace("\0", VERTICAL_BORDER_UNIT);
        System.out.println(verticalBorder);
    }

    private void printCanvasBody(PointTable drawablePoints, Integer height, Integer width) {
        for(int x=0; x < height; x++){
            System.out.print("|");
            for(int y=0; y < width; y++){
                System.out.print(Objects.requireNonNullElse(
                        drawablePoints.getPointValue(x,y),' '));
            }
            System.out.println("|");
        }
    }
}
