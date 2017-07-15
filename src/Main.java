import app.FieldGenerator;
import app.Game;
import util.InputUtil;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public class Main {
    public static void main(String[] args) {
        int height = InputUtil.checkInput("Add the height: ", Integer.class);
        int width = InputUtil.checkInput("Add the width: ", Integer.class);
        int bombs = InputUtil.checkInput("Add max bombs: ", Integer.class);
        FieldGenerator.generate(height, width, bombs);
        Game game = new Game(FieldGenerator.getBoard());
        game.gameStart();
    }
}
