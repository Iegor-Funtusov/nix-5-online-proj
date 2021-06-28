package csvfile;

import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import type.FileType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public void createFiles() {
        loggerInfo.info("Начало создания CSV-файлов:");
        List<String[]> csvDataAuthor = new ArrayList<>();
        String[] headerAuthors = {"AuthorId", "FirstName", "LastName", "Books", "Visible"};
        csvDataAuthor.add(headerAuthors);
        try (CSVWriter writer = new CSVWriter(new FileWriter(FileType.AUTHORS.getPATH(), true))) {
            writer.writeAll(csvDataAuthor);
        } catch (IOException e) {
           // e.printStackTrace();
            System.out.println(e + "Ошибка записи в файл!");
            loggerError.error("Ошибка записи в файл!");
        }

        List<String[]> csvDataBooks = new ArrayList<>();
        String[] headerBooks = {"BookId", "Title", "Authors", "Visible"};
        csvDataBooks.add(headerBooks);
        try (CSVWriter writer = new CSVWriter(new FileWriter(FileType.BOOKS.getPATH(),true))) {
            writer.writeAll(csvDataBooks);
        } catch (IOException e) {
           // e.printStackTrace();
            System.out.println(e + "Ошибка записи в файл!");
            loggerError.error("Ошибка записи в файл!");
        }
        loggerInfo.info("Окончание создания файлов");
    }

    public String allIds(List<String> ids) {
        StringBuilder sb = new StringBuilder();
        if (ids != null) {
            for (String id : ids) {
                sb.append(id).append("|");
            }
        }
        return sb.toString();
    }

    public List<String> parseId(String str) {
        List<String> list = new ArrayList<>();
        String[] arrayIds = str.split("\\|");
        for (String id : arrayIds) {
            if (id != null) {
                list.add(id);
            }
        }
        return list;
    }
}
