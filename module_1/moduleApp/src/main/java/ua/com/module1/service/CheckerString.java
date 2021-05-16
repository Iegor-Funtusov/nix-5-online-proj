package ua.com.module1.service;

import org.apache.commons.lang3.StringUtils;
import ua.com.module1.CheckString;

import java.util.*;

public class CheckerString {
    public static void getStingChecker(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s = scanner.nextLine();
        if(!StringUtils.isNoneBlank(s)) {
            System.out.println("String is valid");
        }
        else if (CheckString.getCheckerString(s)){
            System.out.println("String is valid");
        }
            else{
                System.out.println("String is NOT valid");
            }
        }

        public static String shuffle (String s){
            char[] characters = s.toCharArray();
            for(int i = 0; i < characters.length; i++ ){
                int randomIndex = (int) (Math.random() * characters.length);
                char temp = characters[i];
                characters[i] = characters[randomIndex];
                characters[randomIndex] = temp;
            }
        return new String(characters);
        }

        public static void getStingCheckerAuto(){
            String symbols = "(){}[]";
            int counter = 0;
            while(counter<100){
            String s = shuffle(symbols);
            System.out.println(s);
                if(!StringUtils.isNoneBlank(s)){
                    System.out.println("String is valid");
                    break;
                }
                counter++;
                System.out.println("Step -> " + counter);
               if(CheckString.getCheckerString(s)) {
                    System.out.println("String is valid");
                    break;
                 }
                else{
                    System.out.println("String is NOT valid");
                }
        }
    }
}


