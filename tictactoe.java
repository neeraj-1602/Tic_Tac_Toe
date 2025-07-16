package Tic_Tac_Toe;
import java.util.Scanner;

public class tictactoe {
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        tictactoe game = new tictactoe();
        char[][] board = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}
        };

        printBoard(board);

        for (int i = 0; i < 9; i++) {
            System.out.println("Turn: " + (i % 2 == 0 ? "X" : "O"));
            int[] spot;

            if (i % 2 == 0) {
                spot = game.askUser(board);
                board[spot[0]][spot[1]] = 'X';
            } else {
                spot = game.askUser(board);
                board[spot[0]][spot[1]] = 'O';
            }

            printBoard(board);
            int result = checkWin(board);
            if (result == 3) {
                System.out.println("X wins");
                break;
            } else if (result == -3) {
                System.out.println("O wins");
                break;
            } else if (i == 8) {
                System.out.println("It's a tie!");
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println();
        for (char[] row : board) {
            System.out.print("\t");
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println("\n");
        }
    }

    public int[] askUser(char[][] board) {
        while (true) {
            System.out.println("Pick a row and column number: ");
            int row = scan.nextInt();
            int col = scan.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '_') {
                return new int[]{row, col};
            }
            System.out.println("Spot taken or invalid, try again.");
        }
    }

    public static int checkWin(char[][] board) {
        int rows = checkRows(board);
        if (Math.abs(rows) == 3) return rows;

        int cols = checkColumns(board);
        if (Math.abs(cols) == 3) return cols;

        int left = checkLeft(board);
        if (Math.abs(left) == 3) return left;

        int right = checkRight(board);
        if (Math.abs(right) == 3) return right;

        return 0;
    }

    public static int checkRows(char[][] board) {
        for (char[] row : board) {
            int count = 0;
            for (char cell : row) {
                if (cell == 'X') count++;
                else if (cell == 'O') count--;
            }
            if (Math.abs(count) == 3) return count;
        }
        return 0;
    }

    public static int checkColumns(char[][] board) {
        for (int col = 0; col < 3; col++) {
            int count = 0;
            for (int row = 0; row < 3; row++) {
                if (board[row][col] == 'X') count++;
                else if (board[row][col] == 'O') count--;
            }
            if (Math.abs(count) == 3) return count;
        }
        return 0;
    }

    public static int checkLeft(char[][] board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] == 'X') count++;
            else if (board[i][i] == 'O') count--;
        }
        return count;
    }

    public static int checkRight(char[][] board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][2 - i] == 'X') count++;
            else if (board[i][2 - i] == 'O') count--;
        }
        return count;
    }
}
