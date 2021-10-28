package io.elkaim.canvas.challenge.command.executors.abstracts;

import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BasicCommandExecutorTest {

    private BasicCommandExecutor sut;
    private Map<String, String> args;
    private Command cmdWellFormed;
    private Command cmdMalFormed;
    private Command cmdNoOptionalArg;

    @BeforeEach
    public void init() {
        this.sut = Mockito.mock(BasicCommandExecutor.class, Mockito.CALLS_REAL_METHODS);
        this.args = Map.of("argName", "argValue");
        this.cmdWellFormed = new Command(CommandType.FILL, "30 4 c");
        this.cmdMalFormed = new Command(CommandType.RECTANGLE, "10 4 p w d");
        this.cmdNoOptionalArg = new Command(CommandType.RECTANGLE, "10 4 30 6");
    }

    @Test
    void should_getArg_return_value_existing_arg() {
        String argValue = Assertions.assertDoesNotThrow(() -> this.sut.getArg(this.args, "argName"));
        Assertions.assertNotNull(argValue);
        Assertions.assertEquals("argValue", argValue);
    }

    @Test
    void should_getArg_throw_if_arg_not_exist_but_is_required() {
        Assertions.assertThrows(MalFormedCommandException.class, () -> this.sut
                .getArg(this.args, "notExistingArgName", true));
    }

    @Test
    void should_getArg_return_null_if_arg_not_exist_and_not_is_not_required() {
        String result = assertDoesNotThrow(() -> this.sut
                .getArg(this.args, "notExistingArgName", false));
        Assertions.assertNull(result, " Non-existing-non-required arg should result as null value");
    }


    @Test
    void should_parseCommandBody_throw_if_body_malFormatted() {
        Assertions.assertThrows(MalFormedCommandException.class, () ->
                this.sut.parseCommandBody(this.cmdMalFormed.getType().getBodyFormat(), this.cmdMalFormed.getBody()));
    }

    @Test
    void should_parseCommandBody_return_args_map_if_body_well_formatted() {
        Map<String, String> args = assertDoesNotThrow(() ->
                this.sut.parseCommandBody(this.cmdWellFormed.getType().getBodyFormat(), this.cmdWellFormed.getBody()));
        Assertions.assertNotNull(args);
        String argX = args.get("x");
        Assertions.assertNotNull(argX);
        Assertions.assertEquals("30", argX, "Arg parsed does not reflect body argument provided.");
        String argColor = args.get("color");
        Assertions.assertNotNull(argColor);
        Assertions.assertEquals("c", argColor, "Arg parsed does not reflect body argument provided.");
    }

    @Test
    void should_parseCommandBody_tolerate_non_mandatory_arg_missing() {
        Map<String, String> args = assertDoesNotThrow(() ->
                this.sut.parseCommandBody(this.cmdNoOptionalArg.getType().getBodyFormat(), this.cmdNoOptionalArg.getBody()));
        String argColor = args.get("color");
        Assertions.assertNull(argColor, "Optional argument missing, so null value instead.");
        String argX1 = args.get("x1");
        Assertions.assertNotNull(argX1, "mandatory arguments still visible in args map.");
    }
}
