package io.elkaim.canvas.challenge.command.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Command {
    private final CommandType type;
    private final String body;
}
