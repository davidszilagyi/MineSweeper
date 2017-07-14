import app.FieldGenerator;
import app.Game;
import java.util.Scanner;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public class Main {
    public static void main(String[] args) {
        System.out.print("Add the height: ");
        int height = new Scanner(System.in).nextInt();
        System.out.print("Add the width: ");
        int width = new Scanner(System.in).nextInt();
        System.out.print("Add the maximum bombs number: ");
        int maxBombs = new Scanner(System.in).nextInt();
        FieldGenerator.generateAndWrite(height, width, maxBombs);
        Game game = new Game(String.format("src/fields/%dx%d.txt", height, width));
        game.gameStart();
    }
}
