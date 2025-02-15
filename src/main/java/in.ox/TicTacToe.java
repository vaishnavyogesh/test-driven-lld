package in.ox;

import in.ox.exceptions.OutOfBoardException;

public class TicTacToe {

    public static final int DEFAULT_BOARD_SIZE = 3;
    private Board board;

    public TicTacToe() {
        board = new Board(DEFAULT_BOARD_SIZE);
    }

    public void play(int row, int col, char symbol) throws OutOfBoardException {
        board.mark(row, col, symbol);
    }

    public boolean hasWinner() throws OutOfBoardException {
        return board.hasUniformRow();
    }
}
