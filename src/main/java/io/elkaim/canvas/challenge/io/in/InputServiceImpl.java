package io.elkaim.canvas.challenge.io.in;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Implementation working with InputStream (in) and PrintStream (out)
 */
public class InputServiceImpl implements InputService {
    private final Scanner scanner;
    private final PrintStream outputStream;

    public InputServiceImpl(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.outputStream = out;
    }

    @Override
    public String requestInputLine(String msg) {
        this.outputStream.print("> " + msg + ": ");
        if (this.scanner.hasNextLine()) {
            return this.scanner.nextLine();
        }
        return null;
    }

}
