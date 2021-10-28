package io.elkaim.canvas.challenge.command;

import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;

public interface CommandExecutor {

    /**
     * Every CommandExecutor declares on which CommandType it is able to work on.
     *
     * @return the command type handled by this executor.
     */
    CommandType getCommandType();

    /**
     * Execute a command from the user.
     *
     * @param cmd to process and translate into action.
     */
    void execute(Command cmd);

}

