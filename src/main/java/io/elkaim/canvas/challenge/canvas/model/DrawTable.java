package io.elkaim.canvas.challenge.canvas.model;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Represent any matrix of points.
 * Only points with a value are represented.
 */
public class DrawTable {
    protected Table<Integer, Integer, Character> points;

    public DrawTable() {
        this.points = TreeBasedTable.create();
    }

    public Character getPointValue(Integer x, Integer y) {
        return points.get(x, y);
    }

    public void addPoint(Point point) {
        this.points.put(point.getX(), point.getY(), point.getValue());
    }

    public Collection<Point> getPoints() {
        return this.points.cellSet().stream()
                .map(PointFactory::build)
                .collect(Collectors.toList());
    }

}
