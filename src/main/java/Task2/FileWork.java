package Task2;

import Configs.FilesPaths;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileWork {
    public static List<String> readFromFile() throws IOException {
        List<String> data;
        Path path = Paths.get(FilesPaths.TASK_2_INPUT.getPath());
        data = Files.readAllLines(path);
        return data;
    }

    public static void writeToFile(List<String> data){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FilesPaths.TASK_2_OUTPUT.getPath()))){
            for (String item : data) {
                writer.write(item + "\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
