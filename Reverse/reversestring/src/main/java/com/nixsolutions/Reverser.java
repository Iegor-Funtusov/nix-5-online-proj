package com.nixsolutions;

public class Reverser  {

    /* Reverse full String using array*/
    /* Return reversed String*/
    public static String toReverse(String str) {
        char[] array = str.toCharArray();
        String reversed = "";
        for (int i = array.length - 1; i >= 0; i--) {
            reversed = reversed + array[i];
        }
        return reversed;
    }

    /* Reverse only subString using regx from argument, the rest of the symbols remain in their places */
    /* Return original String with reversed subString*/
    public static String toReverseBySubStr(String str, String subStr) {
        return str.replaceAll(subStr,"") + Reverser.toReverse(subStr);
    }

    /* Reverse String from index to index, the rest of the symbols remain in their places  */
    /* Return original String with reversed subString*/
    public static String toReverseByIndex(String str, int firstIndex, int lastIndex) {

        String cutStr = str.substring(firstIndex, lastIndex +1);
        return str.replaceAll(cutStr,Reverser.toReverse(cutStr));
    }

    /* Reverse String from Symbol to Symbol, the rest of the symbols remain in their places  */
    /* Return original String with reversed subString*/
    public static String toReverseBySymbol(String str, char firstSymbol, char lastSymbol) {

        int first = str.indexOf(firstSymbol);
        int last = str.indexOf(lastSymbol) + 1;
        String cutStr = str.substring(first, last);
        return str.replaceAll(cutStr,Reverser.toReverse(cutStr));
    }

    /* Reverse String from CharSequence to CharSequence, the rest of the symbols remain in their places  */
    /* Return original String with reversed subString*/
    public static String toReverseByCharSequence(String str, CharSequence firstSymbol, CharSequence lastSymbol) {

        int first = str.indexOf(firstSymbol.toString());
        int last = str.indexOf(lastSymbol.toString()) + 1;
        String cutStr = str.substring(first, last);
        return str.replaceAll(cutStr,Reverser.toReverse(cutStr));
    }

}
