package io.elkaim.canvas.challenge.app.exceptions;

import io.elkaim.canvas.challenge.command.executors.abstracts.BasicCommandExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ErrorMessageExceptionTest {

    private static final String EXPECTED_HEAD_MESSAGE = "Error occurred: ";

    @Test
    void should_getMessage_prepend_introduction_to_err_message() {
        String helloWorld = "Hello World, there is a problem here!";
        ErrorMessageException sut = Mockito.mock(ErrorMessageException.class, Mockito.withSettings()
                .useConstructor(helloWorld)
                .defaultAnswer(Mockito.CALLS_REAL_METHODS));

        Assertions.assertEquals(EXPECTED_HEAD_MESSAGE + helloWorld, sut.getMessage(),
                "getMessage() should prepend a header to introduce the message.");

    }
}
