package main;

import figure.*;
import java.util.Scanner;

public class MainGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        board.boardPlacing();
        while (true) {
            System.out.println("________________________________________________________________\nВыполните выбор фигуры \n" +
                    "K = Король\nQ - Ферзь\nR - Ладья\nB - Слон\nN - Конь\nP - Пешка");
            String figure = sc.nextLine();
            while (!ValidContr.correctFigure(figure)) {
                System.out.println("Введенный символ не сообтветсятвует ни одной фигуре, попробуйте еще раз.");
                figure = sc.nextLine();
            }

            System.out.println("Выберите цвет \nw - Белый\nb - Черный\nФигуры черного цвета будут отображаться с \'*\'");
            String color = sc.nextLine();
            while (!ValidContr.correctColor(color)) {
                System.out.println("Введенный символ не соответсятвует ни одному цвету.");
                color = sc.nextLine();
            }

            System.out.println("Введите начальную позицию. Например е3");
            String place = sc.nextLine();

            while (!ValidContr.correctPlace(place)) {
                System.out.println("Вы пытаетесь выйти за границы доски");
                place = sc.nextLine();
            }


            char letter = place.charAt(0);
            char number = place.charAt(1);
            char fig = figure.charAt(0);

            if (fig == 'P' || fig == 'p') {
                Pawn pawn = new Pawn();
                pawn.pawn(place, color);
            } else if (fig == 'R' || fig == 'r') {
                Rook rook = new Rook();
                rook.rook(place, color);
            } else if (fig == 'K' || fig == 'k') {
                King king = new King();
                king.king(place, color);
            } else if (fig == 'B' || fig == 'b') {
                Bishop bishop = new Bishop();
                bishop.bishop(place, color);
            } else if (fig == 'Q' || fig == 'q') {
                Queen queen = new Queen();
                queen.queen(place, color);
            }
            else if (fig == 'N' || fig == 'n') {
                Knight knight = new Knight();
                knight.knight(place, color);
            }
            System.out.println("Выбрать фигуру - N, Закончить игру - E");
            String exitLine = sc.nextLine();
            exitLine = exitLine.toLowerCase();
            char exit = exitLine.charAt(0);

            if (exit == 'e' || exit == 'E')
                return;
        }
    }



}
