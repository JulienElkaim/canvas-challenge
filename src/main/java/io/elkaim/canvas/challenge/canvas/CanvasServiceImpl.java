package io.elkaim.canvas.challenge.canvas;

import io.elkaim.canvas.challenge.canvas.exceptions.CanvasSizeLimitException;
import io.elkaim.canvas.challenge.canvas.exceptions.NoCanvasExistsException;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.DrawTable;
import io.elkaim.canvas.challenge.canvas.model.Point;

import java.util.Collection;
import java.util.Objects;

public class CanvasServiceImpl implements CanvasService {

    private Canvas canvas;

    public CanvasServiceImpl() {
    }

    @Override
    public Canvas getCanvas() {
        return Objects.requireNonNullElseGet(this.canvas, () -> {
            throw new NoCanvasExistsException();
        });
    }

    @Override
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void applyMask(DrawTable mask, boolean hasPriority) {
        Collection<Point> eltPoints = mask.getPoints();
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

    @Override
    public void updateCanvasSize(int w, int h) {
        this.getCanvas().setWidth(w);
        this.getCanvas().setHeight(h);
    }

}
