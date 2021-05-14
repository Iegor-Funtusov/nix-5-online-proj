package ua.com.reverseimpl;

import org.apache.commons.lang3.StringUtils;

public class reverseString {
    public static String reverse(String src){
        if(StringUtils.isNoneBlank(src) && src.length()>= 2){
        StringBuilder stringBuilder = new StringBuilder();
        char [] a = src.toCharArray();
        for(int i = 0; i<(a.length/2); i++){
            char temp = a[i];
            a[i] = a[a.length-i-1];
            a[a.length-i-1] = temp;
        }
        stringBuilder.append(a);
        return stringBuilder.toString();}
        else {
           throw new IllegalArgumentException("String is empty");
        }
}

    private static int findsubstring(char[] fullstring, char [] substring){
        int entersubstring = -1;
        for(int i = 0; i < fullstring.length; i++){
            if(fullstring[i] == substring[0]){
                for(int j = 1; j < substring.length; j++){
                    if(fullstring[i+j] == substring[j]) {
                        entersubstring = i;
                    }
                    else{
                        entersubstring = -1;
                        break;
                    }
                }
            }
        }
        return entersubstring;
    }

    public static String reverse(String src, String dest){
        if(StringUtils.isNoneBlank(src) && StringUtils.isNoneBlank(dest)
        && src.length() > dest.length()){
        StringBuilder stringBuilder = new StringBuilder();
        char [] fullstring = src.toCharArray();
        char [] substring = dest.toCharArray();
        int entersubstring = reverseString.findsubstring(fullstring, substring);
        if(entersubstring != -1) {
            dest = reverseString.reverse(dest);
            substring = dest.toCharArray();
            stringBuilder.append(fullstring, 0, entersubstring);
            stringBuilder.append(substring, 0, substring.length);
            stringBuilder.append(fullstring, entersubstring + substring.length,
                    fullstring.length - (entersubstring + substring.length));
            return stringBuilder.toString();
        }
        System.out.println("Substring doesn`t exist");
        return src;}
        else {
            throw new IllegalArgumentException("String/substring is empty or substring`s length is longer than string");
        }
    }

    public static String reverse(String src, int firstIndex, int
            lastIndex){
        if(StringUtils.isNoneBlank(src) && firstIndex > 0 && firstIndex < lastIndex
    && firstIndex < src.length() && lastIndex <= src.length()-1)  {
        StringBuilder stringBuilder = new StringBuilder();
        char [] fullstring = src.toCharArray();
        StringBuilder divider = new StringBuilder();
        stringBuilder.append(fullstring, 0, firstIndex);
        for (int i = firstIndex; i <= lastIndex; i++){
            if(fullstring[i] != ' '){
            divider.append(fullstring[i]);
            }
            if(fullstring[i] == ' ' || i == lastIndex){
                char [] a = reverseString.reverse(divider.toString()).toCharArray();
                stringBuilder.append(a);
            if(i!= lastIndex){
                stringBuilder.append(' ');}
                divider.setLength(0);
            }
        }
        stringBuilder.append(fullstring, lastIndex + 1, fullstring.length-lastIndex-1);
        return stringBuilder.toString();}
        else {
            throw new IllegalArgumentException("String is empty or firstindexlastindex is incorrect");
        }
    }

}
