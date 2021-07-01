package task1;

import java.util.ArrayList;
import java.util.List;

public class ChangeDateFormat {

    String [] formats = new String[]
            {"\\d{4}/\\d{2}/\\d{2}", "\\d{2}/\\d{2}/\\d{4}", "\\d{2}-\\d{2}-\\d{4}"};

    public List<String> defineFormat(String st){

        String[] strs = st.trim().split("\\s+");
        List<String> output = new ArrayList<>();
        for (String str : strs) {
            for (int i = 0; i < 3; i++) {
                if (str.matches(formats[i])) {
                    if (changeformat(str, i + 1) != null) {
                        String res = changeformat(str, i + 1);
                        output.add(res);
                    }
                }
            }
        }
        return output;
    }

    public static String changeformat(String before, int matched_format){
        switch (matched_format){
            case 1: {
                String delimiter = "/";
                String [] substring;
                substring = before.split(delimiter);
                if(CalendarService.checkDate(
                        Integer.parseInt(substring[0]),
                        Integer.parseInt(substring[1]),
                        Integer.parseInt(substring[2]))){
                    return substring[0] + substring[1] + substring[2];
                }
                    break;
            }
            case 2: {
                String delimiter = "/";
                String [] substring;
                substring = before.split(delimiter);
                if(CalendarService.checkDate(
                        Integer.parseInt(substring[2]),
                        Integer.parseInt(substring[1]),
                        Integer.parseInt(substring[0]))){
                    return substring[2] + substring[1] + substring[0];
                }
                    break;
            }
            case 3: {
                String delimiter = "-";
                String [] substring;
                substring = before.split(delimiter);
                if(CalendarService.checkDate(
                        Integer.parseInt(substring[2]),
                        Integer.parseInt(substring[0]),
                        Integer.parseInt(substring[1]))){
                    return substring[2] + substring[0] + substring[1];
                }
                break;
            }
        }
        return null;
    }
}
