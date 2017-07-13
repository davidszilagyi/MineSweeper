package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public class Mine {
    private BufferedReader br = null;
    private int height;
    private int width;
    private Object[][] board;
    private int curBombs;

    public Mine(String file) {
        try {
            this.br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createBoard() {
        try {
            String line;
            int counter = 0;
            int x = 0;
            while ((line = br.readLine()) != null) {
                if (counter == 0) {
                    height = Integer.valueOf(line.split(" ")[0]);
                    width = Integer.valueOf(line.split(" ")[1]);
                    board = new Object[height][width];
                    System.out.println(String.format("\nThe table is: %dx%d", height, width));
                    counter++;
                } else {
                    for (int y = 0; y < width; y++) {
                        board[x][y] = line.charAt(y);
                    }
                    x++;
                    counter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBoard(Object[][] board) {
        System.out.println("\nThe board:");
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
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

    public Object[][] getBoard() {
        return board;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getCurBombs() {
        return curBombs;
    }

    public void start() {
        createBoard();
//        showBoard(board);
        addZero();
        solve();
//        showBoard(board);
    }
}
