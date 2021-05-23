package ua.com.alevel.string.lib;

public class StringReverser {

    public static String reverse(String src)
    {
        char[] currentString = src.toCharArray();
        char[] reversedString = new char[currentString.length];

        int revPos = 0;
        for (int i = currentString.length - 1; i >= 0; i--)
        {
            reversedString[revPos] = currentString[i];
            revPos++;
        }

        return new String(reversedString);
    }

    public static String reverse(String src, String dest)
    {
        int firstIndex = src.indexOf(dest);
        if (firstIndex == -1)
        {
            System.out.println("Substring isn't exist!");
            return src;
        }
        int lastIndex = firstIndex + dest.length() - 1;
        return reverse(src, firstIndex, lastIndex);
    }

    public static String reverse(String src, char firstSymbol, char lastSymbol)
    {
        int firstIndex = src.indexOf(firstSymbol);
        int lastIndex = src.indexOf(lastSymbol, firstIndex);
        return reverse(src, firstIndex, lastIndex);
    }

    public static String reverse(String src, CharSequence firstChar, CharSequence lastChar)
    {
        int firstIndex = src.indexOf(firstChar.toString());
        int lastIndex = src.indexOf(lastChar.toString(), firstIndex);
        return reverse(src, firstIndex, lastIndex);
    }

    public static String reverse(String src, int firstIndex, int lastIndex)
    {
        char[] currentString = src.toCharArray();
        char[] reversedString = new char[currentString.length];

        for (int i = 0; i < firstIndex; i++)
        {
            reversedString[i] = currentString[i];
        }

        for (int i = firstIndex; i <= lastIndex ; i++)
        {
            reversedString[i] = currentString[lastIndex + firstIndex  - i];
        }

        for (int i = lastIndex + 1; i < currentString.length; i++)
        {
            reversedString[i] = currentString[i];
        }
        return new String(reversedString);
    }


}
