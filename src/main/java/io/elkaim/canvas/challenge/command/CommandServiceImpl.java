package io.elkaim.canvas.challenge.command;

import com.google.common.collect.Maps;
import io.elkaim.canvas.challenge.command.exceptions.NoExecutorFoundException;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;

import java.util.List;
import java.util.Map;

public class CommandServiceImpl implements CommandService {

    private final Map<CommandType, CommandExecutor> executorMap = Maps.newConcurrentMap();

    public CommandServiceImpl(List<CommandExecutor> executors) {
        executors.forEach(proc -> executorMap.put(proc.getCommandType(), proc));
    }

    @Override
    public void execute(Command command) {
        if (executorMap.containsKey(command.getType())) {
            executorMap.get(command.getType()).execute(command);
        } else {
            throw new NoExecutorFoundException(String
                    .format("No behavior implemented for command \"%s\" .",
                            command.getType().getAbbreviate()));
        }
    }
}
