package ua.com.alevel.app.csv;

import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDb {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");

    public void initFiles() {
        loggerInfo.info("Start init txt files");
        List<String[]> csvDataAuthor = new ArrayList<>();
        String[] headerA = {"ID", "FirstName", "LastName", "Books", "Visible"};
        csvDataAuthor.add(headerA);
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("authors.txt",true))) {
            csvWriter.writeAll(csvDataAuthor);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String[]> csvDataBooks = new ArrayList<>();
        String[] headerB = {"ID", "Title", "Authors", "Visible"};
        csvDataBooks.add(headerB);
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("books.txt",true))) {
            csvWriter.writeAll(csvDataBooks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loggerInfo.info("End of init txt files");
    }

    public String allId(List<String> id) {
        StringBuilder ids = new StringBuilder();
        if(id!=null){
            for (String i : id) {
                ids.append(i).append("|");
            }
        }
        return ids.toString();
    }

    public List<String> parseId(String str){
        List<String> fin = new ArrayList<>();
        String[] masId = str.split("\\|");
        for (String s : masId) {
            if (s!=null){
                fin.add(s);
            }
        }
        return fin;
    }
}
