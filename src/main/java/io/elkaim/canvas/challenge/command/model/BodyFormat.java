package io.elkaim.canvas.challenge.command.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Describes the body that a specific command has to provide.
 * First, in a human friendly way (useful for documentation) and,
 * Secondly, as a regex for parsing purpose.
 */
@Getter
@AllArgsConstructor
public class BodyFormat {
    private String bodyRegex;
    private String humanFriendly;

    public static BodyFormat of(String humanFriendly, String regex) {
        return new BodyFormat(regex, humanFriendly);
    }
}
