package io.elkaim.canvas.challenge.command.model;

import java.util.Optional;


public enum CommandType {
    CREATE_CANVAS("C","Create a Canvas. If one exist, modify its width and height."),
    CREATE_LINE("L","Create a line"),
    CREATE_RECTANGLE("R","Create a rectangle");

    private String abbreviate;
    private String description;

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
