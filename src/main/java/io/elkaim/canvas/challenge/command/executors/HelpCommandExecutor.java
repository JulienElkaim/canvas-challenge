package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.command.executors.abstracts.BasicCommandExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;

import java.util.stream.Stream;

public class HelpCommandExecutor extends BasicCommandExecutor {

    public static final String HELP_INTRODUCTION = "Find bellow the available commands:";

    public HelpCommandExecutor(MessagePrinter messagePrinter) {
        super(messagePrinter);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.HELP;
    }

    @Override
    public void execute(Command cmd) {
        this.messagePrinter.print(HELP_INTRODUCTION);
        Stream.of(CommandType.values())
                .forEach(commandType -> {
                    this.messagePrinter.print("=====================");
                    this.messagePrinter.print(String.format(
                            "%s\t\t%s\t\t%s",
                            commandType.getAbbreviate(),
                            commandType.getBodyFormat().getHumanFriendly(),
                            commandType.getDescription()
                    ));
                });
    }
}
