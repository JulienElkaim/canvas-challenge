package io.elkaim.canvas.challenge.canvas;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.Point;
import io.elkaim.canvas.challenge.canvas.model.PointTable;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.painter.CanvasPainter;

import java.util.Collection;
import java.util.Objects;

public class CanvasServiceImpl implements CanvasService {

    private Canvas canvas;

    public CanvasServiceImpl(){
    }

    @Override
    public Canvas getCanvas() {
        return Objects.requireNonNullElseGet(this.canvas, () -> {
            throw MalFormedCommandException.NO_CANVAS;
        });
    }

    @Override
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Canvas addElement(PointTable pointTable, boolean isInFront) {
        Collection<Point> eltPoints = pointTable.getPoints();
        if (isInFront) {
            eltPoints.forEach(point -> {
                this.canvas.addPoint(point.getX(), point.getY(), point.getValue());
            });
        } else {
            eltPoints.forEach(point -> {
                this.canvas.addPointIfAbsent(point.getX(), point.getY(), point.getValue());
            });
        }


        return null;
    }

    @Override
    public boolean canvasExist() {
        return Objects.nonNull(this.canvas);
    }

}
