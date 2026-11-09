import java.util.Scanner;

public class TicTacToe {

    // Class-level variables and constants
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];
    private static final int MOVES_FOR_WIN = 5;
    private static final int MOVES_FOR_TIE = 9;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;

        do {
            // 1. Clear the board and set starting player to X
            clearBoard();
            String currentPlayer = "X";
            int moveCount = 0;
            boolean gameOver = false;

            while (!gameOver) {
                // 2. Display the board
                display();
                System.out.println("Player " + currentPlayer + "'s turn.");

                // 3. Get coordinates (1-3) using SafeInput
                int rowMove = SafeInput.getRangedInt(in, "Enter row move", 1, 3);
                int colMove = SafeInput.getRangedInt(in, "Enter column move", 1, 3);

                // 4. Convert to 0-based array indices
                int row = rowMove - 1;
                int col = colMove - 1;

                // 5. Loop until the move is valid (space is empty)
                while (!isValidMove(row, col)) {
                    System.out.println("That space is already taken or invalid. Try again.");
                    rowMove = SafeInput.getRangedInt(in, "Enter row move", 1, 3);
                    colMove = SafeInput.getRangedInt(in, "Enter column move", 1, 3);
                    row = rowMove - 1;
                    col = colMove - 1;
                }

                // 6. Record the valid move
                board[row][col] = currentPlayer;
                moveCount++;

                // 7. Check for a win or tie (only possible after enough moves)
                if (moveCount >= MOVES_FOR_WIN) {
                    if (isWin(currentPlayer)) {
                        display();
                        System.out.println("Player " + currentPlayer + " wins!");
                        gameOver = true;
                        break;
                    }
                }

                if (moveCount >= MOVES_FOR_TIE) {
                    if (isTie()) {
                        display();
                        System.out.println("It's a tie!");
                        gameOver = true;
                        break;
                    }
                }

                // 8. Toggle player
                if (currentPlayer.equals("X")) {
                    currentPlayer = "O";
                } else {
                    currentPlayer = "X";
                }
            }

            // Prompt to play again using SafeInput
            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again");

        } while (playAgain);

        System.out.println("Thanks for playing Tic Tac Toe!");
        in.close();
    }

    // --- Helper Methods ---

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("\n  1   2   3");
        for (int i = 0; i < ROWS; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < COLS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < ROWS - 1) {
                System.out.println(" ---+---+---");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        // Returns true if the cell is within bounds and contains a space
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            return board[row][col].equals(" ");
        }
        return false;
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {
        // Check top-left to bottom-right diagonal OR top-right to bottom-left diagonal
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        // Check if all spaces are filled
        boolean allFilled = true;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    allFilled = false;
                    break;
                }
            }
        }
        if (allFilled) {
            return true;
        }

        // Check if all 8 win vectors (3 rows, 3 cols, 2 diagonals) are blocked by both X and O
        // (Optional advanced check or covered if board fills up)
        return false;
    }
}