package io.elkaim.canvas.challenge.app;

import com.google.common.collect.Maps;
import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.CanvasServiceImpl;
import io.elkaim.canvas.challenge.command.CommandService;
import io.elkaim.canvas.challenge.command.CommandServiceImpl;
import io.elkaim.canvas.challenge.command.executors.*;
import io.elkaim.canvas.challenge.io.in.InputService;
import io.elkaim.canvas.challenge.io.in.InputServiceImpl;
import io.elkaim.canvas.challenge.io.out.*;
import io.elkaim.canvas.challenge.io.out.printers.CanvasPrinterImpl;
import io.elkaim.canvas.challenge.io.out.printers.ErrorPrinterImpl;
import io.elkaim.canvas.challenge.io.out.printers.MessagePrinterImpl;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

/**
 * Application context
 * Should be responsible to provide Application with components, states, resources.
 */
public class ApplicationContext {

    private final Map<Class<?>, Object> beans = Maps.newConcurrentMap();

    public ApplicationContext() {
        this.initializeBeans();
    }

    /**
     * @param clz the Bean's class we want an instance of.
     * @param <T> the type of bean.
     * @return the bean instance stored. can be null if no instance created for the requested {@param clz}.
     */
    public <T> T getBean(Class<T> clz) {
        return clz.cast(this.beans.get(clz));
    }

    /**
     * Simulate DI performed with reflection by frameworks such as Spring.
     * Initialize useful components for application.
     */
    private void initializeBeans() {
        PrintStream printStream = System.out;
        this.beans.put(PrintStream.class, printStream);

        CanvasPrinter canvasPrinter = new CanvasPrinterImpl(printStream);
        MessagePrinter messagePrinter = new MessagePrinterImpl(printStream);
        ErrorPrinter errorPrinter = new ErrorPrinterImpl(printStream);
        OutputService outputService = new OutputServiceImpl(messagePrinter, canvasPrinter, errorPrinter);
        this.beans.put(OutputService.class, outputService);

        InputService inputService = new InputServiceImpl(System.in, printStream);
        this.beans.put(InputService.class, inputService);


        CanvasService canvasService = new CanvasServiceImpl();
        this.beans.put(CanvasService.class, canvasService);


        CommandService commandService = new CommandServiceImpl(List.of(
                new FillCommandExecutor(messagePrinter, canvasService),
                new RectangleCommandExecutor(messagePrinter, canvasService),
                new CanvasCommandExecutor(messagePrinter, canvasService),
                new LineCommandExecutor(messagePrinter, canvasService),
                new PointCommandExecutor(messagePrinter, canvasService),
                new QuitApplicationExecutor(messagePrinter),
                new HelpCommandExecutor(messagePrinter)
        ));
        this.beans.put(CommandService.class, commandService);
    }
}
