package ua.com.nkrasnovoronka.task1;

import ua.com.nkrasnovoronka.util.FileUtil;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DateFormatCheck {

    private static final String DATE_PATTERN_1 = "^\\d{4}\\/\\d{2}\\/\\d{2}$";
    private static final String DATE_PATTERN_2 = "^\\d{2}\\/\\d{2}\\/\\d{4}$";
    private static final String DATE_PATTERN_3 = "^\\d{2}-\\d{2}-\\d{4}$";

    private static final String OUTPUT_FORMAT = "yyyyMMdd";

    private static final String DATE_FORMAT_1 = "yyyy/MM/dd";
    private static final String DATE_FORMAT_2 = "dd/MM/yyyy";
    private static final String DATE_FORMAT_3 = "MM-dd-yyyy";

    public void writeValidDatesToFile(String inputFile, String outputFile) {
        List<String> collect = FileUtil.readFile(inputFile).stream()
                .map(this::formatDate)
                .collect(Collectors.toList());
        FileUtil.writeToFile(collect, outputFile);
    }

    private String formatDate(String str) {
        Map<Pattern, DateTimeFormatter> dateFormats = Map.of(
                Pattern.compile(DATE_PATTERN_1), DateTimeFormatter.ofPattern(DATE_FORMAT_1),
                Pattern.compile(DATE_PATTERN_2), DateTimeFormatter.ofPattern(DATE_FORMAT_2),
                Pattern.compile(DATE_PATTERN_3), DateTimeFormatter.ofPattern(DATE_FORMAT_3)
        );
        return dateFormats.keySet().stream()
                .filter(pattern -> pattern.matcher(str).matches())
                .map(dateFormats::get)
                .map(dtf -> dtf.parse(str))
                .map(df -> DateTimeFormatter.ofPattern(OUTPUT_FORMAT).format(df))
                .findFirst()
                .orElse("<not valid>");
    }
}
