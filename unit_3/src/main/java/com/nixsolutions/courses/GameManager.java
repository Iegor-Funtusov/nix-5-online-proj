package com.nixsolutions.courses;

import com.nixsolutions.courses.basics.Board;
import com.nixsolutions.courses.basics.Figure;
import com.nixsolutions.courses.basics.Square;
import com.nixsolutions.courses.figures.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameManager {

    public static Board board;
    public static Figure figure;
    public static BufferedReader input;

    public static void chooseMove(Figure figure) throws IOException {
        while (true) {
            Square newPosition = choosePosition();
            if (figure.validMove(figure.getPosition(), newPosition)) {
                System.out.println("Figure moved");
                figure.setPosition(newPosition);
                System.out.println("Continue moving or restart?\n0 - continue\n1 - restart");
                switch (input.readLine()) {
                    case "0":
                        System.out.println("Choose where to move (like k7):");
                        continue;
                    case "1":
                        return;
                }
//                int c =  Integer.parseInt(input.readLine());
//                System.out.println(c);
//                if(c) {
//                    return;
//                } else {
//                    System.out.println("Choose where to move (like k7):");
//                    continue;
//                }
            }
            System.out.println("Not valid move, choose another position");
        }
    }

    public static Square choosePosition() throws IOException {
        int x, y;
        String s;
        while (true) {
            s = input.readLine();
            switch (s.charAt(0)) {
                case 'a':
                    x = 0;
                    break;
                case 'b':
                    x = 1;
                    break;
                case 'c':
                    x = 2;
                    break;
                case 'd':
                    x = 3;
                    break;
                case 'e':
                    x = 4;
                    break;
                case 'f':
                    x = 5;
                    break;
                case 'g':
                    x = 6;
                    break;
                case 'h':
                    x = 7;
                    break;
                default:
                    System.out.println("Wrong column option, renter position");
                    continue;
            }
            y = Integer.parseInt(String.valueOf(s.charAt(1))) - 1;

            if (board.getSquare(x, y) != null) {
                return board.getSquare(x, y);
            }
        }
    }

    public static boolean chooseColor() throws IOException {
        System.out.println("Choose color:\n0 - exit\n1 - white\n2 - black");
        String s;
        while (true) {
            s = input.readLine();
            switch (s) {
                case "0":
                    System.exit(0);
                case "1":
                    return true;
                case "2":
                    return false;
                default:
                    System.out.println("Wrong color option");
            }
            System.out.println("Choose color:\n0 - exit\n1 - white\n2 - black");
        }
    }

    public static Figure chooseFigure() throws IOException {
        printFigureOptions();
        String s;
        while ((s = input.readLine()) != null) {
            switch (s) {
                case "0":
                    System.exit(0);
                case "1":
                    return new King();
                case "2":
                    return new Queen();
                case "3":
                    return new Rook();
                case "4":
                    return new Bishop();
                case "5":
                    return new Knight();
                case "6":
                    return new Pawn();
                default:
                    System.out.println("Wrong figure option");
            }
            printFigureOptions();
        }
        return null;
    }

    public static void printFigureOptions() {
        System.out.println("Choose figure:");
        System.out.println("0 - exit\n1 - King\n2 - Queen\n3 - Rook\n4 - Bishop\n5 - Knight\n6 - Pawn");
    }

    public static void main(String[] args) {
        board = new Board();
        input = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                    figure = chooseFigure();
                    figure.setIsWhite(chooseColor());
                    System.out.println("Choose position (like h6):");
                    figure.setPosition(choosePosition());
                    System.out.println("Choose where to move (like h7):");
                    chooseMove(figure);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
