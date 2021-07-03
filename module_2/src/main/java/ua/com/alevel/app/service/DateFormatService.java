package ua.com.alevel.app.service;

import ua.com.alevel.app.util.FileUtils;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DateFormatService {

    private static final String[] patterns;
    private static final String[] formats;

    static {
        patterns = new String[]{"^\\d{4}/\\d{2}/\\d{2}$", "^\\d{2}/\\d{2}/\\d{4}$", "^\\d{2}-\\d{2}-\\d{4}$"};
        formats = new String[]{"yyyy/MM/dd", "dd/MM/yyyy", "MM-dd-yyyy", "yyyyMMdd"};
    }

    private String formatDate(String str) {
        Map<Pattern, DateTimeFormatter> dateFormats = Map.of(
                Pattern.compile(patterns[0]), DateTimeFormatter.ofPattern(formats[0]),
                Pattern.compile(patterns[1]), DateTimeFormatter.ofPattern(formats[1]),
                Pattern.compile(patterns[2]), DateTimeFormatter.ofPattern(formats[2])
        );
        return dateFormats.keySet().stream()
                .filter(p -> p.matcher(str).matches()).map(dateFormats::get)
                .map(formatter -> formatter.parse(str)).map(format -> DateTimeFormatter.ofPattern(formats[3]).format(format))
                .findAny().orElse(str + " (ignored, wrong format)");
    }

    public void formatFileDates(String inFile, String outFile) {
        List<String> collect = FileUtils.readFile(inFile).stream().map(this::formatDate).collect(Collectors.toList());
        FileUtils.writeFile(collect, outFile);
    }
}
