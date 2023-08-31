<<<<<<< HEAD
import java.awt.*;
import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuffer sb = new StringBuffer();
        int nums = 1000;
        for (int i = 0; i < values.length; i++) {
            if (nums<=0) break;
            while (nums >= values[i]){
                nums -= values[i];
                sb.append(symbols[i]);
            }
        }
        String s = sb.toString();
    }
}
=======
public class Test3 {
    enum Singleton{
        INSTANCE;
        public void test(){
            System.out.println("test");
        }
    }
    public static void main(String[] args) {
        Singleton.INSTANCE.test();
    }
}
>>>>>>> 69b6c0cf297bc4cc5337f038860bb372fcdfad3e
