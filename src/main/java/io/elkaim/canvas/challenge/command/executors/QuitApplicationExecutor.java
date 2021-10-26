package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.command.exceptions.QuitApplicationSignalException;
import io.elkaim.canvas.challenge.command.executors.abstracts.BasicCommandExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;

public class QuitApplicationExecutor extends BasicCommandExecutor {

    public QuitApplicationExecutor(MessagePrinter messagePrinter) {
        super(messagePrinter);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.QUIT;
    }

    @Override
    public void execute(Command cmd) {
        this.messagePrinter.print("You requested to quit.");
        throw new QuitApplicationSignalException();

    }
}
