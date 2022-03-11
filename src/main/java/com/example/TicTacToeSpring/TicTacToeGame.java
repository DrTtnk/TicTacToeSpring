package com.example.TicTacToeSpring;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class TicTacToeGame {
    public Cell[][] board;

    public Player player;

    public TicTacToeGame() {
        player = Player.X;
        board = new Cell[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = Cell.Empty;
    }

    /**
     * Constructs a new TictacToeGame from its Entity representation
     *
     * @param move the move as TickTacToeMoveEntity
     */
    public TicTacToeGame(TicTacToeMoveEntity move) {
        this.player = move.player;
        this.board = BoardSerializer.deserialize(move.serializedBoard);
    }

    /**
     * @return the winner of the game, if any
     */
    public Optional<Player> getWinner() {
        final var b = board;
        Function<Cell, Optional<Player>> winner = cell -> Optional.of(cell == Cell.X ? Player.X : Player.O);


        // check rows
        if (b[0][0] == b[0][1] && b[0][0] == b[0][2] && b[0][0] != Cell.Empty) return winner.apply(b[0][0]);
        if (b[1][0] == b[1][1] && b[1][0] == b[1][2] && b[1][0] != Cell.Empty) return winner.apply(b[1][0]);
        if (b[2][0] == b[2][1] && b[2][0] == b[2][2] && b[2][0] != Cell.Empty) return winner.apply(b[2][0]);
        // check columns
        if (b[0][0] == b[1][0] && b[0][0] == b[2][0] && b[0][0] != Cell.Empty) return winner.apply(b[0][0]);
        if (b[0][1] == b[1][1] && b[0][1] == b[2][1] && b[0][1] != Cell.Empty) return winner.apply(b[0][1]);
        if (b[0][2] == b[1][2] && b[0][2] == b[2][2] && b[0][2] != Cell.Empty) return winner.apply(b[0][2]);
        // check diagonals
        if (b[0][0] == b[1][1] && b[0][0] == b[2][2] && b[0][0] != Cell.Empty) return winner.apply(b[0][0]);
        if (b[0][2] == b[1][1] && b[0][2] == b[2][0] && b[0][2] != Cell.Empty) return winner.apply(b[0][2]);
        return Optional.empty();
    }

    /**
     * @return true if the game is a draw
     */
    public boolean isDraw() {
        return Arrays.stream(board).flatMap(Arrays::stream).allMatch(cell -> cell != Cell.Empty);
    }

    /**
     * @return true if the game is over
     */
    public boolean isGameOver() {
        return getWinner().isPresent() || isDraw();
    }

    /**
     * Updates the board with the move of the current player
     *
     * @param i row
     * @param j column
     */
    public void makeMove(int i, int j) {
        board[i][j] = player == Player.X ? Cell.X : Cell.O;
        player = player == Player.X ? Player.O : Player.X;
    }

    /**
     * @return true if the move is valid
     */
    public boolean isMoveValid(int i, int j) {
        return board[i][j] == Cell.Empty;
    }
}
