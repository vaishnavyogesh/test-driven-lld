package in.ox;

import in.ox.exceptions.IllegalMoveException;

public class TicTacToe {
    public static final int DEFAULT_BOARD_SIZE = 3;
    private final char playerA;
    private final char playerB;
    private char playerToMove;
    private char winner;
    private Board board;
    private Status status;

    public TicTacToe(char playerA, char playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        playerToMove = playerA;
        //TODO: does default size makes sense or should it be explicit via constructor?
        board = new Board(DEFAULT_BOARD_SIZE);
        status = Status.NOT_STARTED;
    }

    private boolean isValidPlayer(char player) {
        return (player == playerA || player == playerB) && player == playerToMove;
    }

    private void turn() {
        playerToMove = playerToMove == playerA ? playerB : playerA;
    }

    public Status play(int row, int col, char player) {
        //TODO: should we check whether the board should be reset before playing a new game?
        if (!isValidPlayer(player) || board.filled(row, col)) {
            throw new IllegalMoveException(); //TODO: should exception be more specific
        }
        board.mark(row, col, player);
        if (board.hasUniformRow() || board.hasUniformColumn() || board.hasUniformDiagonal()) {
            winner = player;
            return status = Status.WON;
        }
        if (board.completed()) {
            return status = Status.DRAW;
        }
        turn();
        return status = Status.IN_PROGRESS;
    }

    public Status status() {
        return status;
    }

    public char winner() {
        return this.winner;
    }

    public enum Status {NOT_STARTED, IN_PROGRESS, WON, DRAW}
}
