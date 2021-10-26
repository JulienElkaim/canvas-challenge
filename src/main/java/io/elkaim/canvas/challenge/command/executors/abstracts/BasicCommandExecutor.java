package io.elkaim.canvas.challenge.command.executors.abstracts;

import io.elkaim.canvas.challenge.command.CommandExecutor;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.command.model.BodyFormat;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Methods & dependencies inherent to every CommandExecutor components.
 */
public abstract class BasicCommandExecutor implements CommandExecutor {
    protected final MessagePrinter messagePrinter;

    public BasicCommandExecutor(MessagePrinter messagePrinter) {
        this.messagePrinter = messagePrinter;
    }

    /**
     * Get a specific argument of a command, or throw an error.
     *
     * @param args all the arguments provided by the user.
     * @param key  name of the argument in the BodyFormat's regex.
     * @return the argument we are looking for.
     */
    protected final String getArg(Map<String, String> args, String key) {
        return this.getArg(args, key, true);
    }

    /**
     * Get a specific argument of a command, or throw an error if argument is required but not found.
     *
     * @param args all the arguments provided by the user.
     * @param key  name of the argument in the BodyFormat's regex.
     * @return the argument we are looking for, or null if does not required and does not exist
     * @throws MalFormedCommandException if required but not found.
     */
    protected final String getArg(Map<String, String> args, String key, boolean required) {
        String arg = args.get(key);
        if (required && Objects.isNull(arg)) {
            throw new MalFormedCommandException(String.format("Missing required argument <%s> in the command body.", key));
        } else {
            return arg;
        }
    }

    /**
     * Parse the command's body to find arguments required to execute the command.
     *
     * @param format the format's regex and human friendly representation.
     * @param body   the element to parse.
     * @return all the arguments mapped by their name.
     */
    protected final Map<String, String> parseCommandBody(BodyFormat format, String body) {
        Map<String, String> result = new HashMap<>();
        Set<String> groupNames = this.getGroupNames(format.getBodyRegex());
        final Pattern pattern = Pattern.compile(format.getBodyRegex());
        final Matcher matcher = pattern.matcher(body.trim());

        if (matcher.find()) {
            groupNames.forEach(gn -> result.put(gn, matcher.group(gn)));
        } else {
            throw new MalFormedCommandException(String
                    .format("Body provided does not respect the expected format...\nReceived: %s\nExpected format: %s",
                            body, format.getHumanFriendly()));
        }

        return result;
    }

    /**
     * Names of group found inside a regex.
     *
     * @param regex to be analized.
     * @return all group names found.
     */
    private Set<String> getGroupNames(String regex) {
        Set<String> namedGroups = new TreeSet<String>();
        Matcher m = Pattern.compile("\\(\\?<([a-zA-Z][a-zA-Z0-9]*)>").matcher(regex);

        while (m.find()) {
            namedGroups.add(m.group(1));
        }
        return namedGroups;
    }
}
