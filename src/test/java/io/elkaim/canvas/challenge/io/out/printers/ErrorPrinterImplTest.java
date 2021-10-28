package io.elkaim.canvas.challenge.io.out.printers;

import io.elkaim.canvas.challenge.io.out.ErrorPrinter;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ErrorPrinterImplTest {

    private ErrorPrinter sut;
    private ByteArrayOutputStream outputStream;
    private PrintStream err;

    @BeforeEach
    void init(){
        this.outputStream= new ByteArrayOutputStream();
        this.sut = new ErrorPrinterImpl(new PrintStream(outputStream));
        this.err = System.err;


    }

    @Test
    void should_print_print_customized_error_message_and_stackTrace() {
        String helloWorld = "Hello world, there is a problem!";
        Exception exception = new Exception(helloWorld);

        OutputStream errOutputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errOutputStream));

        Assertions.assertTrue(errOutputStream.toString().isEmpty());
        Assertions.assertTrue(this.outputStream.toString().isEmpty());
        this.sut.print(exception);

        String head = "Unexpected error occurred: ";
        Assertions.assertEquals(head+helloWorld+"\n", this.outputStream.toString());
        Assertions.assertFalse(errOutputStream.toString().isEmpty());
    }

    @AfterEach
    void finalized(){
        System.setErr(this.err);
    }

}
