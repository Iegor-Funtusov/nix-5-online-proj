package ua.com.threadedcode.cli;

import ua.com.threadedcode.controller.Chess;
import ua.com.threadedcode.figure.Figure;
import ua.com.threadedcode.cli.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cli {
    Chess chess = new Chess();
    String side = "white";

    public Figure select(String input, String side) {
        int[] inputs = Util.StringToIntegerArray(input);
        Figure figure = chess.selectFigure(inputs[0], inputs[1], side);
        System.out.println("You choose: " + figure);
        return figure;
    }


    public void move(BufferedReader reader, String input, Figure figure) throws IOException {
        System.out.print("enter newX,newY \n>");
        input = reader.readLine();
        int[] inputs = Util.StringToIntegerArray(input);
        chess.moveFigure(inputs[0], inputs[1], figure);
        System.out.println( figure + " moved: x=" + figure.getX() + ", y=" + figure.getY());
    }


    public void loopMenu(BufferedReader reader, String input, Figure figure) throws IOException {
        while ((input = reader.readLine()) != null) {
            if (input.equals("1")) {
                move(reader, input, figure);
                System.out.println("1: move figure, 2: select figure, 0: exit");
                continue;
            }

            if (input.equals("0")) {
                break;
            }

            System.out.println("enter x,y for select figure");
            input = reader.readLine();
            figure = select(input, side);
            System.out.println("1: move figure, 2: select figure");
        }
    }

    public void readConsole() throws IOException {
        Figure figure;
        side = "white";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.println("Choose figure color: 1: white, 2: black, 0: exit");

        while ((input = reader.readLine()) != null) {
            if ("2".equals(input)) {
                side = "black";
            } else if ("0".equals(input)) {
                System.out.println("exit");
                System.exit(0);
            }
            chess.run(side);

            System.out.print("Select figure. Enter x,y \n>");
            input = reader.readLine();
            figure = select(input, side);
            System.out.println("1: move figure, 2: select figure");

            loopMenu(reader, input, figure);
            break;
        }
    }
}
