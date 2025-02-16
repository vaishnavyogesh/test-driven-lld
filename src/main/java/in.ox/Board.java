package in.ox;

import in.ox.exceptions.OutOfBoardException;

public class Board {
    private final int size;
    private final char[][] cells;
    private int occupiedCells;

    public Board(int size) {
        this.size = size;
        this.cells = new char[size][size];
    }


    //TODO: does symbol or symbolAt makes more sense than cell?
    // as cell might infer to Cell instance
    public char cell(int row, int col) throws OutOfBoardException {
        if (outside(row, col)) {
            throw new OutOfBoardException();
        }
        return cells[row][col];
    }

    private boolean outside(int row, int col) {
        return row < 0 || row >= size || col < 0 || col >= size;
    }

    public void mark(int row, int col, char symbol) throws OutOfBoardException {
        if (outside(row, col)) {
            throw new OutOfBoardException();
        }
        cells[row][col] = symbol;
        occupiedCells++;
    }

    public boolean empty(int row, int col) throws OutOfBoardException {
        if (outside(row, col)) {
            throw new OutOfBoardException();
        }
        return cells[row][col] == Character.MIN_VALUE;
    }

    public boolean hasUniformRow() throws OutOfBoardException {
        for (int row = 0; row < size; ++row) {
            if (empty(row, 0)) {
                continue;
            }
            boolean uniform = true;
            for (int col = 0; col < size; ++col) {
                if (cells[row][col] != cells[row][0]) {
                    uniform = false;
                    break;
                }
            }
            if (uniform) {
                return true;
            }
        }
        return false;
    }

    public boolean hasUniformColumn() throws OutOfBoardException {
        for (int col = 0; col < size; ++col) {
            if (empty(0, col)) {
                continue;
            }
            boolean uniform = true;
            for (int row = 0; row < size; ++row) {
                if (cells[row][col] != cells[0][col]) {
                    uniform = false;
                    break;
                }
            }
            if (uniform) {
                return true;
            }
        }
        return false;
    }

    private boolean hasUniformLeftDiagonal() throws OutOfBoardException {
        if (empty(0, 0)) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            if (cells[i][i] != cells[0][0]) {
                return false;
            }
        }
        return true;
    }

    private boolean hasUniformRightDiagonal() throws OutOfBoardException {
        if (empty(0, size - 1)) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            if (cells[i][size - i - 1] != cells[0][size - 1]) {
                return false;
            }
        }
        return true;
    }

    //TODO: would it more readable to name hasUniformLeftToRightDiagonal() and vice versa?
    public boolean hasUniformDiagonal() throws OutOfBoardException {
        return hasUniformLeftDiagonal() || hasUniformRightDiagonal();
    }

    public boolean completed() {
        return occupiedCells == (size * size);
    }

    public boolean filled(int row, int col) {
        return !empty(row, col);
    }
}
