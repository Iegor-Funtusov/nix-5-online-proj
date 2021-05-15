package chess;


import java.util.Scanner;

public class Knight {
    public void knight(int horizontal, int vertical) {
        Scanner scanner = new Scanner(System.in);
        Control.figure(horizontal, vertical);
        int moveHorNumber;
        int moveVerNumber;
        while (true){
            System.out.println("Choose the way you want to move the knight (random, by hand) or \"exit\" from task 2");
            String input = scanner.nextLine();
            input = input.toLowerCase();
            switch (input) {
                case "by hand": {
                    System.out.println("\nInput place where you want to move\n");
                    System.out.print("Horizontal number: ");
                    moveHorNumber = Control.correctNum();
                    System.out.print("Vertical number: ");
                    moveVerNumber = Control.correctNum();
                    while (!Control.moveKnight(horizontal, vertical, moveHorNumber, moveVerNumber)) {
                        if (!Control.moveKnight(horizontal, vertical, moveHorNumber, moveVerNumber)) {
                            System.out.println("Knight can't move there. Input new place.");
                        }

                        System.out.print("Horizontal number: ");
                        moveHorNumber = Control.correctNum();
                        System.out.print("Vertical number: ");
                        moveVerNumber = Control.correctNum();
                    }
                    Control.figure(moveHorNumber, moveVerNumber);
                    horizontal = moveHorNumber;
                    vertical = moveVerNumber;
                }break;
                case "random":{

                    int randVer = (int) Math.round((Math.random() * 2) % 2);
                    int randHor = (int) Math.round((Math.random() * 2) % 2);
                    System.out.println("Horizontal number: " + (horizontal+randHor));
                    System.out.println("Vertical number: " + (vertical+randVer));
                    while(!Control.moveKnight(horizontal, vertical, horizontal+randHor, vertical+randVer)) {
                        if (!Control.moveKnight(horizontal, vertical, horizontal+randHor, vertical+randVer)) {
                            System.out.println("Knight can't move there. Input new place.");
                            randVer = (int) Math.round((Math.random() * 2) % 2);
                            randHor = (int) Math.round((Math.random() * 2) % 2);
                            System.out.println("Horizontal number: " + (horizontal+randHor));
                            System.out.println("Vertical number: " + (vertical+randVer));
                        }
                    }
                    Control.figure(horizontal+randHor, vertical+randVer);
                    horizontal = horizontal+randHor;
                    vertical = vertical+randVer;
                    System.out.println("Next step");
                }break;
                case "exit":{
                    return;
                }
                default:{
                    System.out.println("Incorrect input");
                }break;
            }
        }
    }
}
