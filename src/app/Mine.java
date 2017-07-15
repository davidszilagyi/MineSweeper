package app;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public class Mine {
    protected int height;
    protected int width;
    protected Object[][] board;
    protected int curBombs;

    public Mine(Object[][] board) {
        this.board = board;
        this.height = board.length;
        this.width = board[0].length;
    }

    private void addZero() {
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (board[x][y].equals(Field.FIELD)) {
                    board[x][y] = 0;
                } else {
                    curBombs++;
                }
            }
        }
    }

    private void solve() {
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (!board[x][y].equals(Field.BOMB)) {
                    int bombs = 0;
                    for (int i = x - 1; i <= x + 1; i++) {
                        for (int c = y - 1; c <= y + 1; c++) {
                            try {
                                if (board[i][c].equals(Field.BOMB)) {
                                    bombs++;
                                }
                            } catch (IndexOutOfBoundsException e) {
                                continue;
                            }
                        }
                    }
                    board[x][y] = bombs;
                }
            }
        }
    }

    public void start() {
        addZero();
        solve();
    }
}
