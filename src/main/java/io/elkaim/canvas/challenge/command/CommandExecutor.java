package io.elkaim.canvas.challenge.command;

import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;

public interface CommandExecutor extends CommandService {
    CommandType getCommandType();
}

