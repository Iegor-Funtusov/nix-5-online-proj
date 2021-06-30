package Configs;

public enum FilesPaths {
    TASK_1_INPUT("src/main/resources/inputTask1.txt"),
    TASK_1_OUTPUT("src/main/resources/outputTask1.txt"),

    TASK_2_INPUT("src/main/resources/inputTask2.txt"),
    TASK_2_OUTPUT("src/main/resources/outputTask2.txt"),

    TASK_3_INPUT("src/main/resources/inputTask3.txt"),
    TASK_3_OUTPUT("src/main/resources/outputTask3.txt");

    private final String path;

    FilesPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
