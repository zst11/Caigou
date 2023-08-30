package com.briup.jdk8;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Test3_ConfigBeanTest {
    public static List<String> subList;

    public static void main(String[] args) {
        // 读取文件
        InputStream resourceAsStream =
                Test3_ConfigBeanTest.class.getClassLoader()
                        .getResourceAsStream("com/briup/homework/exam2/config.txt");
        try(
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(resourceAsStream));
        ) {
            String line = null;
            List<List<String>> list = new ArrayList<>();
            while((line = br.readLine())!=null){
                if("".equals(line)){
                    List<String> temp = subList;
                    list.add(temp);
                    subList = null;
                }else {
                    if (subList == null) {
                        subList = new ArrayList<>();
                    }
                    subList.add(line);
                }
            }
            list.forEach(System.out::println);

            // 定义Map类型收集对象
            // key值是id，value值是对象
            Map<String,Object> map = new HashMap<>();
            // 数据保存在list中
            for (List<String> l : list) {
                // 前两行代表id和类名，是单个的
                String id = l.get(0).split("=")[1];
                String className = l.get(1).split("=")[1];
                // 创建对象
                Class<?> clazz = Class.forName(className);
                // 使用Class对象调用空参构造器（必须）创建对象
                Object o = clazz.getConstructor(null).newInstance();
                if(o instanceof Address){
                    Address address = (Address)o;
                    // 加属性
                    for (int i = 2; i < l.size(); i++) {
                        String s = l.get(i);
                        // property的相关内容
                        String[] propertyData = s.split(",");
                        // 定义set方法的名字
                        String setMethodName = null;
                        // 定义set方法传递的类型
                        Class<?> setClass = null;
                        // 定义set方法的传值
                        Object setValue = null;

                        for (String pds : propertyData) {
                            String[] split = pds.split("=");
                            String propertyName = split[0];
                            String propertyValue = split[1];

                            // 判断有没有引用
                            if("propertyRef".equals(propertyName)){
                                // 有引用就进行检索
                                String searchId = propertyValue;
                                Object o1 = map.get(searchId);
                                Class<?> o1Class = o1.getClass();
                            }else{
                                // 没有引用直接调用set方法
                                if("propertyName".equals(propertyName)){
                                    setMethodName = "set"+capUpperCase(propertyValue);
                                }
                                if("propertyType".equals(propertyName)){
                                    if("String".equals(propertyValue)){
                                        setClass = String.class;
                                    }
                                }
                                if("propertyValue".equals(propertyName)){
                                    setValue = propertyValue;
                                }
                            }
                        }
                        // 获取class对象进行方法调用
                        Class<? extends Address> addressClass = address.getClass();
                        Method method = addressClass.getMethod(setMethodName, setClass);
                        method.invoke(address,setValue);

                    }
                    map.put(id,address);

                }

                if(o instanceof Student1){
//                    Student student = (Student) o;
                    PropertyDescriptor pd = new PropertyDescriptor("propertyValue",clazz);
                    for (int i = 2; i < l.size(); i++) {
                        String s = l.get(i);
                        String[] split = s.split(",");
                        for (String s1 : split) {
                            String[] propertyData = s1.split("=");
                            String propertyName = propertyData[0];
                            String propertyValue = propertyData[1];
                            if ("propertyRef".equals(propertyName)){

                            }else {
                                if ("propertyName".equals(propertyName)){
                                 //   PropertyDescriptor pd = new PropertyDescriptor("propertyValue",clazz);

                                }
                            }
                        }
                    }
//                    map.put(id,student);
                }

            }

            System.out.println(map.get("1"));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 首字母大写
     * @param s
     * @return 首字母大写后的字符串
     */
    private static String capUpperCase(String s){
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }
}
class Address{
    private String province;
    private String city;
    private String street;

    public Address() {
    }

    public Address(String province, String city, String street) {
        this.province = province;
        this.city = city;
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
class Student1 {
    private Integer id;
    private String name;
    private Address address;

    public Student1() {
    }

    public Student1(Integer id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}


