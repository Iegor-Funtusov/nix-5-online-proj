import lev2.stringValid.StringGo;
import lev1.array.ArrGo;
import lev1.array.UniqInArr;
import lev1.chess.ChessGo;
import lev1.triangle.TriangleGo;
import lev1.triangle.TriangleSq;
import lev2.stringValid.StringGo;

import java.awt.*;
import java.util.Scanner;
public class MainController {
        public static void controllerRun(){

                Scanner scanner = new Scanner(System.in);
                while(true){
                        System.out.println("\n\n__________________________________\n" +
                                "MENU:                             |\n'1' - Uniq Number In Array        |\n'2' - Triangle Square             |" +
                                "\n'3' - Endless Knight's Move       |\n" +
                                "'4' - String Valid                |\n'5' - Exit                        |" +
                                "\n__________________________________");
                        int us = scanner.nextInt();
                        switch (us){
                                case 1:{
                                        ArrGo.RunArr();
                                        break;
                                }
                                case 2:{
                                        TriangleGo.run();
                                        break;
                                }
                                case 3:{
                                        ChessGo.chessRun();
                                        break;
                                }
                                case 4:{
                                        StringGo.StringRun();
                                        break;
                                }
                                case 5:{
                                        System.exit(0);
                                        break;
                                }
                                default:{
                                        System.out.println("Invalid input");
                                        break;
                                }
                        }
                }
        }


}
