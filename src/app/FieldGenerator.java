package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public abstract class FieldGenerator {

    private FieldGenerator() {}

    public static void generateAndWrite(int height, int width, int maxBombs) {
        Random rand = new Random();
        char[] field = new char[2];
        int index = rand.nextInt(2);
        field[index] = Field.BOMB;
        if(index == 0) {
            field[index+1] = Field.FIELD;
        } else {
            field[index-1] = Field.FIELD;
        }
        try {
            int curBombs = 0;
            PrintWriter writer = new PrintWriter(String.format("src/fields/%dx%d.txt", height, width));
            writer.printf("%d %d\n", height, width);
            for (int x = 0; x < height; x++) {
            String line = "";
                for (int y = 0; y < width; y++) {
                    char append = field[rand.nextInt(2)];
                    if(append == Field.BOMB) {
                        if(curBombs == maxBombs) {
                            append = Field.FIELD;
                        } else {
                            if(rand.nextBoolean() && rand.nextBoolean()) {
                                append = Field.BOMB;
                                curBombs++;
                            } else {
                                append = Field.FIELD;
                            }
                        }
                    }
                    line += append;
                }
                writer.println(line);
            }
            writer.close();
            System.out.println("Field generated successfully!");
        } catch (IOException e) {
        }
    }
}
