package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private final static int cellsAmount = 9;
    private final static int boardSideSize = 3;

    private static int charsAmount = 0;

    private static final Scanner scanner = new Scanner(System.in);
    private static final char[][] board = new char[boardSideSize][boardSideSize];
    private static final char[] gameChars = { 'X', 'O' };

    private static void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < boardSideSize; ++i) {
            System.out.print("| ");
            for (int j = 0; j < boardSideSize; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static boolean isWinner(char gameChar) {
        for (int i = 0; i < boardSideSize; ++i) {
            if (
                board[i][0] == gameChar && board[i][1] == gameChar && board[i][2] == gameChar ||
                    board[0][i] == gameChar && board[1][i] == gameChar && board[2][i] == gameChar
            ) {
                return true;
            }
        }

        return board[0][0] == gameChar && board[1][1] == gameChar && board[2][2] == gameChar ||
            board[0][2] == gameChar && board[1][1] == gameChar && board[2][0] == gameChar;
    }

    private static boolean checkWinner() {
        String winnerMessage = " wins";
        String drawMessage = "Draw";

        for (char gameChar : gameChars) {
            boolean isWinner = isWinner(gameChar);

            if (isWinner) {
                System.out.println(gameChar + winnerMessage);
                return true;
            }

            if (charsAmount == cellsAmount) {
                System.out.println(drawMessage);
                return true;
            }
        }

        return false;
    }

    private static void makeMove(char curCh) {
        for (;;) {
            int x, y;

            try {
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } catch (InputMismatchException exception) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }

            if (x < 0 || x >= boardSideSize || y < 0 || y >= boardSideSize) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (board[x][y] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                board[x][y] = curCh;
                break;
            }
        }

        ++charsAmount;
    }

    public static void main(String[] args) {
        for (int i = 0; i < boardSideSize; ++i) {
            for (int j = 0; j < boardSideSize; ++j) {
                board[i][j] = '_';
            }
        }

        printBoard();
        for (int i = 0; !checkWinner(); ++i) {
            makeMove(gameChars[i % 2]);
            printBoard();
        }

        scanner.close();
    }
}
