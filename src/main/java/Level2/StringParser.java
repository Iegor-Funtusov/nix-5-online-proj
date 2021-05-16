package Level2;

import java.util.ArrayList;
import java.util.List;

public class StringParser {
    private String str;


    public StringParser(){
        str = "";
        String braces = "(){}[]";
        String symbols = ",./123";

        for (int i = 0; i < (int)(Math.random() * 20); i++){
            if(i % 2 == 0){
                str += braces.charAt((int)(Math.random() * braces.length()));
            }
            else{
                str += symbols.charAt((int)(Math.random() * symbols.length()));
            }
        }
    }

    public StringParser(String s){
        str = s;
    }


    public  boolean checkBraces(){
        if(str.length() == 0){
            return false;
        }

        //Заполняю скобками
        List<Character> brackets = new ArrayList<>();
        for (int i = 0; i < str.length(); i++){
            switch (str.charAt(i)) {
                case '(' -> { brackets.add('('); }
                case ')' -> { brackets.add(')'); }
                case '[' -> { brackets.add('['); }
                case ']' -> { brackets.add(']'); }
                case '{' -> { brackets.add('{'); }
                case '}' -> { brackets.add('}'); }
            }
        }

        //Если нет скобок
        if(brackets.size() == 0)
            return false;

        //Разворачиваю лист скобок отражая их
        List<Character> reversed = new ArrayList<>();
        for(int i = brackets.size()-1; i >= 0; i--){
            switch (brackets.get(i)){
                case '(' -> { reversed.add(')'); }
                case ')' -> { reversed.add('('); }
                case '[' -> { reversed.add(']'); }
                case ']' -> { reversed.add('['); }
                case '{' -> { reversed.add('}'); }
                case '}' -> { reversed.add('{'); }
            }
        }

        //Проверка на палиндром
        if(brackets.equals(reversed))
            return true;
        return false;

    }


    public String getString() {
        return str;
    }
}
