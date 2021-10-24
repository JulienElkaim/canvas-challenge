package io.elkaim.canvas.challenge.canvas.model;

import com.google.common.collect.Table;

public class PointFactory {

    public static Point build(Table.Cell<Integer, Integer, Character> tCell){
        return Point.builder()
                .x(tCell.getRowKey())
                .y(tCell.getColumnKey())
                .value(tCell.getValue())
                .build();
    }
}
