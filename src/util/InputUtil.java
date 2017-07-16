package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
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

    public static <T> T checkInput(String text, Class<T> cls, T compareTo1, T compareTo2) {
        boolean correct = false;
        while (!correct) {
            T input = getInput(text, cls);
            if (compareTo2 == null) {
                if (compare(input, compareTo1) > 0) {
                    return input;
                } else {
                    System.out.println("Wrong input!");
                }
            } else if(compare(input, compareTo1) > 0 && compare(compareTo2, input) > 0) {
                return input;
            } else {
                System.out.println("Wrong input!");
            }
        }
        return null;
    }

    private static int compare(Object o1, Object o2) {
        try {
            int number1 = Integer.parseInt(String.valueOf(o1));
            int number2 = Integer.parseInt(String.valueOf(o2));
            if (number1 == number2) {
                return 0;
            } else if (number1 < number2) {
                return -1;
            } else if (number1 > number2) {
                return 1;
            } else {
                return 0;
            }
        } catch (ClassCastException e) {
            return 0;
        }
    }
}
