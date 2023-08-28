public class Test1 {
    public static void main(String[] args) {
        System.out.println(getSum(4));
    }
    public static int getSum(int x){
        if (x == 1) return 1;
        if (x == 2) return 2;
        return getSum(x - 1) + getSum(x - 2);
    }
}
