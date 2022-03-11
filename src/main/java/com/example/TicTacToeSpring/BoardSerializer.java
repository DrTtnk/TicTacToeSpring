package com.example.TicTacToeSpring;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BoardSerializer {

    // This below in green is a javadoc, it is a comment that will be displayed in the javadoc, try it by hovering over the
    // method and see the magic of IntelliJ showing you nice looking documentations.

    /**
     * Convert a string representation of a board to a 2D array of cells.
     *
     * @param serializedBoard String representation of the board
     * @return a 2D array of Cell
     */
    public static Cell[][] deserialize(String serializedBoard) {
        return Arrays.stream(serializedBoard.split(";"))
                .map(r -> Arrays.stream(r.split(",")).map(Cell::valueOf).toArray(Cell[]::new))
                .toArray(Cell[][]::new);
    }

    /**
     * Convert a 2D array of cells to a string representation of the board.
     *
     * @param board 2D array of cells
     * @return String representation of the board
     */
    public static String serialize(Cell[][] board) {
        return Arrays.stream(board)
                .map(r -> Arrays.stream(r).map(Cell::toString).collect(Collectors.joining(",")))
                .collect(Collectors.joining(";"));
    }
}
