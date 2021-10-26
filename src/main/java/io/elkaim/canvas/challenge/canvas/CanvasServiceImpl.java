package io.elkaim.canvas.challenge.canvas;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.DrawTable;
import io.elkaim.canvas.challenge.canvas.model.Point;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;

import java.util.Collection;
import java.util.Objects;

public class CanvasServiceImpl implements CanvasService {

    private Canvas canvas;

    public CanvasServiceImpl() {
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
    public void applyMask(DrawTable drawTable, boolean hasPriority) {
        Collection<Point> eltPoints = drawTable.getPoints();
        if (hasPriority) {
            eltPoints.forEach(point -> this.canvas.addPoint(point));
        } else {
            eltPoints.forEach(point -> this.canvas.addPointIfAbsent(point));
        }


    }

    @Override
    public boolean canvasNotYetCreated() {
        return !Objects.nonNull(this.canvas);
    }

}
