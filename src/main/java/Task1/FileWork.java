package Task1;

import Configs.FilesPaths;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileWork {
    public static List<String> readFromFile() throws IOException {
        List<String> data;
        Path path = Paths.get(FilesPaths.TASK_1_INPUT.getPath());
        data = Files.readAllLines(path);
        return data;
    }


    public static void writeToFile(List<String> data){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FilesPaths.TASK_1_OUTPUT.getPath()))){
            for (String item : data) {
                writer.write(item + "\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
