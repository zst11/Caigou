import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea",  "ate","tan", "nat", "bat"};
        ArrayList<List<List<String>>> lists = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            List<String> list = map.getOrDefault(s,new ArrayList<String>());
            list.add(str);
            map.put(s,list);
        }
        ArrayList<List<String>> lists1 = new ArrayList<>(map.values());
        System.out.println(lists1);
    }
}

