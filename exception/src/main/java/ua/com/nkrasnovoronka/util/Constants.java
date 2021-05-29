package ua.com.nkrasnovoronka.util;

import java.util.List;
import java.util.Map;

public final class Constants {

    private Constants() {
    }

    public static final String PATTERN_1 = "dd/mm/yy";
    public static final String PATTERN_2 = "m/d/yyyy";
    public static final String PATTERN_3 = "mmm-d-yy";
    public static final String PATTERN_4 = "dd-mmmm-yyyy";

    private static final String FORMANT_1 = "^(?<day>0[1-9]|[12]\\d|3[01]|)\\/(?<month>0[1-9]|1[0-2]|)\\/(?<year>\\d{2}|)$";
    private static final String FORMANT_2 = "^(?<month>[1-9]|1[0-2]|)\\/(?<day>[1-9]|[12]\\d|3[01]|)\\/(?<year>\\d{4}|)$";
    private static final String FORMANT_3 = "^(?<month>jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec|)\\-(?<day>[1-9]|[12]\\d|3[01]|)\\-(?<year>\\d{2}|)$";
    private static final String FORMANT_4 = "^(?<day>0[1-9]|[12]\\d|3[01]|)\\-(?<month>january|february|march|april|may|june|july|august|september|october|november|december|)\\-(?<year>\\d{4}|)$";

    public static final Map<Integer, String> dateParserMap = initMap();
    public static final List<String> datePatterns = initList();

    private static List<String> initList() {
        return List.of(PATTERN_1, PATTERN_2, PATTERN_3, PATTERN_4);
    }

    private static Map<Integer, String> initMap() {
        return Map.of(1, FORMANT_1, 2, FORMANT_2, 3, FORMANT_3, 4, FORMANT_4);
    }
}
