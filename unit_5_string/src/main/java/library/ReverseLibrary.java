package library;

public class ReverseLibrary {

    public static String reverse(String src, CharSequence firstStr, CharSequence lastStr){
        String open = firstStr.toString();
        String close = lastStr.toString();
        int start = src.indexOf(open);
        int end = src.indexOf(close,start + open.length());
        return reverse(src, start + open.length(), end);
    }

    public static String reverse(String src, char firstChar, char lastChar){
        int firstIndex = src.indexOf(firstChar);
        int lastIndex = src.indexOf(lastChar, firstIndex+1);
        return reverse(src, firstIndex, lastIndex);
    }
    public static String reverse(String src, int firstIndex, int lastIndex){
        String destSubstring = src.substring(firstIndex, lastIndex);
        return reverse(src,destSubstring);
    }

    public static String reverse(String src, String dest){
        return src.replace(dest, reverse(dest));
    }

    public static String reverse(String src){
        char[] srcChars = src.toCharArray();
        for (int i = 0; i < srcChars.length/2; i++) {
            char tmp = srcChars[i];
            srcChars[i] = srcChars[srcChars.length-i-1];
            srcChars[srcChars.length-i-1] = tmp;
        }
        return String.valueOf(srcChars);
    }
}
