package com.briup.jdk8.work2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test3 {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 1
        List<Transaction> list = transactions.stream().filter(s -> s.getTime() == 2011)
                .sorted((o1, o2) -> {
                    return o1.getVolume() - o2.getVolume();
                }).collect(Collectors.toList());
//        System.out.print(list);
        System.out.println("--------");
        // 2
        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);
        traders.stream().map(Trader::getUniversity).distinct().forEach(System.out::println);
        System.out.println("--------");
        // 3
        traders.stream().filter(s->"Cambridge".equals(s.getUniversity()))
                .sorted(((o1, o2) -> o1.getName().compareTo(o2.getName()))).forEach(System.out::println);
        System.out.println("-------");
        // 4
        traders.stream().map(Trader::getName).sorted(((o1, o2) -> o1.compareTo(o2)))
                .forEach(System.out::println);
        System.out.println("-------");
        // 5
        traders.stream().filter(s->"Milan".equals(s.getUniversity())).map(Trader::getName)
                .forEach(System.out::println);
        System.out.println("--------");
        // 6
        System.out.println(transactions.stream().filter(s -> "Cambridge".equals(s.getName().getUniversity()))
                .map(Transaction::getVolume).reduce((o1, o2) -> o1 + o2).get());
        System.out.println("---------");
        // 7
        Optional<Integer> max = transactions.stream().filter(s -> "Cambridge".equals(s.getName().getUniversity()))
                .map(Transaction::getVolume).max((o1, o2) -> o1.compareTo(o2));
        System.out.println(max);
        System.out.println("----------");
        // 8
        Optional<Integer> min = transactions.stream().filter(s -> "Cambridge".equals(s.getName().getUniversity()))
                .map(Transaction::getVolume).min((o1, o2) -> o1.compareTo(o2));
        System.out.println(min);
        Integer integer = min.get();
        System.out.println(integer);
    }
}
class Transaction{
    private Trader name;
    private int time;
    private int volume;

    public Transaction(Trader name, int time, int volume) {
        this.name = name;
        this.time = time;
        this.volume = volume;
    }

    public Transaction() {
    }

    /**
     * 获取
     * @return name
     */
    public Trader getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(Trader name) {
        this.name = name;
    }

    /**
     * 获取
     * @return time
     */
    public int getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * 获取
     * @return volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * 设置
     * @param volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String toString() {
        return "Transaction{name = " + name + ", time = " + time + ", volume = " + volume + "}";
    }
}
class Trader{
    private String name;
    private String university;
    public Trader(String name,String university){
        this.name = name;
        this.university = university;
    }

    public Trader() {
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return university
     */
    public String getUniversity() {
        return university;
    }

    /**
     * 设置
     * @param university
     */
    public void setUniversity(String university) {
        this.university = university;
    }

    public String toString() {
        return "Trader{name = " + name + ", university = " + university + "}";
    }
}
