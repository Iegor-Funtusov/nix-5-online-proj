package FileType;

public enum fileType {
    DATES("module_2/src/main/resources/examples/dates/input.txt"),
    NAMES("module_2/src/main/resources/examples/names/input.txt"),
    GRAPHS_INPUT("module_2/src/main/resources/examples/graphs/input.txt"),
    GRAPHS_OUTPUT("module_2/src/main/resources/examples/graphs/output.txt"),
    GRAPHS_EX2_INPUT("module_2/src/main/resources/examples/graphs/input2.txt"),
    GRAPHS_EX2_OUTPUT("module_2/src/main/resources/examples/graphs/output2.txt");

    private final String path;

    fileType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
