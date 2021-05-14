package ua.com.nix.board;

import lombok.Getter;

@Getter
public class Position {

    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
