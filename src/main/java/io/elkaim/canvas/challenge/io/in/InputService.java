package io.elkaim.canvas.challenge.io.in;

/**
 * Interface to handle inputs from users
 */
public interface InputService {
    /**
     * WIll request input from user, using the msg provided by consumer.
     * @param msg, explain to the user what to input
     * @return input line from user
     */
    String requestInputLine(String msg);
}
