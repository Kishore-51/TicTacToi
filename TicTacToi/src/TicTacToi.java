
import java.util.*;

public class TicTacToi {
    public static void main(String[] args) {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        
        char currentPlayer = 'X';
        boolean gameOver = false;
        int moves = 0;

        while (!gameOver) {
            displayBoard(board);
            System.out.println("Player " + currentPlayer + "'s turn. Enter row and column (e.g., 1 2): ");
            
            int row, col;
            Scanner scanner = new Scanner(System.in);

            try {
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;

                if (isValidMove(board, row, col)) {
                    board[row][col] = currentPlayer;
                    moves++;
                    
                    if (isWinner(board, currentPlayer)) {
                        displayBoard(board);
                        System.out.println("Player " + currentPlayer + " wins!");
                        gameOver = true;
                    } else if (moves == 9) {
                        displayBoard(board);
                        System.out.println("It's a tie!");
                        gameOver = true;
                    } else {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter row and column numbers.");
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    public static void displayBoard(char[][] board) {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  -----");
            }
        }
        System.out.println();
    }

    public static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    public static boolean isWinner(char[][] board, char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Row
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Column
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Diagonal (top-left to bottom-right)
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Diagonal (top-right to bottom-left)
        }
        return false;
    }
}
