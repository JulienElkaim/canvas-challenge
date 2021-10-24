package io.elkaim.canvas.challenge.command;

import io.elkaim.canvas.challenge.command.model.Command;

public interface CommandService {
    void exec(Command command);
}
