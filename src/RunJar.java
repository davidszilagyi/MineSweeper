import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;

/**
 * Created by David Szilagyi on 2017. 07. 15..
 */
public class RunJar {
    public static void main (String [] args) throws IOException, InterruptedException, URISyntaxException {
        Console console = System.console();
        if(console == null && !GraphicsEnvironment.isHeadless()){
            String filename = URLDecoder.decode(RunJar.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6), "UTF-8");
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
        }else{
            Main.main(new String[0]);
            System.out.println("Program has ended, please type 'exit' to close the console");
        }
    }
}
