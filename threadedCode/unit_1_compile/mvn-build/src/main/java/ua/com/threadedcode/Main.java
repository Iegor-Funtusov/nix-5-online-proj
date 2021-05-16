package ua.com.threadedcode;

import ua.com.threadedcode.service.CommandLine;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandLine commandLine = new CommandLine();
        commandLine.readConsole();
    }
}