package ua.com.alevel;

public class StringUtils {

    public static boolean isStringValid(String s){

        if (!isAnyBracketsExist(s))
            return true;

        char[] chars = s.toCharArray();
        char[] brackets = new char[chars.length];
        int lastOpenBrackets = -1;

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            switch (ch){
                case '(':
                case '{':
                case '[': {
                    lastOpenBrackets++;
                    brackets[lastOpenBrackets] = ch;
                    break;
                }
                case ')':
                case '}':
                case ']':{
                    if(lastOpenBrackets >= 0){
                        char openBrackets = brackets[lastOpenBrackets--];
                        if( (ch == ')' && openBrackets != '(')
                                || (ch == '}' && openBrackets != '{')
                                || (ch == ']' && openBrackets != '[')) {
                            return false;
                        }
                    }else {
                        return false;
                    }
                    break;
                }
                default:
                    break;
            }
        }

        if (lastOpenBrackets >= 0) {
            return false;
        }
        return true;
    }

    private static boolean isBracketsValid(char[] brackets, int bracketsCount) {
        if (bracketsCount % 2 == 1) {
            return false;
        }
        boolean isValid = false;
        for (int i = 1; i < bracketsCount; i+=2) {
            if(brackets[i] - brackets[i-1] == 2) {
                isValid = true;
                continue;
            }else if (brackets[i] == ')') {
                if (brackets[i] - brackets[i - 1] == 1) {
                    isValid = true;
                    continue;
                }
            }
            isValid = false;
            break;
        }
        return isValid;
    }

    public static boolean isAnyBracketsExist(String string){
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++)
            if (isSymbolBrackets(chars[i]))
                return true;
        return false;
    }

    private static boolean isSymbolBrackets(char symbol){
        if (symbol == '('
                || symbol == ')'
                || symbol == '{'
                || symbol == '}'
                || symbol == '['
                || symbol == ']'){
            return true;
        }
        return false;
    }

}
