package in.ox;

import in.ox.exceptions.IllegalMoveException;
import in.ox.exceptions.OutOfBoardException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicTacToeTest {

    @Test
    void itThrowsIllegalMoveExceptionWhenMarkingOccupiedCell() {
        TicTacToe ox = new TicTacToe('O', 'X');
        ox.play(0, 0, 'O');
        assertThrows(IllegalMoveException.class, () -> ox.play(0, 0, 'X'));
    }

    @Test
    void itThrowsOutOfBoardExceptionWhenMarkingNonExistentCell() {
        TicTacToe ox = new TicTacToe('O', 'X');
        assertThrows(OutOfBoardException.class, () -> ox.play(-1, -1, 'O'));
        assertThrows(OutOfBoardException.class, () -> ox.play(100, 100, 'O'));
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'B', 'C'})
    void itThrowsIllegalMoveExceptionOnPlayerWithUnmentionedSymbols(char invalidSymbol) {
        TicTacToe ox = new TicTacToe('O', 'X');
        assertThrows(IllegalMoveException.class, () -> ox.play(0, 0, invalidSymbol));
    }

    @Test
    void itThrowsIllegalMoveExceptionWhenPlayerPlaysTwice() {
        TicTacToe ox = new TicTacToe('O', 'X');
        ox.play(0, 0, 'O');
        assertThrows(IllegalMoveException.class, () -> ox.play(1, 1, 'O'));
    }

    @Test
    void itHasNotStartedStatusInitially() {
        TicTacToe ox = new TicTacToe('O', 'X');
        assertEquals(TicTacToe.Status.NOT_STARTED, ox.status());
    }

    @Test
    void itReturnsInProgressStatusWhenGameShouldBeContinued() {
        TicTacToe ox = new TicTacToe('O', 'X');
        TicTacToe.Status status = ox.play(0, 0, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
    }


    @Test
    void itDeclaresWinnerWithUniformRow() {
        TicTacToe ox = new TicTacToe('O', 'X');
        TicTacToe.Status status = ox.play(0, 0, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 1, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(2, 0, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 2, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(0, 2, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 0, 'X');
        assertEquals(TicTacToe.Status.WON, status);
        assertEquals('X', ox.winner());
    }

    @Test
    void itDeclaresWinnerWithUniformColumn() {
        TicTacToe ox = new TicTacToe('X', 'O');
        TicTacToe.Status status = ox.play(0, 0, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 1, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 0, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(2, 2, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(2, 0, 'X');
        assertEquals(TicTacToe.Status.WON, status);
        assertEquals('X', ox.winner());
    }

    @Test
    void itDeclaresWinnerWithUniformDiagonal() {
        TicTacToe ox = new TicTacToe('X', 'O');
        TicTacToe.Status status = ox.play(0, 0, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 0, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 1, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(2, 1, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(2, 2, 'X');
        assertEquals(TicTacToe.Status.WON, status);
        assertEquals('X', ox.winner());

        ox = new TicTacToe('O', 'X');
        status = ox.play(0, 2, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 0, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 1, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(0, 0, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(2, 0, 'O');
        assertEquals(TicTacToe.Status.WON, status);
        assertEquals('O', ox.winner());
    }

    @Test
    void itDeclaresDrawWhenBoardIsCompleteWithoutWinner() {
        TicTacToe ox = new TicTacToe('O', 'X');
        TicTacToe.Status status = ox.play(0, 0, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 1, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(0, 1, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(0, 2, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(2, 0, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 0, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(1, 2, 'O');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(2, 2, 'X');
        assertEquals(TicTacToe.Status.IN_PROGRESS, status);
        status = ox.play(2, 1, 'O');
        assertEquals(TicTacToe.Status.DRAW, status);
    }
}
