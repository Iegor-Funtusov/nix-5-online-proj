public class ReverseString {
    public static String toReverse(String str) {
        char[] array = str.toCharArray();
        String reversed = "";
        for (int i = array.length - 1; i >= 0; i--) {
            reversed = reversed + array[i];
        }
        return reversed;
    }

    public static String ReverseBySubStr(String str, String subStr) {
        return str.replaceAll(subStr, "") + ReverseString.toReverse(subStr);
    }

    public static String ReverseByIndex(String str, int firstIndex, int lastIndex) {

        String cutStr = str.substring(firstIndex, lastIndex + 1);
        return str.replaceAll(cutStr, ReverseString.toReverse(cutStr));
    }

    public static String ReverseBySymbol(String str, char firstSymbol, char lastSymbol) {

        int first = str.indexOf(firstSymbol);
        int last = str.indexOf(lastSymbol) + 1;
        String cutStr = str.substring(first, last);
        return str.replaceAll(cutStr, ReverseString.toReverse(cutStr));
    }

    public static String ReverseByCharSequence(String str, CharSequence firstSymbol, CharSequence lastSymbol) {

        int first = str.indexOf(firstSymbol.toString());
        int last = str.indexOf(lastSymbol.toString()) + 1;
        String cutStr = str.substring(first, last);
        return str.replaceAll(cutStr, ReverseString.toReverse(cutStr));
    }
}