package io.elkaim.canvas.challenge.command.model;

import java.util.Optional;


public enum CommandType {
    CREATE_CANVAS("C","Create a Canvas. If one exist, modify its width and height."),
    CREATE_LINE("L","Create a line"),
    CREATE_RECTANGLE("R","Create a rectangle"),
    BULK_FILL("B","Bulk fill the designated Area"),
    QUIT("Q","Quit application");

    private final String abbreviate;
    private final String description;

    CommandType(String abbreviate, String description){
     this.abbreviate = abbreviate;
     this.description = description;
    }

    public static Optional<CommandType> of(String abbreviate){
        for(CommandType cmd : CommandType.values()){
            if(cmd.getAbbreviate().equals(abbreviate)){
                return Optional.of(cmd);
            }
        }
        return Optional.empty();
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public String getDescription() {
        return description;
    }
}
