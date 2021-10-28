package io.elkaim.canvas.challenge.io.out.printers;

import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MessagePrinterImplTest {

    private MessagePrinter sut;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void init() {
        this.outputStream = new ByteArrayOutputStream();
        this.sut = new MessagePrinterImpl(new PrintStream(outputStream));
    }

    @Test
    void should_print_print_exact_message_provided_on_its_print_stream() {
        String helloWorld = "Hellow world !";
        Assertions.assertTrue(this.outputStream.toString().isEmpty());
        this.sut.print(helloWorld);
        Assertions.assertEquals(helloWorld + "\n", this.outputStream.toString());
    }

}
