package libs;

public class ReverseString {
    public static String reverse (String string) {
        String newString = "";

        String[] words = string.split(" ");

        for (int k = 0; k < words.length; k++) {
            for (int i = words[k].length()-1; i >= 0 ; i--) {
                newString += words[k].charAt(i);
            }
            newString += " ";
        }

        return newString;
    }


    public static String reverse (String string, String substring){
        String newString = "";
        String reverseSubStr = "";

        for(int i = substring.length()-1; i >= 0; i--){
            reverseSubStr += substring.charAt(i);
        }

        for(int i = 0; i < string.length(); i++) {
            for (int k = 0; k < substring.length(); k++) {
                if(i+k < string.length()) {
                    if (string.charAt(i + k) != substring.charAt(k)) {
                        newString += string.charAt(i);
                        break;
                    }
                }
                else{
                    for(int l = i; l < string.length(); l++)
                    {
                        newString += string.charAt(l);
                    }
                    return newString;
                }
                if(k == substring.length()-1){
                    newString += reverseSubStr;
                    i += k;
                }
            }
        }
        return newString;
    }

    public static String reverseFromString (String string, String substring){
        String newString = "";
        String reverseSubStr = "";

        for(int i = 0; i < string.length(); i++) {
            for (int k = 0; k < substring.length(); k++) {
                if(i+k < string.length()) {
                    if (string.charAt(i + k) != substring.charAt(k)) {
                        newString += string.charAt(i);
                        break;
                    }
                }
                if(k == substring.length()-1){
                    for(int m = i; m < string.length(); m++)
                    {
                        reverseSubStr += string.charAt(m);
                    }

                    newString += reverse(reverseSubStr);
                    return newString;
                }
            }
        }
        return newString;
    }

    public static String reverseBeforeString (String string, String substring){
        String newString = "";
        for(int i = 0; i < string.length(); i++) {
            for (int k = 0; k < substring.length(); k++) {
                if(i+k < string.length()) {
                    if (string.charAt(i + k) != substring.charAt(k)) {
                        break;
                    }
                }
                if(k == substring.length()-1){
//                    newString += reverse(string, 0, i);
                    return reverse(string, 0, i);
                }
            }
        }
        return string;
    }

    public static String reverse(String string, int firstIndex, int lastIndex){
        String newString = "";
        String substring = "";
        String newSubstring = "";

        for(int i = firstIndex; i <= lastIndex; i++){
            substring += string.charAt(i);
        }
        String[] words = substring.split(" ");

        for (int k = 0; k < words.length; k++) {
            for (int i = words[k].length()-1; i >= 0 ; i--) {
                newSubstring += words[k].charAt(i);
            }
            if(k == words.length-1 && string.charAt(lastIndex) == ' ')
            {
                newSubstring += " ";
            }
            else if (k != words.length-1)
                newSubstring += " ";
        }

        for(int i = 0; i < string.length(); i++){
            if(i == firstIndex){
                newString += newSubstring;
                i += lastIndex-firstIndex+1;
            }
            if(string.length() > i) {

                newString += string.charAt(i);
            }
        }

        return newString;
    }


    public static String reverse(String string, char firstLet, char lastLet) {
        String newString = "";
        String substring = "";
        String newSubstring = "";
        int first, last;

        first = string.indexOf(firstLet);
        last = string.indexOf(lastLet, first);

        if(first == -1 || last == -1){
            System.out.println("One of the symbols doesn't exist in the string");
            return string;
        }

        for(int i = first; i <= last; i++){
            substring += string.charAt(i);
        }

        String[] words = substring.split(" ");

        for (int k = 0; k < words.length; k++) {
            for (int i = words[k].length()-1; i >= 0 ; i--) {
                newSubstring += words[k].charAt(i);
            }
            if(k == words.length-1 && string.charAt(last) == ' ')
            {
                newSubstring += " ";
            }
            else if (k != words.length-1)
                newSubstring += " ";
        }

        for(int i = 0; i < string.length(); i++){
            if(i == first){
                newString += newSubstring;
                i += last-first+1;
            }
            if(string.length() > i) {
                newString += string.charAt(i);
            }
        }

        return newString;
    }
}
