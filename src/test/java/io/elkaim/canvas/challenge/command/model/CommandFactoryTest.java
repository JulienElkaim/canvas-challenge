package io.elkaim.canvas.challenge.command.model;

import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    @Test
    void should_build_throw_if_unrecognized_command() {
        Assertions.assertThrows(MalFormedCommandException.class, ()-> CommandFactory.build("NA"));
        Assertions.assertThrows(MalFormedCommandException.class, ()-> CommandFactory.build("NA 4 5 r"));
    }

    @Test
    void should_build_throw_if_empty_cmd() {
        Assertions.assertThrows(MalFormedCommandException.class, ()-> CommandFactory.build(""));
    }

    @Test
    void should_build_assign_good_CommandType() {
        Command cmd = CommandFactory.build("L 4 2 4 3");
        Assertions.assertSame(cmd.getType(), CommandType.LINE);

        Command cmd2 = CommandFactory.build("R 4 2 4 3");
        Assertions.assertSame(cmd2.getType(), CommandType.RECTANGLE);

        Command cmd3 = CommandFactory.build("HELP");
        Assertions.assertSame(cmd3.getType(), CommandType.HELP);
    }

    @Test
    void should_build_accept_user_cmd_with_no_body() {
        Command cmd = Assertions.assertDoesNotThrow(()->CommandFactory.build("Q"));
        Assertions.assertNotNull(cmd.getBody());
        Assertions.assertTrue(cmd.getBody().isEmpty());

        Command cmd2 = Assertions.assertDoesNotThrow(()->CommandFactory.build("HELP"));
        Assertions.assertNotNull(cmd2.getBody());
        Assertions.assertTrue(cmd2.getBody().isEmpty());
    }

    @Test
    void should_build_segregate_body_and_remove_command_header() {
        String userInput = "L 4 3 4 10 r --low";
        Command cmd = Assertions.assertDoesNotThrow(()->CommandFactory.build(userInput));
        Assertions.assertNotNull(cmd.getBody());
        Assertions.assertEquals( userInput.substring(2) ,cmd.getBody());
    }

    @Test
    void should_build_trim_body() {
        String userInput = "L   4 3 4 10 r --low   ";
        Command cmd = Assertions.assertDoesNotThrow(()->CommandFactory.build(userInput));
        Assertions.assertNotNull(cmd.getBody());
        Assertions.assertNotEquals( ' ',cmd.getBody().charAt(0));
        Assertions.assertNotEquals( ' ',cmd.getBody().charAt(cmd.getBody().length()-1));
    }


}
