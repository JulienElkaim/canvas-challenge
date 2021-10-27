package io.elkaim.canvas.challenge.utils;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MaskUtilsTest {

    @Test
    void drawHorizontal() {
    }

    @Test
    void drawVertical() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        printStream.println("Test here");
        printStream.println("and here also !");

        System.out.println(byteArrayOutputStream.toString());

        String data = "Hello, World!\r\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);
            System.out.println("user entered: " + scanner.nextLine());
        } finally {
            System.setIn(stdin);
        }
    }
}
