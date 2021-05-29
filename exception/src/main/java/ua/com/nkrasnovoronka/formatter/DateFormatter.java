package ua.com.nkrasnovoronka.formatter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatter {
    private final Map<String, String> dateFormats;

    public DateFormatter() {
        dateFormats = initDateFormats();
    }

    private Map<String, String> initDateFormats() {
        final Map<String, String> dateFormats;
        dateFormats = new HashMap<>();
        dateFormats.put("dd/mm/yy", "\\d{2}");
        dateFormats.put("m/d/yyyy", "");
        dateFormats.put("mmm-d-yy", "");
        dateFormats.put("dd-mmm-yyyy", "");
        return dateFormats;
    }

    public void formatStringToDate(String parser, String input){
        String regexp = dateFormats.get(parser);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }

}
