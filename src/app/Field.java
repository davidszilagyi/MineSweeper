package app;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public abstract class Field {
    protected static final char BOMB = '*';
    protected static final char BOOM = 'B';
    protected static final char HINT = '?';
    protected static final char FIELD = '.';

    private Field() {}

    public static void showBoard(Object[][] board, int height, int width) {
        System.out.println("\nThe board:");
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
    }
}
