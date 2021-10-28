package io.elkaim.canvas.challenge.integration;

import io.elkaim.canvas.challenge.app.ApplicationContext;
import io.elkaim.canvas.challenge.app.CanvasApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public abstract class ApplicationLevelIntegration {
    protected ByteArrayOutputStream outputStream;

    @BeforeEach
    void setup() {
        this.outputStream = new ByteArrayOutputStream();

    }

    protected void runFullAppScenario(InputStream in, OutputStream out){
        this.runFullAppScenario( in, out, 10);

    }
    protected void runFullAppScenario(InputStream in, OutputStream out, int timeOutSeconds){
        PrintStream printStream = new PrintStream(out);
        ApplicationContext applicationContext = new ApplicationContext(printStream, in);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(new CanvasApplication(applicationContext));
        Assertions.assertDoesNotThrow(()-> future.get(timeOutSeconds, TimeUnit.SECONDS),
                String.format("Current state of outputStream is: \n%s", out));

    }
}
