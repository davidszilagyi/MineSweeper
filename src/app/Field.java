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
        int lengthX = String.valueOf(height).length();
        int lengthY = String.valueOf(width).length();
        if (lengthX % 2 == 0) {
            lengthX++;
        }
        if (lengthY % 2 == 0) {
            lengthY++;
        }
        printHeader(width, lengthX, lengthY);
        for (int x = 0; x < height; x++) {
            System.out.printf("%-" + lengthX + "d", x + 1);
            for (int y = 0; y < width; y++) {
                System.out.printf("%" + lengthY + "s", board[x][y]);
            }
            System.out.println();
        }
    }

    private static void printHeader(int width, int lengthX, int lengthY) {
        for (int i = 0; i < width; i++) {
            if (i == 0) {
                System.out.printf("%" + lengthX + "s", " ");
            }
            System.out.printf("%" + lengthY + "d", i + 1);
        }
        System.out.println();
    }
}
