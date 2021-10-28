package io.elkaim.canvas.challenge.command.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CommandTypeTest {

    @Test
    void should_of_return_relevant_type_when_known_abbreviate() {
        Optional<CommandType> type1 = CommandType.of("L");
        Assertions.assertTrue(type1.isPresent());
        Assertions.assertSame(CommandType.LINE, type1.get());
        Optional<CommandType> type2 = CommandType.of("R");
        Assertions.assertTrue(type2.isPresent());
        Assertions.assertSame(CommandType.RECTANGLE, type2.get());
        Optional<CommandType> type3 = CommandType.of("HELP");
        Assertions.assertTrue(type3.isPresent());
        Assertions.assertSame(CommandType.HELP, type3.get());
    }

    @Test
    void should_of_return_empty_optional_when_unknown_abbreviate() {
        Optional<CommandType> unknown = CommandType.of("UNKNOWN_ABRV");
        Assertions.assertTrue(unknown.isEmpty());
    }
}
