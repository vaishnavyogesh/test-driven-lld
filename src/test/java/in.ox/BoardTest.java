package in.ox;

import in.ox.exceptions.OutOfBoardException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private static Stream<Arguments> provideUniformRows() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {0, 0, 'X'},
                                {0, 1, 'X'},
                                {0, 2, 'X'}
                        },
                        true
                ),
                Arguments.of(
                        new int[][]{
                                {0, 0, 'X'},
                                {0, 1, 'Y'},
                                {0, 2, 'X'}
                        },
                        false
                )
        );
    }

    private static Stream<Arguments> provideUniformColumns() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {0, 0, 'X'},
                                {1, 0, 'X'},
                                {2, 0, 'X'}
                        },
                        true
                ),
                Arguments.of(
                        new int[][]{
                                {0, 0, 'X'},
                                {1, 0, 'Y'},
                                {2, 0, 'X'}
                        },
                        false
                )
        );
    }

    private static Stream<Arguments> provideUniformDiagonals() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {0, 0, 'X'},
                                {1, 1, 'X'},
                                {2, 2, 'X'}
                        },
                        true
                ),
                Arguments.of(
                        new int[][]{
                                {0, 2, 'X'},
                                {1, 1, 'X'},
                                {2, 0, 'X'}
                        },
                        true
                ),
                Arguments.of(
                        new int[][]{
                                {0, 2, 'X'},
                                {1, 1, 'Y'},
                                {2, 0, 'X'}
                        },
                        false
                )
        );
    }

    private static Stream<Arguments> provideCompleteness() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {0, 0, 'X'}, {0, 1, 'Y'}, {0, 2, 'X'},
                                {1, 0, 'Y'}, {1, 1, 'X'}, {1, 2, 'X'},
                                {2, 0, 'X'}, {2, 1, 'X'}, {2, 2, 'Y'}
                        },
                        true
                ),
                Arguments.of(
                        new int[][]{
                                {0, 2, 'X'},
                                {1, 1, 'X'},
                                {2, 0, 'X'}
                        },
                        false
                )
        );
    }

    @Test
    void itInitiallyMarksCellsEmpty() throws OutOfBoardException {
        Board board = new Board(3);
        assertTrue(board.empty(0, 0));
    }

    @Test
    void itMarksCellWithGivenSymbol() throws OutOfBoardException {
        Board board = new Board(3);
        board.mark(0, 0, 'X');
        assertEquals('X', board.cell(0, 0));
    }

    @Test
    void itThrowsOutOfBoardExceptionWhenMarkingNonExistentCell() {
        Board board = new Board(3);
        assertThrows(OutOfBoardException.class, () -> board.mark(10, 10, 'X'));
    }

    @ParameterizedTest
    @MethodSource(value = "provideUniformRows")
    void itDeterminesUniformRow(int[][] moves, boolean uniform) throws OutOfBoardException {
        Board board = new Board(3);
        for (int[] move : moves) {
            board.mark(move[0], move[1], (char) move[2]);
        }
        assertEquals(uniform, board.hasUniformRow());
    }

    @Test
    void itDoesNotConsiderEmptyBoardForUniformity() throws OutOfBoardException {
        Board board = new Board(3);
        assertFalse(board.hasUniformRow());
        assertFalse(board.hasUniformColumn());
        assertFalse(board.hasUniformDiagonal());
    }

    @ParameterizedTest
    @MethodSource(value = "provideUniformColumns")
    void itDeterminesUniformColumn(int[][] moves, boolean uniform) throws OutOfBoardException {
        Board board = new Board(3);
        for (int[] move : moves) {
            board.mark(move[0], move[1], (char) move[2]);
        }
        assertEquals(uniform, board.hasUniformColumn());
    }

    @ParameterizedTest
    @MethodSource(value = "provideUniformDiagonals")
    void itDeterminesUniformDiagonal(int[][] moves, boolean uniform) throws OutOfBoardException {
        Board board = new Board(3);
        for (int[] move : moves) {
            board.mark(move[0], move[1], (char) move[2]);
        }
        assertEquals(uniform, board.hasUniformDiagonal());
    }

    @ParameterizedTest
    @MethodSource(value = "provideCompleteness")
    void itDeterminesCompleteness(int[][] moves, boolean complete) throws OutOfBoardException {
        Board board = new Board(3);
        for (int[] move : moves) {
            board.mark(move[0], move[1], (char) move[2]);
        }
        assertEquals(complete, board.completed());
    }

    @Test
    void itDeterminesFilledCell() {
        Board board = new Board(3);
        board.mark(0, 0, 'X');
        assertTrue(board.filled(0, 0));
        assertFalse(board.filled(0, 1));
    }
}