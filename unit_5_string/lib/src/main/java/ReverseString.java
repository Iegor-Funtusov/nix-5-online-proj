public class ReverseString {
  public static String reverseString(String string) {
      String result = "";
      for (int i = 0; i < string.length(); i++) {
          result = string.charAt(i) + result;
      }
      return result;
  }

    public static String reverseString(String string, String substring) {
        String result = "";
        String substringReverse = "";
        for (int i = 0; i < substring.length(); i++) {
            substringReverse = substring.charAt(i) + substringReverse;
            }
        result = string.replace(substring,substringReverse);
        return result;
    }

    public static String reverseString(String string, int firstIndex, int lastIndex) {
        String result = "";
        String substringReverse = "";
        indexAcceptable(string, firstIndex, lastIndex);
        String substring = string.substring(firstIndex, lastIndex + 1);
   //     System.out.println(substring);
        String[] words = substring.split(" "); // Разбиение строки на слова с помощью разграничителя (пробел)
        for (int i=0; i < words.length; i++) {
            substringReverse = (substringReverse + " " + reverseString(words[i])).trim();
        }
        result = string.replace(substring,substringReverse);
        return result;
    }

    public static String reverseString(String string, char firstChar, char lastChar) {
        String result = "";
        String substringReverse = "";
        String substring = "";
        int firstIndex = string.indexOf(firstChar);
        int lastIndex = string.indexOf(lastChar);
        substring = string.substring(firstIndex, lastIndex + 1);
        //     System.out.println(substring);
        String[] words = substring.split(" "); // Разбиение строки на слова с помощью разграничителя (пробел)
        for (int i=0; i < words.length; i++) {
            substringReverse = (substringReverse + " " + reverseString(words[i])).trim();
        }
        result = string.replace(substring,substringReverse);
        return result;
    }

    public static String reverseString(String string, String firstString, String lastString) {
        String result = "";
        String substringReverse = "";
        String substring = "";
        int counter = lastString.length();
        int firstIndex = string.indexOf(firstString);
        int lastIndex = string.indexOf(lastString);
        substring = string.substring(firstIndex, lastIndex + counter);
        //     System.out.println(substring);
        String[] words = substring.split(" "); // Разбиение строки на слова с помощью разграничителя (пробел)
        for (int i=0; i < words.length; i++) {
            substringReverse = (substringReverse + " " + reverseString(words[i])).trim();
        }
        result = string.replace(substring,substringReverse);
        return result;
    }

    private static void indexAcceptable(String string, int firstIndex, int lastIndex) {
        if (string.length() < firstIndex || string.length() <= lastIndex) {
            throw new IllegalArgumentException("Индекс за пределами строки!");
        }
        if (firstIndex >= lastIndex) {
            throw new IllegalArgumentException("Второй индекc должен быть больше первого!");
        }
    }

}
