package ua.com.ionio.file;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateFile {

    public void init() {
        List<String[]> csvAuthorHeader = new ArrayList<>();
        String[] headers = {"id", "firstName", "lastName", "Author of", "visible"};
        csvAuthorHeader.add(headers);
        try (CSVWriter csvWriter = new CSVWriter(
                new FileWriter(FileType.CSV_AUTHOR.getPath(),true))) {
            csvWriter.writeAll(csvAuthorHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String[]> csvBookHeader = new ArrayList<>();
        String[] headers_ = {"id", "title", "Written by", "visible"};
        csvBookHeader.add(headers_);
        try (CSVWriter csvWriter = new CSVWriter(
                new FileWriter(FileType.CSV_BOOKS.getPath(),true))) {
            csvWriter.writeAll(csvBookHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        File authors = new File(FileType.CSV_AUTHOR.getPath());
        authors.delete();
        File books = new File(FileType.CSV_BOOKS.getPath());
        books.delete();
        File logs = new File("logs");
        recursiveDelete(logs);
    }

    public static void recursiveDelete(File file) {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursiveDelete(f);
            }
        }
        file.delete();
       // System.out.println("Удаленный файл или папка: " + file.getAbsolutePath());
    }
}
