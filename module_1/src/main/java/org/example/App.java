package org.example;

import org.example.console.Cli;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Cli cli = new Cli();
        cli.runMenu();
    }
}