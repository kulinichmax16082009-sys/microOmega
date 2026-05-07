import java.awt.*;

public class BoardManager {
    private Cell[][] board;

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

    public int[][] initDirections() {
        return new int[][] { {-1, 0}, {0, -1}, {0, 1}, {1, 0} };
    }

    public boolean isAnySameCell() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                int[][] directions = initDirections();

                for (int[] d : directions) {
                    int y = i + d[0];
                    int x = j + d[1];

                    if  (y < 0 || y >= board.length || x < 0 || x >= board[i].length) {
                        continue;
                    }

                    if (board[i][j].getValue() == board[y][x].getValue() && board[i][j].getValue() != 0) {
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
                if (!isCellEmpty(j,i) && !isAnySameCell()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isThere2048() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addRandomCell(RandomGenerator rnd) {
        int x,y;

        x = rnd.randomNumber(0, board.length - 1);
        y = rnd.randomNumber(0, board.length - 1);

        while (!isCellEmpty(x,y)) {
            x = rnd.randomNumber(0, board.length - 1);
            y = rnd.randomNumber(0, board.length - 1);
        }

        if (rnd.generateProbability(35)) {
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

    public void setBoard(Cell[][] board) {
        this.board = board;
    }
}
