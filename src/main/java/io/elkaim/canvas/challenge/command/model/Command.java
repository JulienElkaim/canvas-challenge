package io.elkaim.canvas.challenge.command.model;

/**
 * Command is a representation of user's input.
 * It has a Type (determined by its leading component, i.e 'P' in 'P x y c',
 * and a body, i.e 'x y c' in 'P x y c'
 */
public record Command(CommandType type, String body) {

    public CommandType getType() {
        return this.type;
    }

    public String getBody() {
        return this.body;
    }
}
