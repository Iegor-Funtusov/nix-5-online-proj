package ua.com.nkrasnovoronka.unit3.chesspiace;

public enum Color {
    WHITE("W"), BLACK("B");
    private final String name;
    Color(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
