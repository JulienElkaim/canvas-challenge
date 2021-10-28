package io.elkaim.canvas.challenge.io.in;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertTimeout;

class InputServiceImplTest {

    private InputService sut;
    private ByteArrayOutputStream outputStream;
    private InputStream inputStream;
    private String userReply;

    @BeforeEach
    void init() {
        this.userReply = "Ok, I do that!";
        this.inputStream = new ByteArrayInputStream(userReply.getBytes());
        this.outputStream = new ByteArrayOutputStream();
        this.sut = new InputServiceImpl(inputStream, new PrintStream(outputStream));
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
