import app.FieldGenerator;
import app.Game;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public class Main {
    public static void main(String[] args) {
        FieldGenerator.generateAndWrite(15, 15, 50);
        Game game = new Game("src/fields/15x15.txt");
        game.gameStart();
//        app.Mine mine35 = new app.Mine("src/fields/3x5.txt");
//        app.Mine mine44 = new app.Mine("src/fields/4x4.txt");
//        app.Mine mine1010 = new app.Mine("src/fields/10x10.txt");
//        mine35.start();
//        mine44.start();
//        mine1010.start();
//        app.FieldGenerator.generateAndWrite(15, 15);
//        app.Mine mine1515 = new app.Mine("src/fields/15x15.txt");
//        mine1515.start();
    }
}
