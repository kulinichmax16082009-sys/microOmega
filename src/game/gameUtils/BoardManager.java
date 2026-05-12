package game.gameUtils;

import game.gameObjects.Cell;
import game.gameObjects.Player;
import game.windows.GameWindow;

public class BoardManager {
    private Cell[][] board;
    private static final int[][] DIRECTIONS = { {-1, 0}, {0, -1}, {0, 1}, {1, 0} };

    public BoardManager(int size) {
        board = new Cell[size][size];
    }

    public void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell(0);
            }
        }
    }

    public boolean moveCellsLeft(Player player, GameWindow gameWindow) {
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

                        // Update score
                        player.addScore(board[i][k - 1].getValue());
                        gameWindow.updateScoreLabel(player.getScore());

                        moved = true;
                        merged = true;
                    }
                    k--;
                }
            }
        }
        return moved;
    }

    public boolean moveCellsRight(Player player, GameWindow gameWindow) {
        for (int i = 0; i < 2; i++) rotate90Degree();
        boolean moved = moveCellsLeft(player, gameWindow);
        for (int i = 0; i < 2; i++) rotate90Degree();

        return moved;
    }

    public boolean moveCellsUp(Player player, GameWindow gameWindow) {
        for (int i = 0; i < 3; i++) rotate90Degree();
        boolean moved = moveCellsLeft(player, gameWindow);
        rotate90Degree();

        return moved;
    }

    public boolean moveCellsDown(Player player, GameWindow gameWindow) {
        rotate90Degree();
        boolean moved = moveCellsLeft(player, gameWindow);
        for (int i = 0; i < 3; i++) rotate90Degree();

        return moved;
    }

    public void rotate90Degree() {
        Cell[][] newBoard = new Cell[board.length][board.length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                newBoard[j][board[i].length - 1 - i] = board[i][j];
            }
        }

        board = newBoard;
    }

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

    public boolean isFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isCellEmpty(j, i) && !isAnySameCell()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isThere2048() {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

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

    public boolean isCellEmpty(int x, int y) {
        return board[y][x].getValue() == 0;
    }

    public Cell[][] getBoard() {
        return board;
    }
}
