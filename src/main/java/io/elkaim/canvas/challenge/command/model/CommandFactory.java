package io.elkaim.canvas.challenge.command.model;

import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;

import java.util.Optional;

public class CommandFactory {

    public static Command build(String userCommand) throws MalFormedCommandException {
        String[] cmd = userCommand.split(" ", 2);
        if(cmd.length!= 2){
            throw new MalFormedCommandException(String
                    .format("Command %s missing a body", userCommand));
        }else{
            Optional<CommandType> optionalCommandType = CommandType.of(cmd[0]);
            if(optionalCommandType.isEmpty()){
                throw new MalFormedCommandException(String
                        .format("Command %s not recognized", cmd[0]));
            }else{
                CommandType commandType = optionalCommandType.get();
                return new Command(commandType, cmd[1]);
            }
        }
    }
}
