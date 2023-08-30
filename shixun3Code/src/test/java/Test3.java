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
