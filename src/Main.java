import app.FieldGenerator;
import app.Game;
import util.InputUtil;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public class Main {
    public static void main(String[] args) {
        int height = InputUtil.checkInput("Add the height: ", Integer.class, 0, null);
        int width = InputUtil.checkInput("Add the width: ", Integer.class, 0, null);
        int bombs = InputUtil.checkInput("Add max bombs: ", Integer.class, 0, null);
        FieldGenerator.generate(height, width, bombs);
        Game game = new Game(FieldGenerator.getBoard());
        game.gameStart();
    }


}
