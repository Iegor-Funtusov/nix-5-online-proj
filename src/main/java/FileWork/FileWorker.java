package FileWork;
import Validation.Validator;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

public class FileWorker {
    public static void writeToFile(String path, List<String[]> csvData){
        try {
            Validator.isExistFile(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try(CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<String[]> readFromFile(String path){
        try {
            Validator.isExistFile(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try(CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> res = reader.readAll();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Read from file exception");
    }
}
