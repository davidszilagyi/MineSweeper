import app.FieldGenerator;
import app.Game;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by David Szilagyi on 2017. 07. 13..
 */
public class Main {
    public static void main(String[] args) {
        Map<String, Integer> start = new LinkedHashMap<>();
        start.put("height", null);
        start.put("width", null);
        start.put("bombs", null);
        for (String key : start.keySet()) {
            if (key.equals("bombs")) {
                System.out.printf("Add the maximum %s number: ", key);
            } else {
                System.out.printf("Add the %s: ", key);
            }
            start.replace(key, new Scanner(System.in).nextInt());
        }
        FieldGenerator.generateAndWrite(start.get("height"), start.get("width"), start.get("bombs"));
        Game game = new Game(String.format("src/fields/%dx%d.txt", start.get("height"), start.get("width")));
        game.gameStart();
    }
}
