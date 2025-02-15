package in.ox;

import java.util.Scanner;

public class Engine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TicTacToe ticTacToe = new TicTacToe('X', 'O');

        TicTacToe.Status status = ticTacToe.status();

        do {
            System.out.println("Where would you like to play? (eg. 1 1 X): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            char player = scanner.next().charAt(0);
            status = ticTacToe.play(row - 1, col - 1, player);
        } while (status != TicTacToe.Status.WON && status != TicTacToe.Status.DRAW);

        System.out.println("Game Result: " + status);

        if (status == TicTacToe.Status.WON) {
            System.out.println("Winner: " + ticTacToe.winner());
        }

        scanner.close();
    }
}
