package LevelControlles;

import Level2.BinaryTree;
import Level2.StringParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lev2Controller {
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));



    public void level2Interface() throws IOException {

        do {
            System.out.println("Which task do you want to check? 1-Parsing string, 2-Binary tree max depth");

            switch (Integer.parseInt(bf.readLine())) {
                case 1 -> task1Interface();
                case 2 -> task2Interface();
                default -> System.out.println("Incorrect value entered");
            }

            System.out.println("Do you want to continue at this level? 1-yes, else-no");

        } while (Integer.parseInt(bf.readLine()) == 1);
    }



    private void task1Interface() throws IOException{
        StringParser stringParser;
        String str;
        System.out.println("You chose task 1: Parsing string");
        if(chooseMethodOfInput()){
            System.out.println("Enter string which you want to check:");
            str = bf.readLine();
            if(str == null){
                System.out.println("Incorrect string entered");
                return;
            }
            stringParser = new StringParser(str);

            if(stringParser.checkBraces()){
                System.out.println("Braces is all right");
                return;
            }
            else{
                System.out.println("Braces is not in correct position");
            }
        }
        else{
            stringParser = new StringParser();
            System.out.println("Generated string: " + stringParser.getString());
            if(stringParser.checkBraces()){
                System.out.println("Braces is all right");
                return;
            }
            else{
                System.out.println("Braces is not in correct position");
            }

        }
    }



    private void task2Interface() throws IOException{
        System.out.println("You chose task 2: Binary tree max depth");
        BinaryTree binaryTree = new BinaryTree();
        System.out.println("!In this task you can only fill in elements by keyboard!");

        System.out.println("Do you want to check examples of input? 1-yes, else-no");
        if (Integer.parseInt(bf.readLine()) == 1) {
            System.out.println("Example #1: 1 2 3 0 0 4 0 0 5 6 0 0 7 0 0");
            System.out.println("Example #2: 1 2 3 4 0 0 0 0 5 0 0");
            System.out.println("Example #3: 1 0 2 0 3 4 0 0 5 0 0");
        }
        System.out.println("Please, write the elements by the ENTER");
        binaryTree.formTree();

        System.out.println("Symmetric binary tree traversal:");
        binaryTree.displaySymmetric();
        System.out.println("\nMax depth in tree: " + binaryTree.maxDepth());
    }



    private boolean chooseMethodOfInput() throws IOException{
        System.out.println("Choose method of input: 1-from the keyboard, else-mechanically");
        if(Integer.parseInt(bf.readLine()) == 1){
            return true;
        }
        return false;
    }
}