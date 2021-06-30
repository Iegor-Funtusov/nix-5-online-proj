package FileType;

public enum fileType {
    DATES("src/main/resources/examples/dates/input.txt"),
    NAMES("src/main/resources/examples/names/input.txt"),
    GRAPHS_INPUT("src/main/resources/examples/graphs/input.txt"),
    GRAPHS_OUTPUT("src/main/resources/examples/graphs/output.txt"),
    GRAPHS_EX2_INPUT("src/main/resources/examples/graphs/input2.txt"),
    GRAPHS_EX2_OUTPUT("src/main/resources/examples/graphs/output2.txt");

    private final String path;

    fileType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
