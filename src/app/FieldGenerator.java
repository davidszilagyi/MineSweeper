package app;

import java.util.Random;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public abstract class FieldGenerator {

    private static Object[][] board;

    private FieldGenerator() {
    }

    public static void generate(int height, int width, int maxBombs) {
        Random rand = new Random();
        char[] field = new char[2];
        int index = rand.nextInt(2);
        field[index] = Field.BOMB;
        if (index == 0) {
            field[index + 1] = Field.FIELD;
        } else {
            field[index - 1] = Field.FIELD;
        }
        board = new Object[height][width];
        int curBombs = 0;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                char append = field[rand.nextInt(2)];
                if (append == Field.BOMB) {
                    if (curBombs == maxBombs) {
                        append = Field.FIELD;
                    } else {
                        if (rand.nextBoolean() && rand.nextBoolean()) {
                            append = Field.BOMB;
                            curBombs++;
                        } else {
                            append = Field.FIELD;
                        }
                    }
                }
                board[x][y] = append;
            }
        }
        System.out.println("Field generated successfully!\n");
        System.out.println(String.format("The field is %dx%d", height, width));
    }

    public static Object[][] getBoard() {
        return board;
    }
}
