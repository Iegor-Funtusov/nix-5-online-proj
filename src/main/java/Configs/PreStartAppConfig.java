package Configs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PreStartAppConfig {
    public static void configureApp(){
        Path task1Input = Paths.get(FilesPaths.TASK_1_INPUT.getPath());
        Path task2Input = Paths.get(FilesPaths.TASK_2_INPUT.getPath());
        Path task3Input = Paths.get(FilesPaths.TASK_3_INPUT.getPath());

        Path task1Output = Paths.get(FilesPaths.TASK_1_OUTPUT.getPath());
        Path task2Output = Paths.get(FilesPaths.TASK_2_OUTPUT.getPath());
        Path task3Output = Paths.get(FilesPaths.TASK_3_OUTPUT.getPath());

        if(Files.notExists(task1Input) || Files.notExists(task2Input) || Files.notExists(task3Input)){
            throw new RuntimeException("Input files exception");
        }

        try {
            Files.deleteIfExists(task1Output);
            Files.deleteIfExists(task2Output);
            Files.deleteIfExists(task3Output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
