package game.gameUtils;

import game.gameObjects.Cell;
import game.gameObjects.Player;
import java.io.Serializable;

/**
 * This class manages the game board, including cell movements, merging, and checking game state.
 *
 * @author Maksym Kulynych
 */
public class BoardManager implements Serializable {
    private Cell[][] board;
    private static final int[][] DIRECTIONS = { {-1, 0}, {0, -1}, {0, 1}, {1, 0} };

    public BoardManager(int size) {
        board = new Cell[size][size];
        initBoard();
    }

    /**
     * This method initializes the game board by filling it with empty cells (value 0).
     */
    public void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell(0);
            }
        }
    }

    /**
     * This method moves the cells on the board to the left, merging cells with the same value and updating the player's score.
     *
     * @param player the player whose score will be updated based on amount of merged cells
     * @return true if any cells were moved, false otherwise
     */
    public boolean moveCellsLeft(Player player) {
        boolean moved = false;

        for (int i = 0; i < board.length; i++) {
            boolean merged = false;
            for (int j = 0; j < board[i].length; j++) {

                int k = j;

                while (k > 0 && board[i][k].getValue() != 0) {
                    if (board[i][k - 1].getValue() == 0) {
                        board[i][k - 1] = board[i][k];
                        board[i][k] = new Cell(0);
                        moved = true;
                    } else if (board[i][k - 1].compareTo(board[i][k]) == 0 && !merged) {
                        board[i][k - 1] = new Cell(board[i][k - 1].getValue() * 2);
                        board[i][k] = new Cell(0);

                        player.addScore(board[i][k - 1].getValue());

                        moved = true;
                        merged = true;
                    }
                    k--;
                }
            }
        }
        return moved;
    }

    /**
     * This method moves the cells on the board to the right, merging cells with the same value and updating the player's score.
     *
     * @param player the player whose score will be updated based on amount of merged cells
     * @return true if any cells were moved, false otherwise
     */
    public boolean moveCellsRight(Player player) {
        for (int i = 0; i < 2; i++) rotate90Degree();
        boolean moved = moveCellsLeft(player);
        for (int i = 0; i < 2; i++) rotate90Degree();

        return moved;
    }

    /**
     * This method moves the cells on the board up, merging cells with the same value and updating the player's score.
     *
     * @param player the player whose score will be updated based on amount of merged cells
     * @return true if any cells were moved, false otherwise
     */
    public boolean moveCellsUp(Player player) {
        for (int i = 0; i < 3; i++) rotate90Degree();
        boolean moved = moveCellsLeft(player);
        rotate90Degree();

        return moved;
    }

    /**
     * This method moves the cells on the board down, merging cells with the same value and updating the player's score.
     *
     * @param player the player whose score will be updated based on amount of merged cells
     * @return true if any cells were moved, false otherwise
     */
    public boolean moveCellsDown(Player player) {
        rotate90Degree();
        boolean moved = moveCellsLeft(player);
        for (int i = 0; i < 3; i++) rotate90Degree();

        return moved;
    }

    /**
     * This method rotates the game board 90 degrees clockwise.
     */
    public void rotate90Degree() {
        Cell[][] newBoard = new Cell[board.length][board.length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                newBoard[j][board[i].length - 1 - i] = board[i][j];
            }
        }

        board = newBoard;
    }

    /**
     * This method checks if there are any cells with the same value on the board near each other.
     *
     * @return true if there are cells with the same value near each other, false otherwise
     */
    public boolean isAnySameCell() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                for (int[] d : DIRECTIONS) {
                    int y = i + d[0];
                    int x = j + d[1];

                    if  (y < 0 || y >= board.length || x < 0 || x >= board[i].length) {
                        continue;
                    }

                    if (board[i][j].compareTo(board[y][x]) == 0 && board[i][j].getValue() != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method checks if the game board is full by checking if there are no empty cells and no cells with the same value near each other.
     *
     * @return true if the board is full, false otherwise
     */
    public boolean isFull() {
        if (isAnySameCell()) return false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isCellEmpty(j, i)) return false;
            }
        }
        return true;
    }

    /**
     * This method checks if there is a cell with the value of 2048 on the board.
     *
     * @return true if there is a cell with the value of 2048, false otherwise
     */
    public boolean isThere2048() {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.getValue() == 2048) return true;
            }
        }
        return false;
    }

    /**
     * This method adds a new cell with the value of 2 or 4 to a random empty position on the board.
     *
     * @param rnd the random generator used to determine the position and value of the new cell
     */
    public void addRandomCell(RandomGenerator rnd) {
        int x,y;

        do {
            x = rnd.randomNumber(0, board.length - 1);
            y = rnd.randomNumber(0, board.length - 1);
        } while (!isCellEmpty(x, y));

        if (rnd.generateProbability(90)) {
            board[y][x] = new Cell(2);
        } else {
            board[y][x] = new Cell(4);
        }
    }

    /**
     * This method checks if a cell at the specified coordinates is empty (has a value of 0).
     *
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return true if the cell is empty, false otherwise
     */
    public boolean isCellEmpty(int x, int y) {
        return board[y][x].getValue() == 0;
    }

    public Cell[][] getBoard() {
        return board;
    }
}
