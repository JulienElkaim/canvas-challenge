package io.elkaim.canvas.challenge.command;

import io.elkaim.canvas.challenge.command.model.CommandType;

public interface CommandExecutor extends CommandService {

    /**
     * Every CommandExecutor declares on which CommandType it is able to work on.
     *
     * @return the command type handled by this executor.
     */
    CommandType getCommandType();
}

