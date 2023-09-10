import java.util.*;

public class Test5 {
    public static void main(String[] args) {
        String path = "//home/lib/";
        String[] split = path.split("/");
        LinkedList<String> stack = new LinkedList<>();
        for (String s : split) {
            if ("..".equals(s)&&!stack.isEmpty()) stack.pop();
            if (!".".equals(s)&&!"..".equals(s)&&!"".equals(s)) stack.push(s);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.insert(0,stack.pop());
            sb.insert(0,"/");
        }

        if (sb.length()==0){
            sb.append("/");
        }
        String s = sb.toString();

    }
}
