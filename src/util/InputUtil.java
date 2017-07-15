package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created by David Szilagyi on 2017. 07. 15..
 */
public class InputUtil {
    private static Scanner sc = new Scanner(System.in);

    public static <T> T getInput(String text, Class<T> cls) {
        boolean correct = false;
        while (!correct) {
            System.out.print(text);
            String input = sc.next();
            try {
                Method method = cls.getMethod("valueOf", String.class);
                return cls.cast(method.invoke(null, input));
            } catch (ClassCastException e) {
                System.out.println("Wrong input!");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println("Wrong input!");
            }
        }
        return null;
    }

    public static boolean checkInput(int number1, int number2) {
        return number1 < number2 ? true: false;
    }

    public static <T> T checkInput(String text, Class<T> cls, T compareTo) {
        boolean correct = false;
        while(!correct) {
        T input = getInput(text, cls);
            if (!input.equals(compareTo)) {
                return input;
            } else {
                System.out.println("Wrong input!");
            }
        }
        return null;
    }
}
