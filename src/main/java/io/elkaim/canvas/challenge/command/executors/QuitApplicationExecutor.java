package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.command.CommandExecutor;
import io.elkaim.canvas.challenge.command.exceptions.QuitApplicationSignalException;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;

public class QuitApplicationExecutor implements CommandExecutor {
    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_RECTANGLE;
    }

    @Override
    public void execute(Command cmd) {
        throw new QuitApplicationSignalException();

    }
}
