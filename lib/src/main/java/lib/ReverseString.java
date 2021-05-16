package lib;

public class ReverseString {

    //Разворот всей строки
    public static String reverse(String str){
        if(str == null){
            return str;
        }

        String result = "";
        int j = str.length()-1;
        for (int i = str.length()-1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }


    //Разворот подстроки в строке
    public static String reverse(String str, String subString){
        if(str == null || subString == null){
            return str;
        }

        String reversedSubString = reverse(subString);
        str = str.replace(subString, reversedSubString);
        return str;
    }


    //Разворот от первого индекса до второго
    public static String reverse(String str, int firstIndex, int lastIndex){
        if(str == null){
            return str;
        }
        //Проверка корректности индекса
        if (firstIndex < 0 || lastIndex > str.length() || firstIndex > lastIndex){
            return str;
        }

        String subStr = str.substring(firstIndex, lastIndex + 1);
        String subStrReversed = reverse(subStr);
        str = str.replace(subStr, subStrReversed);
        return str;
    }


    //Разворот от первого символа до второго
    public static String reverse(String str, char firstSymbol, char lastSymbol){
        if(str == null){
            return str;
        }

        int firstIndex = str.indexOf(firstSymbol);
        int lastIndex = str.indexOf(lastSymbol);

        str = reverse(str, firstIndex, lastIndex);
        return str;
    }


    //Разворот, от конца первой строки до начала второй
    public static String reverse(String str, String first, String last){
        if(str == null || first == null || last == null){
            return str;
        }

        int firstIndex = first.indexOf(first.charAt(first.length()-1));        //Индекс конца первой строки
        int lastIndex = str.indexOf(last.charAt(0));     //Индекс начала второй строки

        str = reverse(str, firstIndex, lastIndex);
        return str;
    }
}