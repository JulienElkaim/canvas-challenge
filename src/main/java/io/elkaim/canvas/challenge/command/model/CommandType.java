package io.elkaim.canvas.challenge.command.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * Describe the different command types available in this application.
 * Any characteristics of commands are described here.
 */
@Getter
@AllArgsConstructor
public enum CommandType {
    CREATE_CANVAS("C",
            BodyFormat.of("C w h", "^(?<width>\\d+)\\s+(?<height>\\d+)\\s*$"),
            "Create a Canvas of width w and height h. If one already exists, it modifies its width and height.",
            true),

    LINE("L",
            BodyFormat.of("L x1 y1 x2 y2 (c?) (--low?)", "^(?<x1>\\d+)\\s+(?<y1>\\d+)\\s+(?<x2>\\d+)\\s+(?<y2>\\d+)(\\s+(?<color>[a-zA-Z]))?(\\s+(?<noPriority>\\-\\-low))?\\s*$"),
            "Create a line between (x1,y1) (x2,y2). If --low, existing elements will have the priority.",
            true),

    RECTANGLE("R",
            BodyFormat.of("R x1 y1 x2 y2 (c?) (--low?)",
                    "^(?<x1>\\d+)\\s+(?<y1>\\d+)\\s+(?<x2>\\d+)\\s+(?<y2>\\d+)(\\s+(?<color>[a-zA-Z]))?(\\s+(?<noPriority>\\-\\-low))?\\s*$"),
            "Create a rectangle between upper-left corner (x1,y1) and bottom-right corner (x2,y2). If --low, existing elements will have the priority.",
            true),

    FILL("B",
            BodyFormat.of("B x y (c)?", "^(?<x>\\d+)\\s+(?<y>\\d+)(\\s+(?<color>[a-zA-Z]))?\\s*$"),
            "Fill the area connected to the point (x,y) with color (character) c. Works on empty spaces and connected lines.",
            true),

    POINT("P",
            BodyFormat.of("P x y (c)?", "^(?<x>\\d+)\\s+(?<y>\\d+)(\\s+(?<color>[a-zA-Z]))?\\s*$"),
            "Create a single point (x,y) with color (character) c.",
            true),

    QUIT("Q",
            BodyFormat.of("Q", null),
            "Quit application.",
            false),

    HELP("HELP", BodyFormat.of("HELP", null),
            "Helps the user to understand the available queries functions.",
            false);

    private final String abbreviate;
    private final BodyFormat bodyFormat;
    private final String description;
    private final boolean redrawRequired;


    public static Optional<CommandType> of(String abbreviate) {
        for (CommandType cmd : CommandType.values()) {
            if (cmd.getAbbreviate().equals(abbreviate)) {
                return Optional.of(cmd);
            }
        }
        return Optional.empty();
    }

}
