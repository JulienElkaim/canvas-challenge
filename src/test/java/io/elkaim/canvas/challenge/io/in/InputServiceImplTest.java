package io.elkaim.canvas.challenge.io.in;

import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import io.elkaim.canvas.challenge.io.out.printers.MessagePrinterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class InputServiceImplTest {

    private InputService sut;
    private ByteArrayOutputStream outputStream;
    private InputStream inputStream;
    private String userReply;
    @BeforeEach
    void init(){
        this.userReply = "Ok, I do that!";
        this.inputStream = new ByteArrayInputStream(userReply.getBytes());
        this.outputStream= new ByteArrayOutputStream();
        this.sut = new InputServiceImpl(inputStream,new PrintStream(outputStream));
    }

    @Test
    void should_requestInputLine_return_exact_reply_of_user() {
        String requestPunchline = "Please do this";
        String resp = assertTimeout(Duration.of(20, ChronoUnit.SECONDS), () -> this.sut.requestInputLine(requestPunchline));
        Assertions.assertEquals(userReply, resp);
    }

    @Test
    void should_requestInputLine_customize_request_punchline() {
        String requestPunchline = "Please do this";
        assertTimeout(Duration.of(2, ChronoUnit.SECONDS), () -> this.sut.requestInputLine(requestPunchline));

        String head = "> ";
        String tail = ": ";
        Assertions.assertEquals(head + requestPunchline + tail, this.outputStream.toString());

    }
}
