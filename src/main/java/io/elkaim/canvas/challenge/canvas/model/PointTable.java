package io.elkaim.canvas.challenge.canvas.model;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class PointTable {
    protected Table<Integer, Integer, Character> points;

    public PointTable() {
        this.points = TreeBasedTable.create();
    }

    public Character getPointValue(Integer x, Integer y) {
        return points.get(x, y);
    }

    public void addPoint(Integer x, Integer y, Character value) {
        this.points.put(x, y, value);
    }

    public Collection<Point> getPoints(){
        return this.points.cellSet().stream()
                .map(PointFactory::build)
                .collect(Collectors.toList());
    }

}
