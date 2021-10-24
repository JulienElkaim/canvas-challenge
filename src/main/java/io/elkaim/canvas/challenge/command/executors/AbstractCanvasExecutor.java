package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.command.CommandExecutor;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractCanvasExecutor implements CommandExecutor {
    protected final Character BASIC_POINT_FORMAT = 'x';
    protected final CanvasService canvasService;

    public AbstractCanvasExecutor(CanvasService canvasService){
        this.canvasService = canvasService;
    }

    public void noNegativeValue(int... values){
        IntStream intStream = Arrays.stream(values).filter(v -> v < 0);
        if(intStream.findAny().isPresent()){
            throw new MalFormedCommandException("Negative values not allowed here: " +
                    intStream.mapToObj(String::valueOf).collect(Collectors.joining(", ")));
        }
    }
}
