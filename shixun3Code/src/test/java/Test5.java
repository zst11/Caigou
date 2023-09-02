public class Test5 {
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
