package Configs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PreStartAppConfig {
    private static final String csvPattern = ".csv";

    public static void configureApplication(){
        Path pathToBooksCSV = Paths.get(PathConfigs.BOOKS_FILE.getPath());
        Path pathToAuthorsCSV = Paths.get(PathConfigs.AUTHORS_FILE.getPath());

        if(!pathToBooksCSV.toString().contains(csvPattern)){
            throw new RuntimeException("Books file exception");
        }
        
        if(!pathToAuthorsCSV.toString().contains(csvPattern)){
            throw new RuntimeException("Authors file exception");
        }

        try {
            Files.deleteIfExists(pathToBooksCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.deleteIfExists(pathToAuthorsCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
