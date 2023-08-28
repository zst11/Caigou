package com.briup.jdk8;

public class Test2_Calculate {
    public int calculate(int a,int b,Action action){
        return action.action(a,b);
    }

    public static void main(String[] args) {
        Action a1 = new Add();
        Test2_Calculate t = new Test2_Calculate();
        t.calculate(10,20,a1);
        t.calculate(10, 20, new Action() {
            @Override
            public int action(int result, int i) {
                return result - i;
            }
        });
        System.out.println(t.calculate(10, 20, ((result, i) -> result / i)));
    }
}
@FunctionalInterface
interface Action{
    int action(int result,int i);
}
class Add implements Action{

    @Override
    public int action(int result, int i) {
        return result+i;
    }
}