package io.elkaim.canvas.challenge.command.model;

import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;

import java.util.Optional;

public class CommandFactory {

    public static Command build(String userCommand) throws MalFormedCommandException {
        if (userCommand.isEmpty()) {
            throw new MalFormedCommandException("Command empty. Type HELP to see all commands.");
        }

        String[] cmd = userCommand.split(" ", 2);

        Optional<CommandType> optionalCommandType = CommandType.of(cmd[0]);
        if (optionalCommandType.isEmpty()) {
            throw new MalFormedCommandException(String
                    .format("Command %s not recognized", cmd[0]));
        } else {
            CommandType commandType = optionalCommandType.get();
            return new Command(commandType, cmd.length == 2 ? cmd[1].trim() : "");
        }

    }
}
