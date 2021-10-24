package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.command.CommandExecutor;
import io.elkaim.canvas.challenge.command.exceptions.QuitApplicationSignalException;
import io.elkaim.canvas.challenge.command.executors.abstracts.AbstractExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.painter.MessagePainter;

public class QuitApplicationExecutor extends AbstractExecutor {

    public QuitApplicationExecutor(MessagePainter messagePainter) {
        super(messagePainter);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.QUIT;
    }

    @Override
    public void execute(Command cmd) {
        this.messagePainter.draw("You requested to quit.");
        throw new QuitApplicationSignalException();

    }
}
