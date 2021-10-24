package io.elkaim.canvas.challenge.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputRequestor {
    private final Scanner scanner;
    private final PrintStream prompter;

    public InputRequestor(InputStream in, PrintStream out){
        this.scanner =  new Scanner(in);
        this.prompter = out;
    }

    public String promptUserForInput(String msg){
        this.prompter.print("> " + msg + ": ");
        if(this.scanner.hasNextLine()){
            return this.scanner.nextLine();
        }
        return null;
    }

}
